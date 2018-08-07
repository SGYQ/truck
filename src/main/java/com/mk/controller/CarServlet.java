package com.mk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.entry.Car;
import com.mk.service.CarService;
import com.mk.util.StringUtils;

/**
 * 
 */
public class CarServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private CarService carService;
	private ApplicationContext context;

	public void init(ServletConfig config) throws ServletException
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		carService = (CarService)context.getBean("carService");
	}
	/**
	 * 处理GET请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		switch (method) {
			case "queryCars": queryCars(request, response);
				break;
			case "queryCarById":  queryCarById(request, response);
				break;
			default: break;
		}
	}

	/**
	 * 处理POST请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		switch(method){
			case "appendCar":  appendCar(request,response);
				break;
			case "cancelCar":  cancelCar(request,response);
				break;
			case "modifyCar":  modifyCar(request,response);
				break;
			case "queryCars": doGet(request, response);
				break;
			case "queryCarById": doGet(request, response);
				break;
			default: break;
		}
	}
	
	/**
	 * 1、修改车辆信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void modifyCar(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String data = request.getParameter("data"); //获取参数
		//转化POJO
		Car car = JSONObject.parseObject(data,Car.class);
		//执行操作
		int res = carService.editCar(car);
		
		//响应发送
		PrintWriter out = response.getWriter();
		out.write( res>0 ?"编辑成功！":"编辑失败！");
		out.flush();
		out.close();
	}
	/**
	 * 2、删除车辆信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void cancelCar(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		
		String id = request.getParameter("id"); //获取参数
		
		String ids[] = id.split(",");
		int res = 0;
		for(String str: ids)
			res += carService.removeCar(str);
		String result = res==ids.length? "删除成功!":"删除失败!";
		
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
	/**
	 * 3、添加车辆信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void appendCar(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String data = request.getParameter("data"); //获取参数
		//转化POJO
		Car car = JSONObject.parseObject(data,Car.class);
		//执行添加操作
		int res  = carService.addCar(car); 
		String result = res>0?"添加成功!":"添加失败!";
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
	
	/**
	 * 4、根据车牌号获取车辆信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryCarById(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String licensenumber = request.getParameter("licensenumber");
		
		Car car = carService.findCarById(licensenumber);
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(car));
		out.flush();
		out.close();
	}
	/**
	 * 5、查询车辆信息(有无条件:车辆状态)
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryCars(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String c_status = request.getParameter("c_status");
		String index = request.getParameter("pageIndex");
		String size = request.getParameter("pageSize");
		
		int pageIndex = 0;
		int pageSize = 10;
		if( !StringUtils.isEmpty(index)&&!StringUtils.isEmpty(size) )
		{
			pageIndex = Integer.parseInt(index);
			pageSize = Integer.parseInt(size);
		}
		
		List<Car> list = 
				carService.findCars(c_status, pageIndex*pageSize, pageSize); 
		int counts  = carService.findCountOfCar(c_status);
		HashMap<String,Object> map = new HashMap<>();
		map.put("total", counts);
		map.put("data", list);
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(map));
		out.flush();
		out.close();
	}

}
