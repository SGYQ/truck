package com.mk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mk.entry.Car;
import com.mk.mapper.CarMapper;

/**
 * 业务层：车辆信息管理调用
 * @author 上官雅晴
 *
 */
@Scope("prototype")
@Service
public class CarService
{
	@Resource
	private CarMapper carMapper;

	/**
	 * 1、根据车辆状态查询,列表展示(有/无车辆状态)
	 * @param status
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Car> findCars(String status, int index, int size)
	{
		List<Car> list = carMapper.selectCars(status, index, size);
		return list;
	}

	/**
	 * 2、根据车牌号精准查询
	 * @param licensenumber
	 * @return
	 */
	public Car findCarById(String licensenumber)
	{
		Car car = carMapper.selectCarById(licensenumber);
		return car;
	}

	/**
	 * 3、查询表中记录总数(有/无状态)---待议
	 * @param status
	 * @return
	 */
	public int findCountOfCar(String status)
	{
		if( status==null || "".equals(status) )
			return carMapper.selectCountOfCars();
		return carMapper.selectCountByStatus(status);
	}

	/**
	 * 4、添加车辆信息，状态默认: 闲置; loadweight: 0; 
	 * 状态强制为"闲置", loadweight强制=0;
	 * 当前位置默认，强制为出发地
	 */
	public int addCar(Car car)
	{
		car.setC_status("闲置");
		car.setLoadweight(0);
		//当前位置默认，强制为出发地
		car.setCurrentlocation(car.getStarting_());
		//执行插入操作
		return carMapper.insertCar(car);
	}

	/**
	 * 5、删除车辆信息
	 * 只能删除闲置车辆信息
	 */
	public int removeCar(String id)
	{
		return carMapper.deleteCar(id);
	}

	/**
	 * 6、编辑车辆信息
	 */
	public int editCar(Car car)
	{
		return carMapper.updateCar(car);
	}
	
	/**
	 * 7、根据车牌号修改车辆状态
	 * @param licensenumber
	 * @param status
	 * @return
	 */
	public int editCarStatusById(String licensenumber, String status)
	{
		return carMapper.updateCarStatusById(licensenumber, status);
	}
	
	/**
	 * 8、根据车牌号修改车辆的当前载重,直接将当前载重修改为目标值weight
	 * @param licensenumber
	 * @param weight
	 * @return
	 */
	public int editCarLoadweightById(String licensenumber, int weight)
	{
		return carMapper.updateCarLoadweightById(licensenumber, weight);
	}
	
	/**
	 * 9、以变化量的形式更新当前车辆的载重
	 * @param licensenumber
	 * @param weightValue
	 * @return
	 */
	public int editCarLoadweightValue(String licensenumber, int weightValue)
	{
		return carMapper.updateCarLoadweightValue(licensenumber, weightValue);
	}
	
}
