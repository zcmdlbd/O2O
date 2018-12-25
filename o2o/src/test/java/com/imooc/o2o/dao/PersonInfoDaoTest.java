package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoDaoTest extends BaseTest {
	@Autowired
	private PersonInfoDao personInfoDao;
	
	
	@Test
	public void testAInsertPersonInfo() throws Exception {
		//设置新增的用户信息
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("小明");
		personInfo.setGender("男");
		personInfo.setUserType(2);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(2);
		int effectedNum  = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(effectedNum, 1);
	}
	
	@Test
	public void testBQueryPersonInfoById() {
		long userId = 1;
		//查询Id为1的用户信息
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());
	}
}
