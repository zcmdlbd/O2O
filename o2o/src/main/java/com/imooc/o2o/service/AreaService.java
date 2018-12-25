package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.entity.Area;

public interface AreaService {
	public final static String AREALISTKEY ="areaList";
	/**
	 * @author 萝卜丁
	 * */
	List<Area> getAreaList();
}
