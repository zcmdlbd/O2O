package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductImg;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgTest extends BaseTest {
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testABatchInsertProductImg() throws Exception {
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图1");
		productImg1.setImgDesc("测试1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图2");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testCDeleteProductByProductId() throws Exception {
		//删除新增的两条商品详情记录图片
		long productId = 1;
		int effected = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2,effected);
		
	}
}
