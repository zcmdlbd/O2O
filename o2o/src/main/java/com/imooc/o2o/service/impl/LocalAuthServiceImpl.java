package com.imooc.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.dto.LocalAuthExcution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.enums.LocalAuthStateEnum;
import com.imooc.o2o.exceptions.LocalAuthOperationException;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.MD5;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExcution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// 空值判断
		if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
				|| localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExcution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		// 查询此用户是否已经绑定过平台账号
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if (tempAuth != null) {
			// 如果绑定过则退出，保证平台账号的唯一性
			return new LocalAuthExcution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			// 如果之前没有绑定过，则创建一个账号与该用户绑定
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			// 对密码进行MD5加密
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			// 判断是否创建成功
			if (effectedNum <= 0) {
				throw new LocalAuthOperationException("账号绑定失败");
			} else {
				return new LocalAuthExcution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperationException("添加失败" + e.toString());
		}
	}

	@Override
	@Transactional
	public LocalAuthExcution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException {
		// 非空判断，判断传入用户的Id，账号，新旧密码是否相同，不满足则退回
		if (userId != null && username != null && password != null && newPassword != null
				&& !password.equals(newPassword)) {
			try {
				// 更新密码，并对新密码进行MD5加密
				int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				// 判断是否成功
				if (effectedNum <= 0) {
					throw new LocalAuthOperationException("更新失败");
				}
				return new LocalAuthExcution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new LocalAuthOperationException("更新密码失败" + e.toString());
			}
		} else {
			return new LocalAuthExcution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
