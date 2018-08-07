package com.mk.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 装饰类
 * @author hanyu
 *
 */
public class EncodingRequest extends HttpServletRequestWrapper
{
	private HttpServletRequest req;
	//构造器
	public EncodingRequest(HttpServletRequest request)
	{
		super(request);
		this.req  = request;
	}
	
	/**
	 * 处理编码
	 */
	public String getParameter(String name)
	{
		String value = req.getParameter(name);
		
		try {
			value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		return value;
	}
}
