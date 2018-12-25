package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;

public class WechatAuthServiceTest extends BaseTest {
	@Autowired
	private WechatAuthService wechatAuthService;
	@Test
	public void testRegister() {
		//新增一条微信号
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		String openId = "xiaomingxinzengceshi";
		//给微信账号设置用户信息，但不设置用户Id
		//创建微信账号的时候自动创建用户信息
		personInfo.setCreateTime(new Date());
		personInfo.setName("测试新增");
		personInfo.setUserType(1);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setCreateTime(new Date());
		wechatAuth.setOpenId(openId);
		WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(), wae.getState());
		//通过openId找到新增的wechatAuth
		wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
		//打印用户名字看是否和预期相符合
		System.out.println(wechatAuth.getPersonInfo().getName());
	}
}
