package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;


import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println(count);
	}
	
	
	@Test
	@Ignore
	public void  testQueryByShopId() {
		long shopId = 35;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaName" + shop.getArea().getAreaName());
	}
	
//	@Test
//	public void testInsertShop() {
//		Shop shop = new Shop();
//		PersonInfo owner = new PersonInfo();
//		Area area = new Area();
//		ShopCategory shopCategory = new ShopCategory();
//		owner.setUserId(1L);
//		area.setAreaId(2);
//		shopCategory.setShopCategoryId(1L);
//		shop.setOwner(owner);
//		shop.setArea(area);
//		shop.setShopCategory(shopCategory);
//		shop.setShopName("zcm的测试店铺");
//		shop.setShopDesc("ceshi");
//		shop.setShopAddr("ceshi");
//		shop.setPhone("cehsi");
//		shop.setShopImg("cehsi");
//		shop.setPriority(10);
//		shop.setCreateTime(new Date());
//		shop.setEnableStatus(1);
//		shop.setAdvice("审核中");
//		int effectedNum = shopDao.insertShop(shop);
//		assertEquals(1, effectedNum);
//	}
//	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(40L);
		shop.setShopDesc("ceshimiaoshi");
		shop.setShopAddr("ceshidizhi");
		
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
