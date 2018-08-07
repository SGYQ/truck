package com.mk.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mk.entry.Car;
import com.mk.entry.Point;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Scope("prototype")
@Service
public class CarryService
{
	@Resource
	CarService carService;
	@Resource
	BusinessService businessService;
	
	/**
	 * 1、到站
	 * > 修改车辆的当前载重、车辆状态、出发地、目的地、当前位置
	 * > 修改订单状态
	 * @param licensenumber
	 */
	@Transactional
	public void reach(String licensenumber)
	{
		Car car = carService.findCarById(licensenumber);
		
		/**
		 * 1、修改车辆的当前载重、车辆状态、出发地、目的地、当前位置
		 */
		String start = car.getStarting_();
		String end = car.getDestination();
		car.setC_status("闲置");
		car.setLoadweight(0);
		car.setStarting_(end);
		car.setDestination(start);
		car.setCurrentlocation(end);
		carService.editCar(car);
		
		/**
		 * 2、修改订单状态
		 */
		businessService.editBusinessStatus(licensenumber, "送达");
	}
	
	/**
	 * 2、发车
	 * > 修改车辆状态与订单状态
	 * > 处理坐标数据
	 * @param licensenumber
	 * @param start
	 * @param end
	 * @param data
	 * @return
	 */
	@Transactional
	public Carry depart(String licensenumber, String start, String end, String data) 
	{
		Carry carry = new Carry();
		Point location = new Point();
		
		/**
		 * 1、处理车辆与订单业务
		 */
		carService.editCarStatusById(licensenumber, "营运");
		
		businessService.editBusinessStatusOneToAnother(licensenumber, "待发货", "运输中");
		
		/**
		 * 2、处理前台发送的坐标数据
		 */
		JSONArray jsonArray = JSONArray.fromObject(data);
		int length = jsonArray.size();
		double[][] routes = new double[length][2];
		
		for(int i=0; i<length; i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			//数组存储
			routes[i][0] = jsonObject.getDouble("lng");
			routes[i][1] = jsonObject.getDouble("lat");
		}
		//初始化
		location.setLongtiude( routes[0][0] ); //经度
		location.setLatitude( routes[0][1] );  //纬度
		
		//线程类赋值
		carry.setLicensenumber(licensenumber);
		carry.setStart(start);
		carry.setEnd(end);
		carry.setArrPoints(routes);
		carry.setIndex(0);
		carry.setLocation(location);
		
		return carry;
	}
	
	/**
	 * 3、查询车辆位置(闲置)
	 * @param licensenumber
	 * @return
	 */
	public Map<String,Object> findCarLoaction(String licensenumber)
	{
		Map<String,Object> map = new HashMap<>();
		
		Car car = carService.findCarById(licensenumber);
		map.put("start", car.getStarting_());
		map.put("end", car.getDestination());
		map.put("location", car.getCurrentlocation());
		
		return map;
	}
	
}
