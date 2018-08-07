package com.mk.mapper;

import java.util.List;

import com.mk.entry.Car;

/**
 * 车辆信息管理接口
 * @Description
 * @author hanyu
 * @Date 2018年4月14日 下午4:35:13
 * @version
 */

public interface CarMapper
{
	/**
	 * 1、根据车辆状态查询,信息列表
	 * @param status
	 * @param index
	 * @param size
	 */
	public List<Car> selectCars(String status, int index, int size);
	
	/**
	 * 2、根据车牌号精准查询
	 * @param id
	 */
	public Car selectCarById(String id); 
	
	/**
	 * 3、返回表中记录总数
	 * @return
	 */
	public int selectCountOfCars();
	/**
	 * 4、根据状态查询车辆数目
	 * @return
	 */
	public int selectCountByStatus(String status);
	/**
	 * 5、添加车辆信息
	 * @param car
	 */
	public Integer insertCar(Car car);
	/**
	 * 6、根据车牌号删除信息
	 * @param id 车牌号
	 */
	public int deleteCar(String id);
	/**
	 * 7、更新车辆信息
	 * @param car
	 */
	public int updateCar(Car car);
	
	/**
	 * 8、根据车牌号更改车辆状态
	 * @param licensenumber
	 * @return
	 */
	public int updateCarStatusById(String licensenumber, String status);
	/**
	 * 9、根据车牌号修改车辆的当前载重
	 * 	将当前车辆的变化量直接设置为weight
	 * @param licensenumber 车牌号码
	 * @param weight 重量目标值
	 * @return int
	 */
	public int updateCarLoadweightById(String licensenumber, int weight);
	/**
	 * 10、以变化量的形式更新当前车辆的载重
	 * @param licensenumber 车牌号
	 * @param weightValue 重量的变化量
	 * @return
	 */
	public int updateCarLoadweightValue(String licensenumber, int weightValue);
}
