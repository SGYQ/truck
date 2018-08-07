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
import com.mk.entry.Business;
import com.mk.entry.Car;
import com.mk.entry.Cargo;
import com.mk.service.BusinessService;
import com.mk.service.CarService;
import com.mk.service.CargoService;
import com.mk.util.StringUtils;

/**
 * 业务控制器
 * @author 上官雅晴
 *
 */
public class BusinessServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static BusinessService businessService;
	private static ApplicationContext context;
	private static CarService carService;
	private static CargoService cargoService;
	
	public void init(ServletConfig config) throws ServletException
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		businessService = (BusinessService) context.getBean("businessService");
		carService = (CarService) context.getBean("carService");
		cargoService = (CargoService) context.getBean("cargoService");
	}

	/**
	 * 处理GET请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		switch (method) {
			case "queryBusinessByNumber": queryBusinessByNumber(request, response);
				break;
			case "queryBusiness": queryBusiness(request, response);
				break;
			case "queryFreeCar": queryFreeCar(request, response);
				break;
			case "queryHavingCargo": queryHavingCargo(request, response);
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
		switch (method) {
			case "queryBusinessByNumber": doGet(request, response);
				break;
			case "queryBusiness": doGet(request, response);
				break;
			case "cancelBusinessById": cancelBusinessById(request,response);
				break;
			case "queryFreeCar": doGet(request, response);
				break;
			case "queryHavingCargo": doGet(request, response);
				break;	
			case "appendBusiness": appendBusiness(request, response);
				break;
			default: break;
		}
	}

	/**
	 * 1、查找库存量>0的货物信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryHavingCargo(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String index = request.getParameter("pageIndex");
		String size = request.getParameter("pageSize");
		
		int pageIndex = 0;
		int pageSize = 10;
		if( !StringUtils.isEmpty(index)&&!StringUtils.isEmpty(size) )
		{
			pageIndex = Integer.parseInt(index);
			pageSize = Integer.parseInt(size);
		}
		
		List<Cargo> list = 
				cargoService.findHavingCargo(pageIndex*pageSize,pageSize); 
		int counts  = cargoService.findCountOfHavingCargo(); //根据车牌号获取记录数目
		HashMap<String,Object> map = new HashMap<>();
		map.put("total", counts);
		map.put("data", list);
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(map));
		out.flush();
		out.close();
	}

	/**
	 * 2、查询空闲车辆(列表分页展示)
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryFreeCar(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
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
				carService.findCars("闲置", pageIndex*pageSize,pageSize); 
		int counts  = carService.findCountOfCar("闲置"); //根据车牌号获取记录数目
		HashMap<String,Object> map = new HashMap<>();
		map.put("total", counts);
		map.put("data", list);
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(map));
		out.flush();
		out.close();
	}

	
	/**
	 * 3、添加业务信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void appendBusiness(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String data = request.getParameter("data"); //获取参数
		//转化POJO
		Business business = JSONObject.parseObject(data,Business.class);
		//执行添加操作
		int res  = businessService.addBusiness(business); 
		String result = res>0 ?"添加成功!":"添加失败!";
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}

	/**
	 * 3、根据订单编号删除信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void cancelBusinessById(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String ids = request.getParameter("id"); //获取参数
		String ls[] = ids.split(","); //切割
		int res = 0;
		
		for(String str: ls)
			res += businessService.removeBusiness(str);
		String result = res==ls.length ? "删除成功!":"删除失败!";
		
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}

	/**
	 * 4、根据车牌号获取相关订单业务信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryBusinessByNumber(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String b_status = request.getParameter("b_status"); //订单状态
		String licensenumber = request.getParameter("licensenumber");
		String index = request.getParameter("pageIndex");
		String size = request.getParameter("pageSize");
		
		int pageIndex = 0;
		int pageSize = 10;
		if( !StringUtils.isEmpty(index)&&!StringUtils.isEmpty(size) )
		{
			pageIndex = Integer.parseInt(index);
			pageSize = Integer.parseInt(size);
		}
		
		List<Business> list = 
				businessService.findBusinessByNumber(licensenumber,b_status,pageIndex*pageSize,pageSize); 
		int counts  = 
				businessService.findCountOfBusinessWithNumber(licensenumber,b_status); //根据车牌号获取记录数目
		HashMap<String,Object> map = new HashMap<>();
		map.put("total", counts);
		map.put("data", list);
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(map));
		out.flush();
		out.close();
	}

	/**
	 * 5、查询所有的业务信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryBusiness(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String b_status = request.getParameter("b_status"); //订单状态
		String index = request.getParameter("pageIndex");
		String size = request.getParameter("pageSize");
		
		int pageIndex = 0;
		int pageSize = 10;
		if( !StringUtils.isEmpty(index)&&!StringUtils.isEmpty(size) )
		{
			pageIndex = Integer.parseInt(index);
			pageSize = Integer.parseInt(size);
		}
		
		List<Business> list = 
				businessService.findBusinessByNumber(null,b_status,pageIndex*pageSize,pageSize); 
		int counts  = 
				businessService.findCountOfBusinessWithNumber(null,b_status); //获取记录数目
		
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
