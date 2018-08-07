package com.mk.entry;

public class Car
{
    private String licensenumber; //(车牌号码)  -- 主键
    private double oilwear; //(油耗/百公里)
    private int emptyweight; //(空车重量/kg)
    private int maxload; //(最大载重/kg)    --非空
    private int loadweight; //(当前载重/kg) --非空

    private String starting_; //(出发地)	   --非空
    private String destination; //(目的地) --非空
    private String currentlocation; //(当前位置)   --非空
    private String c_status; //(状态: 闲置/营运中)  --非空

    public Car()
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

	public double getOilwear()
	{
		return oilwear;
	}

	public void setOilwear(double oilwear)
	{
		this.oilwear = oilwear;
	}

	public int getEmptyweight()
	{
		return emptyweight;
	}

	public void setEmptyweight(int emptyweight)
	{
		this.emptyweight = emptyweight;
	}

	public int getMaxload()
	{
		return maxload;
	}

	public void setMaxload(int maxload)
	{
		this.maxload = maxload;
	}

	public int getLoadweight()
	{
		return loadweight;
	}

	public void setLoadweight(int loadweight)
	{
		this.loadweight = loadweight;
	}

	public String getStarting_()
	{
		return starting_;
	}

	public void setStarting_(String starting_)
	{
		this.starting_ = starting_;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getCurrentlocation()
	{
		return currentlocation;
	}

	public void setCurrentlocation(String currentlocation)
	{
		this.currentlocation = currentlocation;
	}

	public String getC_status()
	{
		return c_status;
	}

	public void setC_status(String c_status)
	{
		this.c_status = c_status;
	}

	@Override
	public String toString()
	{
		return "Car [licensenumber=" + licensenumber + ", oilwear=" + oilwear + ", emptyweight=" + emptyweight
				+ ", maxload=" + maxload + ", loadweight=" + loadweight + ", starting_=" + starting_ + ", destination="
				+ destination + ", currentlocation=" + currentlocation + ", c_status=" + c_status + "]";
	}
    
    
}
