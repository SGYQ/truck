package com.mk.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mk.entry.Cargo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CargoServiceTest
{
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
	public void testFindCargos()
	{
		List<Cargo> list = cargoService.findCargos(0, 5);
		System.out.println( list.size() );
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindCountOfCargo()
	{
		int counts = cargoService.findCountOfCargo();
		System.out.println(counts);
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindCargoById()
	{
		Cargo cargo = cargoService.findCargoById(401);
		System.out.println(cargo);
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindHavingCargo()
	{
		List<Cargo> list = cargoService.findHavingCargo(0, 10);
		for(Cargo o: list)
			System.out.println(o);
	}

	/**
	 * 通过
	 */
	@Test
	public void testFindCountOfHavingCargo()
	{
		int count = cargoService.findCountOfHavingCargo();
		System.out.println(count);
	}

	/**
	 * 通过
	 */
	@Test
	public void testAddCargo()
	{
		Cargo cargo = new Cargo();
		
		cargo.setC_name("盐酸");
		cargo.setC_store(16);
		cargo.setC_weight(2);
		
		int num = cargoService.addCargo(cargo);
		System.out.println(num);
	}

	/**
	 * 通过
	 */
	@Test
	public void testEditCargo()
	{
		Cargo cargo = cargoService.findCargoById(441);
		cargo.setC_name("库卡");
		
		int number = cargoService.editCargo(cargo);
		System.out.println(number);
	}

	/**
	 * 增加减少
	 */
	@Test
	public void testEditCargoStore()
	{
		int num = cargoService.editCargoStore(-8, 23233);
		System.out.println(num);
	}

	/**
	 * 重量
	 */
	@Test
	public void testFindWeightById()
	{
		int weight = cargoService.findWeightById(408);
		System.out.println(weight);
	}

}
