package com.mk.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mk.entry.Car;
import com.mk.mapper.CarMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMapper
{
	@Autowired
	CarMapper carMapper;
	
	@Test
	public void testInsertCar()
	{
		Car car = new Car();
		car.setLicensenumber("卡车--8");
		car.setOilwear(23.5);
		car.setEmptyweight(3490);
		car.setMaxload(2000);
		car.setStarting_("苏州");
		car.setDestination("常州");
		car.setC_status("闲置");
		car.setLoadweight(0);
		//当前位置默认，强制为出发地
		car.setCurrentlocation(car.getStarting_());
		
		Integer num = carMapper.insertCar(car);
		System.out.println(num);
	}
}
