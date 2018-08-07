package com.mk.mapper;

import java.util.List;

import com.mk.entry.Business;


/**
 * 业务信息管理接口
 * 
 * @Description
 * @author hanyu
 * @Date 2018年4月14日 下午4:50:37
 * @version
 */
public interface BusinessMapper
{
	/**
	 * 1、根据车牌号、车牌号查询
	 * @param licensenumber
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Business> selectBusinessByNumber(String licensenumber, String b_status, int index, int size);

	/**
	 * 2、根据车牌号查询到的记录总数
	 * @param licensenumber
	 * @return
	 */
	public int selectCountOfBusinessWithNumber(String licensenumber, String b_status);

	/**
	 * 3、添加业务信息
	 * 
	 * @param business
	 */
	public int insertBusiness(Business business);

	/**
	 * 4、删除信息
	 * 
	 * @param b_id
	 */
	public int deleteBusiness(String b_id);

	/**
	 * 5、返回业务订单信息列表
	 */
	public List<Business> selectBusinessList(int index, int size);
	
	/**
	 * 6、通过订单编号获取订单信息
	 * @param id
	 * @return
	 */
	public Business selectBusinessById(String id);
	
	/**
	 * 7、根据车牌号修改订单状态
	 * @param licensenumber
	 * @param b_status
	 */
	public int updateBusinessStatus(String licensenumber, String b_status);
	
	/**
	 * 8、根据车牌号将订单从一种状态更改为另一种状态
	 * @param licensenumber
	 * @param beforeStatus
	 * @param afterStatus
	 * @return
	 */
	public int updateBusinessStatusOneToAnother(String licensenumber, String beforeStatus, String afterStatus);
	
}
