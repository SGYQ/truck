package com.mk.entry;
/**
 * 点坐标(包含经纬度)
 * @Description
 * @author hanyu
 * @Date 2018年5月9日 下午3:11:58
 * @version
 */
public class Point
{
	private String licensenumber;
	private double longtiude;
	private double latitude;

	public Point()
	{
	}

	public String getLicensenumber()
	{
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber)
	{
		this.licensenumber = licensenumber;
	}

	public double getLongtiude()
	{
		return longtiude;
	}

	public void setLongtiude(double longtiude)
	{
		this.longtiude = longtiude;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	@Override
	public String toString()
	{
		return "Point [licensenumber=" + licensenumber + ", longtiude=" + longtiude + ", latitude=" + latitude + "]";
	}

	
}
