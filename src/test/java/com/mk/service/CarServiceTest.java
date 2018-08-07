package com.mk.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mk.entry.Car;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CarServiceTest
{
	@Autowired
	private CarService carService;
	
	@Before
	public void setUp() throws Exception
	{
	}

	
	@Test
	public void testFindCars()  //测试成功
	{
		List<Car> list = carService.findCars(null, 0, 5);
		for(Car obj: list)
			System.out.println(obj.getLicensenumber() );
		System.out.println(list.size());
	}

	@Test
	public void testFindCarById()
	{
		Car car = carService.findCarById("DDD");
		System.out.println(car);
	}

	@Test
	public void testFindCountOfCar()
	{
		int nullValue = carService.findCountOfCar(null);
		int freeValue =  carService.findCountOfCar("闲置");
		int busyValue = carService.findCountOfCar("营运");
		
		System.out.println(nullValue);
		System.out.println(freeValue);
		System.out.println(busyValue);
	}

	@Test
	public void testAddCar()
	{
		Car car = new Car();
		car.setLicensenumber("卡车--8");
		car.setOilwear(23.5);
		car.setEmptyweight(3490);
		car.setMaxload(2000);
		car.setStarting_("无锡");
		car.setDestination("常州");
		
		int num = carService.addCar(car);
		
		System.out.println(num);
	}

	/**
	 * 测试删除，根据车牌号删除车辆
	 * 正：1；反：0
	 */
	@Test
	public void testRemoveCar()
	{
		int num = carService.removeCar("卡车--8");
		System.out.println(num);
	}

	/**
	 * 测试通过
	 */
	@Test
	public void testEditCar()
	{
		Car car = carService.findCarById("卡车--8");
		System.out.println(car);
		
		car.setMaxload(5000);
		car.setOilwear(40);
		int num = carService.editCar(car);
		System.out.println(num);
	}

	@Test
	public void testEditCarStatusById()
	{
		int num = carService.editCarStatusById("卡车--8", "闲置");
		System.out.println(num);
	}

	@Test
	public void testEditCarLoadweightById()
	{
		int num = carService.editCarLoadweightById("卡车--3", 0);
		System.out.println(num);
	}

	/**
	 * 车辆当前载重 增加某个变化值
	 */
	@Test
	public void testEditCarLoadweightValue()
	{
		int num = carService.editCarLoadweightValue("卡车--2", -15);
		System.out.println(num);
	}

	/**
	 * 车辆送达 目的地后
	 * 车辆状态更改为“闲置”，车辆的当前载重为0;
	 * 出发地为原目的地，目的地为原出发地，当前位置为原目的地
	 */
	@Test
	public void testReach()
	{
		Car car = carService.findCarById("BAT");
		System.out.println(car);
		
		car.setC_status("闲置");
		car.setLoadweight(0);
		String start = car.getStarting_();  //原出发地
		String end = car.getDestination();  //原目的地
		car.setStarting_(end);
		car.setDestination(start);
		car.setCurrentlocation(end);
		
		int num = carService.editCar(car);
		System.out.println(num);
	}
	
	/**
	 * 双层调用
	 * : 通过
	 */
	@Test
	public void testTwo()
	{
		int num = carService.editCarStatusById("BAT", "营运");
		num += carService.editCarLoadweightById("BAT", 34);
		
		System.out.println(num);
	}
	
	
}
