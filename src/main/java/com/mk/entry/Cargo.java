package com.mk.entry;
/**
 * 货物实体
 * @Description
 * @author hanyu
 * @Date 2018年4月14日 下午4:27:43
 * @version
 */
public class Cargo
{
    private int c_id; //(货物编号)   --主键
    private String c_name; //(货物名称)
    private int c_weight; //(货物重量/kg)
    private int c_store;  //(库存量)  --默认 0

    public Cargo()
	{
	}

	public int getC_id()
	{
		return c_id;
	}

	public void setC_id(int c_id)
	{
		this.c_id = c_id;
	}

	public String getC_name()
	{
		return c_name;
	}

	public void setC_name(String c_name)
	{
		this.c_name = c_name;
	}

	public int getC_weight()
	{
		return c_weight;
	}

	public void setC_weight(int c_weight)
	{
		this.c_weight = c_weight;
	}

	public int getC_store()
	{
		return c_store;
	}

	public void setC_store(int c_store)
	{
		this.c_store = c_store;
	}

	@Override
	public String toString()
	{
		return "Cargo [c_id=" + c_id + ", c_name=" + c_name + ", c_weight=" + c_weight + ", c_store=" + c_store + "]";
	}
    
}
