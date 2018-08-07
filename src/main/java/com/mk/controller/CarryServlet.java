package com.mk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.mk.service.BusinessService;
import com.mk.service.CarService;
import com.mk.service.Carry;
import com.mk.service.CarryService;
import com.mk.service.ControllMap;

/**
 * 运输控制器
 * @author 上官雅晴
 *
 */
public class CarryServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	HashMap<String,Carry> threadPool = new HashMap<>();

	ApplicationContext context;
	CarryService carryService;
	ControllMap controller;
	BusinessService businessService;
	CarService carService;
	
	public void init(ServletConfig config) throws ServletException 
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		/**
		 * 初始化
		 */
		carryService = (CarryService) context.getBean("carryService");
		controller = (ControllMap) context.getBean("controllMap");
		businessService = (BusinessService) context.getBean("businessService");
		carService = (CarService) context.getBean("carService");
		/**
		 * 启动线程
		 */
		controller.setM(threadPool);
		controller.setTemp(true);
		controller.start();
	}
	/**
	 * 销毁
	 */
	public void destroy() 
	{
		//终止线程
		controller.setTemp(false);
		//清空threadPool
		threadPool.clear();
	}

	/**
	 * 处理GET请求
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		
		switch(method) {
			case "queryLocation" : queryLocation(request, response);
				break;
			
			default: break;
		}
	}

	/**
	 * 处理POST请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String method = request.getParameter("method");
		
		switch(method) {
			case "startOff" : startOff(request, response);
				break;
			default: break;
		}
	}

	/**
	 * 1、发车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void startOff(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  
	{
		String licensenumber = request.getParameter("licensenumber");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String data = request.getParameter("points");
		/**
		 * 处理车辆与订单业务，处理坐标数据，返回线程
		 * 线程中传入两个业务实例，carService, businessService
		 */
		Carry carry = carryService.depart(licensenumber, start, end, data);
		carry.setCarService(carService);
		carry.setBusinessService(businessService);
		
		if( carry!=null ) {
			carry.start();
			threadPool.put(licensenumber, carry);
		} 
		PrintWriter out = response.getWriter();
		out.write( (carry!=null)?"已发车！":"发车失败！");
		out.flush();
		out.close();
		
	}

	/**
	 * 2、查询车辆位置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void queryLocation(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  
	{
		String licensenumber = request.getParameter("number");
		HashMap<String,Object> result = new HashMap<>();
		
		if( threadPool.containsKey(licensenumber) ) {  //营运车辆
			Carry carry = threadPool.get(licensenumber);
			result.put("start", carry.getStart() );
			result.put("end", carry.getEnd() );
			result.put("lng", carry.getLongitude());
			result.put("lat", carry.getLatitude());
			result.put("points", carry.getArrPoints());
			result.put("index", carry.getIndex());
		} else		//闲置车辆
			result = (HashMap<String, Object>) carryService.findCarLoaction(licensenumber);
		
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
	}
	
}
