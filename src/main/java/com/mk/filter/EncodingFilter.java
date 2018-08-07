package com.mk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		//响应编码
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//处理POST请求
		request.setCharacterEncoding("utf-8");
		
		//处理GET请求
		HttpServletRequest req = (HttpServletRequest) request;
		if( req.getMethod().equals("GET") ) {
			//装饰类处理
			EncodingRequest er = new EncodingRequest(req);
			chain.doFilter(er, response);
		} else if( req.getMethod().equals("POST") ) {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException
	{
	}

	public void destroy()
	{
	}

}
