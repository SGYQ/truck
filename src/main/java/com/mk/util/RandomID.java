package com.mk.util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 产生编号 
 * 编号总共12位，由字母B+年月日+数字编号组成（如B20170711 1），
 * 必须唯一，在新增时自动生成，数字编号自动增长
 * @author hanyu
 */
public  class RandomID
{
	
	public synchronized static String getID()
	{
		//yyyy-MM-dd HH:mm:ss:SS
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		String str = sd.format(new Date());
		//拼接
		String result = "B"+str+" ";
		
		return result;
	}
}
