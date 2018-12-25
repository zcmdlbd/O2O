package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExcution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
    /**
     * 通过账号密码获取平台账号信息
     * 
     * @param userName
     * @param password
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName,String password);
    
    /**
     * 通过userId获取平台账号信息
     * 
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);
    
    /**
     * 绑定微信，生成平台专属的账号
     * 
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExcution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;
    
    /**
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExcution modifyLocalAuth(Long userId,String username,String password,String newPassword) throws LocalAuthOperationException;
    
}
