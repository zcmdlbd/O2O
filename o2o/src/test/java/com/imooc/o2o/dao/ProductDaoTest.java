package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(28L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(1L);
		//初始化三个商品实例添加进shopId为28的商店里
		//商品分类Id也为1 
		Product product1 = new Product();
		product1.setProductName("商品1");
		product1.setImgAddr("测试");
		product1.setImgAddr("测试地址");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setProductCategory(pc1);
		product1.setShop(shop1);
		
		Product product2 = new Product();
		product2.setProductName("商品2");
		product2.setImgAddr("测试2");
		product2.setImgAddr("测试地址2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setProductCategory(pc1);
		product2.setShop(shop1);
		
		Product product3 = new Product();
		product3.setProductName("商品3");
		product3.setImgAddr("测试3");
		product3.setImgAddr("测试地址3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setProductCategory(pc1);
		product3.setShop(shop1);
		
		//判断成功与否
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
		
	}
	@Test
	public void testBQueryProductList() throws Exception {
		Product productCondition = new Product();
		//分页查询，预期返回三条结果
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(3, productList.size());
		//查询名称为测试的商品总数
		int count = productDao.queryProductCount(productCondition);
		assertEquals(21, count);
		//使用商品名称模糊查询，预期返回两条结果
		productCondition.setProductName("测试");
		productList = productDao.queryProductList(productCondition, 0, 3);
		assertEquals(2, productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(2, count);
	}
	
	
	@Test
	public void queryCProductByProductId() throws Exception{
		long productId = 1;
		//初始化两个商品详情图实例作为productId为1的商品下的详情图片
		//批量插入到商品详情图表中
		ProductImg productImg = new ProductImg();
		productImg.setImgAddr("图片1");
		productImg.setImgDesc("测试图片1");
		productImg.setPriority(1);
		productImg.setCreateTime(new Date());
		productImg.setProductId(productId);
		
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setImgDesc("测试图片2");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		//查询productId为1 的商品信息并返回详情图实例列表size是否为2
		Product product = productDao.queryProductById(productId);
		assertEquals(2, product.getProductImgList().size());
		//删除新增的这两个商品详情图
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
	
	
	@Test 
	public void testDUpdatePrroduct() {
		Product product = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(28L);
		pc.setProductCategoryId(5L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductName("第一个产品");
		product.setProductCategory(pc);
		//修改productId 为1 的商品
		//校验影响的行数是不是1
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	} 
}
