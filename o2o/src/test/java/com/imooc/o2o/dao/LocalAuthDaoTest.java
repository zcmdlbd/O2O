package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	@Autowired
	private static final String username = "testusername";
	private static final String password = "testpasswordnew";

//	@Test
//	public void testAInsertLocalAuth() throws Exception {
//		// 新增一条平台账号信息
//		LocalAuth localAuth = new LocalAuth();
//		PersonInfo personInfo = new PersonInfo();
//		personInfo.setUserId(1L);
//		// 给平台账号绑定上用户信息
//		localAuth.setPersonInfo(personInfo);
//		// 设置用户名和密码
//		localAuth.setUsername(username);
//		localAuth.setPassword(password);
//		localAuth.setCreateTime(new Date());
//		int effectedNum = localAuthDao.insertLocalAuth(localAuth);
//		assertEquals(1, effectedNum);
//	}

	@Test
	public void testBQueryLocalAuthByUserNameAndPwd() throws Exception {
		// 按照账号密码查询用户信息
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
		assertEquals("测试", localAuth.getPersonInfo().getName());
	}

	@Test
	public void testCQueryLocalAuthByUserId() throws Exception {
		//按照用户Id查询平台账户，进而获得用户信息
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		assertEquals("测试", localAuth.getPersonInfo().getName());
	}
//	
//	@Test
//	public void testDUpdateLocalAuth() throws Exception {
//		//依据账户Id，平台账号，以及旧密码，修改平台账号和密码
//		Date date = new Date();
//		int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, password+"new", date);
//		assertEquals(1, effectedNum);
//		//查询出该平台账号的最新信息
//		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
//		//输出新密码
//		System.out.println(localAuth.getPassword());
//	}
//	
}
