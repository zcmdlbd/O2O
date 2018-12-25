package com.imooc.o2o.service;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest {
	@Autowired 
	private ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws ShopOperationException, FileNotFoundException {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(28L);
		ProductCategory pc = new  ProductCategory();
		pc.setProductCategoryId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品全1");
		product.setProductDesc("cesi");
		product.setPriority(10);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//缩略图文件流
		File thumbnailFile = new File("D:/projectdev/image/3.jpg");
		InputStream is  = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		
		//两个详情图
		File productImg1 =new File("D:/projectdev/image/1.jpg");
		InputStream is1  = new FileInputStream(productImg1);
		File productImg2 =new File("D:/projectdev/image/2.jpg");
		InputStream is2  = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
		
	}
	
		@Test
		public void testModifyProduct() throws ShopOperationException, FileNotFoundException{
			//创建shopId为28且productCategoryId为5的商品实例并给其成员变量赋值
			Product product = new Product();
			Shop shop = new Shop();
			shop.setShopId(28L);
			ProductCategory pc = new ProductCategory();
			pc.setProductCategoryId(5L);
			product.setProductId(1L);
			product.setShop(shop);
			product.setProductCategory(pc);
			product.setProductName("正式商品");
			product.setProductDesc("正式商品");
			//创建缩略图文件流
			File thumbnailFile = new File("D:/projectdev/image/1.jpg");
			InputStream is = new FileInputStream(thumbnailFile);
			ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
			//创建两个商品详情图文件流并将它们添加到详情图中
			File productImg1 =new File("D:/projectdev/image/touxiang.jpg");
			InputStream is1  = new FileInputStream(productImg1);
			File productImg2 =new File("D:/projectdev/image/2.jpg");
			InputStream is2  = new FileInputStream(productImg2);
			List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
			productImgList.add(new ImageHolder(productImg1.getName(), is1));
			productImgList.add(new ImageHolder(productImg2.getName(), is2));
			//添加商品并验证
			ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
			assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
		}
}
