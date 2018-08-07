package com.mk.service;

import java.util.Arrays;
import org.springframework.transaction.annotation.Transactional;

import com.mk.entry.Car;
import com.mk.entry.Point;

/**
 * 业务层：运输处理
 * @author 上官雅晴
 *
 */
public class Carry extends Thread
{
	CarService carService;
	BusinessService businessService;
	
	private String licensenumber="";
	private Point location; //当前位置坐标
	private String start; //起点
	private String end; //终点
	private double[][] arrPoints;
	private double longitude;  //当前坐标的经度
	private double latitude;  //当前坐标的纬度
	private int index; //在路线arrPoints数组中 arrPoints[]的索引
	
	public Carry()
	{
	}

	public CarService getCarService()
	{
		return carService;
	}

	public void setCarService(CarService carService)
	{
		this.carService = carService;
	}

	public BusinessService getBusinessService()
	{
		return businessService;
	}

	public void setBusinessService(BusinessService businessService)
	{
		this.businessService = businessService;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public String getLicensenumber()
	{
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber)
	{
		this.licensenumber = licensenumber;
	}

	public Point getLocation()
	{
		return location;
	}

	public void setLocation(Point location)
	{
		this.location = location;
	}

	public String getStart()
	{
		return start;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getEnd()
	{
		return end;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

	public double[][] getArrPoints()
	{
		return arrPoints;
	}

	public void setArrPoints(double[][] arrPoints)
	{
		this.arrPoints = arrPoints;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		if(index<0)
			this.index = 0;
		this.index = index;
	}

	@Override
	public String toString()
	{
		return "Carry [licensenumber=" + licensenumber + ", location=" + location + ", start=" + start + ", end=" + end
				+ ", arrPoints=" + Arrays.toString(arrPoints) + ", index=" + index + "]";
	}

	@Override
	public void run()
	{
		boolean exit = false;
		while(!exit)
		{
			try {
				sleep(2110); //2110ms = 2.11s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.longitude = arrPoints[index][0]; //设置经度
			this.latitude = arrPoints[index][1];  //设置纬度
			
			if( index>=arrPoints.length-1 )
				exit = true;
			index++;
		}
		index--;
		//到达后更新车辆的状态、当前载重，更新订单状态
		reach(licensenumber);
		System.out.println("执行完毕...");
	}
	
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
		 * 2、修改订单状态，从运输中变为送达
		 */
		businessService.editBusinessStatus(licensenumber, "送达");
	}
}
