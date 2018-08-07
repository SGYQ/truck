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
import com.mk.entry.Cargo;
import com.mk.service.CargoService;
import com.mk.util.StringUtils;

/**
 */
public class CargoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private CargoService cargoService;
	private ApplicationContext context;

	/**
	 * 初始化
	 */
	public void init(ServletConfig config) throws ServletException
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		cargoService = (CargoService)context.getBean("cargoService");
	}

	/**
	 * 处理GET请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		switch (method) {
		case "queryCargos":
			queryCargos(request, response);
			break;
		case "queryCargoById":
			queryCargoById(request, response);
			break;
		default:
			break;
		}
	}
	/**
	 * 处理POST请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		switch(method){
			case "appendCargo":  appendCargo(request,response);
				break;
			case "modifyCargo":  modifyCargo(request,response);
				break;
			case "queryCargos": doGet(request, response);
				break;
			case "queryCargoById": doGet(request, response);
				break;
			default: break;
		}
	}
	
	
	/**
	 * 1、根据编号查询货物信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryCargoById(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String c_id = request.getParameter("c_id");
		
		Cargo cargo = cargoService.findCargoById(Integer.parseInt(c_id));
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(cargo));
		out.flush();
		out.close();
	}
	/**
	 * 2、查询表中所有记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void queryCargos(HttpServletRequest request, HttpServletResponse response) 
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
		
		List<Cargo> list = cargoService.findCargos(pageIndex*pageSize, pageSize); 
		int counts = cargoService.findCountOfCargo();
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
	 * 3、修改信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void modifyCargo(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String data = request.getParameter("data"); //获取参数
		//转化POJO
		Cargo cargo = JSONObject.parseObject(data,Cargo.class);
		//执行操作
		int res = cargoService.editCargo(cargo);
		
		//响应发送
		PrintWriter out = response.getWriter();
		out.write( res>0 ?"编辑成功":"编辑失败" );
		out.flush();
		out.close();
	}
	/**
	 * 4、添加货物信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void appendCargo(HttpServletRequest request, HttpServletResponse response) 
			throws IOException
	{
		String data = request.getParameter("data"); //获取参数
		//转化POJO
		Cargo cargo = JSONObject.parseObject(data,Cargo.class);
		//执行添加操作
		int res  = cargoService.addCargo(cargo); 
		String result = res>0?"添加成功!":"添加失败!";
		//响应发送
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
	
	
}
