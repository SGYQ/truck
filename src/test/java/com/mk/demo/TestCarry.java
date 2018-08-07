package com.mk.demo;

import org.junit.Test;

import com.mk.entry.Point;
import com.mk.service.Carry;

public class TestCarry
{
	@Test
	public void test1()
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
	
	/**
	 * 存在空指针
	 * @param args
	 */
	public static void main(String[] args)
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
}
