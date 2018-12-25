package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {

	/**
	 * 分页查询店铺，可输入的条件有，店铺名，店铺状态，店铺类别，区域Id，owner
	 * 
	 * @param shopCodition
	 * @param rowIndex
	 *            从第几行开始取数据
	 * @param 返回的条数
	 * @return
	 * 
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);

	/**
	 * 返回查询总数
	 * 
	 * @param shopCondition
	 * @return
	 * 
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

	/**
	 * 通过shop id查询电批
	 * 
	 * @param ShopId
	 * @param shop
	 */
	Shop queryByShopId(long shopId);

	/**
	 * 新增店铺
	 * 
	 * @param shop
	 * @author 萝卜丁
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺信息
	 * 
	 * @author 萝卜丁
	 * @param shop
	 */
	int updateShop(Shop shop);
}
