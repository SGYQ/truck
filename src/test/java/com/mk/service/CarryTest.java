package com.mk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mk.entry.Point;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CarryTest
{
	@Autowired
	Carry carry;
	
	/**
	 * 不及预期，线程不要放在Test单元测试中
	 */
	@Test
	public void test()
	{
		Carry carry = new Carry();
		double[][] arrPoints = new double[5][2];
		carry.setArrPoints(arrPoints);
		carry.setIndex(0);
		
		String start = "江阴";
		String end = "常州";
		String licensenumber = "DDD";
		Point location = new Point();
		location.setLongtiude(arrPoints[0][0]);
		location.setLatitude(arrPoints[0][1]);
		carry.setEnd(end);
		carry.setLicensenumber(licensenumber);
		carry.setLocation(location);
		carry.setStart(start);
		
		carry.start();
	}

	@Test
	public void test2()
	{
		System.out.println("carry是否为空："+ (carry==null) );  //false
	}
	
	@Test
	public void test1()
	{
		System.out.println(carry);
		System.out.println(carry.carService);
	}
	
	
}
