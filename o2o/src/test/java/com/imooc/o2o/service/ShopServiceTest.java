package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(22L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition,3, 5);
		System.out.println("店铺列表总数为："+se.getShopList().size());
		System.out.println("列表总数" + se.getCount());
	}
	
	
	@Test
	@Ignore
	public void testmodifyshop() throws ShopOperationException,FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(35L);
		shop.setShopName("修改后的店铺名称");
		File shopImg = new File("D:/projectdev/image/1.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("1.jpg", is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println("新的图片地址" + shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("zcm的测试店铺3");
		shop.setShopDesc("ceshi3");
		shop.setShopAddr("ceshi3");
		shop.setPhone("cehsi3");
		shop.setPriority(10);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File image = new File("D:/projectdev/image/2.jpg");
		InputStream is = new FileInputStream(image);
		ImageHolder imageHolder = new ImageHolder(image.getName(),is);
		ShopExecution se = shopService.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());

	}
}
