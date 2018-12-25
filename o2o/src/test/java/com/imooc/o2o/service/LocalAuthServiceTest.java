package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.LocalAuthExcution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.enums.WechatAuthStateEnum;

import jdk.nashorn.internal.ir.annotations.Ignore;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testABindLocalAuth() {
		//新增一条平台账号
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String username="testusername";
		String password="testpassword";
		//给平台账号设置用户信息
		//给用户上设置用户ID，表名是某个用户创建的账号
		personInfo.setUserId(1L);
		//给平台账号设置用户信息
		localAuth.setPersonInfo(personInfo);
		//设置账号
		localAuth.setUsername(username);
		//设置密码
		localAuth.setPassword(password);
		//绑定账号
		LocalAuthExcution lae = localAuthService.bindLocalAuth(localAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
		//通过userId找到新增的localAuth
		localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
		//打印
		System.out.println(localAuth.getPersonInfo().getName());
		System.out.println(localAuth.getPassword());
	}
	@Test
	public void testBModifyLocalAuth() {
		//设置账号信息
		long userId = 1;
		String username= "testusername";
		String password = "testpassword";
		String newpassword = "testnewpassword";
		//修改该账号对应的密码
        LocalAuthExcution lae = localAuthService.modifyLocalAuth(userId, username, password, newpassword);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), lae.getState());
        //通过账号密码找到修改后的平台账号
        LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, newpassword);
        //打印
        System.out.println(localAuth.getPersonInfo().getName());
	}
}
