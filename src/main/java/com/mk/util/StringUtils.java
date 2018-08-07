package com.mk.util;
/**
 * 字符串工具类
 * @Description
 * @author hanyu
 * @Date 2018年4月16日 下午4:33:38
 * @version
 */
public class StringUtils
{
	/**
	 * 验证传入的字符串是否为空
	 * @param str
	 * @return 是，返回true；否，返回false
	 */
	public static boolean isEmpty(String str)
	{
		if( "".equals(str)||str==null )
			return true;
		return false;
	}
}
