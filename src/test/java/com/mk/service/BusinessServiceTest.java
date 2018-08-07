package com.mk.service;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mk.entry.Business;
import com.mk.entry.Car;
import com.mk.entry.Cargo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BusinessServiceTest
{
	@Autowired
	private BusinessService businessService;
	@Autowired
	CarService carService;
	@Autowired
	CargoService cargoService;
	
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindBusinessByNumber()
	{
		List<Business> list = 
				businessService.findBusinessByNumber("", null, 0, 5);
		
		for(Business o: list)
			System.out.println(o);
	}
	
	/**
	 * 通过
	 */
	@Test
	public void testFindCountOfBusinessWithNumber()
	{
		int count = businessService.findCountOfBusinessWithNumber("", "");
		
		System.out.println(count);
	}
	
	/**
	 * 测试通过
	 */
	@Test
	public void testAddBusiness()
	{
		Business business = new Business();
		
		business.setLicensenumber("si");
		business.setC_id(23239);
		business.setAmount(2);
		business.setB_date( new Date() );
		business.setRemark("测试数据》》");
		
		Car car= carService.findCarById("si");
		System.out.println("添加订单前车辆的当前载重："+car.getLoadweight());
		Cargo cargo = cargoService.findCargoById(23239);
		System.out.println("添加订单前货物的库存量："+cargo.getC_store());
		
		int num = businessService.addBusiness(business);
		System.out.println(num);
		System.out.println();
		
		Car carL = carService.findCarById("si");
		System.out.println("添加订单后车辆的当前载重："+carL.getLoadweight());
		Cargo cargoL = cargoService.findCargoById(23239);
		System.out.println("添加订单后货物的库存量："+cargoL.getC_store());
	}

	/**
	 * 通过
	 */
	@Test
	public void testRemoveBusiness()
	{
		String b_id = "B20180603 29";
		
		int num = businessService.removeBusiness(b_id);
		System.out.println(num);
		
	}

	/**
	 * 测试通过
	 */
	@Test
	public void testFindBusinessList()
	{
		List<Business> list = businessService.findBusinessList(0, 10);
		
		for(Business o: list)
			System.out.println(o);
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindBusinessById()
	{
		Business business = businessService.findBusinessById("B20180519 22");
		System.out.println(business);
	}
	
	/**
	 * 根据车牌号修改订单状态
	 * 发车、车辆到站
	 */
	@Test
	public void testEditBusinessStatus()
	{
		int num = businessService.editBusinessStatus("si", "送达");
		System.out.println(num); //返回2，返回匹配的结果，并非车辆对应的所有订单数目
	}

}
