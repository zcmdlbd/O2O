package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询某个店铺的所有商品类别
	 * @param shopId
	 * @return List
	 * */
	List <ProductCategory> queryProductCategoryList(long shopId);
	
	/**
	 * 
	 * 批量新增商品类别
	 * @param productgoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productgoryList) throws ProductCategoryOperationException;
	
	/**
	 * 先将此类别下的商品的类别ID设置为空，再删除商品分类
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)throws ProductCategoryOperationException;
}
