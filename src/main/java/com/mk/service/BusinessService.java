package com.mk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mk.entry.Business;
import com.mk.mapper.BNumMapper;
import com.mk.mapper.BusinessMapper;
import com.mk.util.RandomID;

/**
 * 业务层：订单业务信息管理调用
 * @author 上官雅晴
 *
 */
@Scope("prototype")
@Service
public class BusinessService
{
	@Resource
	BusinessMapper businessMapper;
	@Resource
	BNumMapper bNumMapper;
	@Resource
	CarService carService;
	@Resource
	CargoService cargoService;
	
	/**
	 * 1、根据车牌号、订单状态查询订单业务信息列表
	 * @param licensenumber
	 * @param b_status
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Business> findBusinessByNumber(String licensenumber, String b_status, 
												int index, int size)
	{
		return businessMapper.selectBusinessByNumber(licensenumber, b_status, index, size);
	}
	
	/**
	 * 2、根据车牌号、状态查询相关信息数目
	 * @param licensenumber
	 * @param b_status
	 * @return
	 */
	public int findCountOfBusinessWithNumber(String licensenumber, String b_status)
	{
		return businessMapper.selectCountOfBusinessWithNumber(licensenumber, b_status);
	}
	
	/**
	 * 3、添加订单信息
	 * 车辆的当前载重、货物的库存量
	 * 订单状态为“待发货”
	 * @param business
	 * @return
	 */
	@Transactional
	public int addBusiness(Business business)
	{
		/**
		 * 1、设置业务订单的编号、状态
		 */
		business.setB_id( RandomID.getID()+bNumMapper.getNum() ); //设置业务编号
		business.setB_status("待发货");  //设置订单状态
		
		/**
		 * 2、获取车辆相关信息
		 */
		String licensenumber = business.getLicensenumber(); //车牌号码
		
		/**
		 * 3、获取货物的相关信息
		 */
		int c_id = business.getC_id();						  //货物编号
		int singleWeight = cargoService.findWeightById(c_id); //货物重量
		int amount = business.getAmount(); 					  //订单中货物数量
		int weightValue = amount*singleWeight; 				  //订单中货物总重量
		
		/**
		 * 4、修改车辆的当前载重(增)
		 */
		carService.editCarLoadweightValue(licensenumber, weightValue);
		
		/**
		 * 5、修改货物库存量(减)
		 */
		cargoService.editCargoStore(-amount, c_id);
		
		return businessMapper.insertBusiness(business);
	}
	
	/**
	 * 4、根据订单编号删除信息
	 * 修改车辆当前载重？？、修改货物库存量？？？
	 * @param b_id
	 * @return
	 */
	@Transactional
	public int removeBusiness(String b_id)
	{
		/**
		 * 1、获取订单业务信息
		 */
		Business business = businessMapper.selectBusinessById(b_id);
		
		/**
		 * 如果订单状态为送达，直接清除订单信息即可
		 */
		if( "送达".equals(business.getB_status()) )
			return businessMapper.deleteBusiness(b_id);
		/** 
		 * 订单状态为运输中，禁止删除
		 */
		if( "运输中".equals(business.getB_status()) )
			return -1;
			
		/**
		 * 2、获取车辆车牌号码
		 */
		String licensenumber = business.getLicensenumber();
		
		/**
		 * 3、获取货物信息
		 */
		int amount = business.getAmount();  //获取数量
		int c_id = business.getC_id();  //货物编号
		int weight = cargoService.findWeightById(c_id); //货物单重量
		int weightValue = amount * weight;  //货物总重量
		
		/**
		 * 4、修改货物库存量(增)
		 */
		cargoService.editCargoStore(amount, c_id);
		
		/**
		 * 5、修改车辆当前载重(减)
		 */
		carService.editCarLoadweightValue(licensenumber, -weightValue);
		
		return businessMapper.deleteBusiness(b_id);
	}
	
	/**
	 * 5、查询订单信息列表(分页)
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Business> findBusinessList(int index, int size)
	{
		return businessMapper.selectBusinessList(index, size);
	}
	
	/**
	 * 6、根据订单编号查询信息
	 * @param id
	 * @return
	 */
	public Business findBusinessById(String id)
	{
		return businessMapper.selectBusinessById(id);
	}
	
	/**
	 * 7、根据车牌号修改订单状态
	 * @param licensenumber
	 * @param b_status
	 */
	public int editBusinessStatus(String licensenumber, String b_status)
	{
		return businessMapper.updateBusinessStatus(licensenumber, b_status);
	}
	
	/**
	 * 8、根据车牌号将订单从一种状态转为另一种状态
	 * @param licensenumber
	 * @param beforeStatus
	 * @param afterStatus
	 * @return
	 */
	public int editBusinessStatusOneToAnother(String licensenumber, 
								String beforeStatus, String afterStatus)
	{
		return businessMapper.updateBusinessStatusOneToAnother(licensenumber,
				beforeStatus, afterStatus);
	}
	
}
