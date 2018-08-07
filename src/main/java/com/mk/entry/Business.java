package com.mk.entry;

import java.util.Date;

/**
 * 业务/订单实体
 * @Description
 * @author hanyu
 * @Date 2018年4月14日 下午4:29:52
 * @version
 */
public class Business
{
    private String b_id; //(业务编号) --主键
    private String licensenumber; //(车牌号码) --非空
    private int c_id;   //(货物编号)			--非空
    private int amount; //(货物数量)			--非空，默认为1
    private Date b_date;//(生成日期)				--非空,默认当天
    private String b_status; //(订单状态: 待发货/运输中/送达 )  --非空
    private String remark; //(备注)

    public Business()
	{
	}

	public String getB_id()
	{
		return b_id;
	}

	public void setB_id(String b_id)
	{
		this.b_id = b_id;
	}

	public String getLicensenumber()
	{
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber)
	{
		this.licensenumber = licensenumber;
	}

	public int getC_id()
	{
		return c_id;
	}

	public void setC_id(int c_id)
	{
		this.c_id = c_id;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public Date getB_date()
	{
		return b_date;
	}

	public void setB_date(Date b_date)
	{
		this.b_date = b_date;
	}

	public String getB_status()
	{
		return b_status;
	}

	public void setB_status(String b_status)
	{
		this.b_status = b_status;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String toString()
	{
		return "Business [b_id=" + b_id + ", licensenumber=" + licensenumber + ", c_id=" + c_id + ", amount=" + amount
				+ ", b_date=" + b_date + ", b_status=" + b_status + ", remark=" + remark + "]";
	}
    
    
}
