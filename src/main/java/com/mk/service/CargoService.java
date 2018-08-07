package com.mk.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mk.entry.Cargo;
import com.mk.mapper.CargoMapper;

/**
 * 业务层：货物信息管理调用
 * @author 上官雅晴
 *
 */
@Service
public class CargoService
{
	@Resource
	private CargoMapper cargoMapper;
	
	/**
	 * 1、查询货物表中所有记录
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Cargo> findCargos(int index, int size)
	{
		List<Cargo> list = cargoMapper.selectCargos(index, size);
		return list;
	}
	
	/**
	 * 2、返回表中记录总数
	 * @return
	 */
	public int findCountOfCargo()
	{
		return cargoMapper.selectCountOfCargo();
	}

	/**
	 * 3、根据编号查询货物信息
	 * @param id
	 * @return
	 */
	public Cargo findCargoById(int id)
	{
		Cargo cargo = cargoMapper.selectCargoById(id);
		return cargo;
	}
	
	/**
	 * 4、查询表中库存量>0的所有货物信息
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Cargo> findHavingCargo(int index, int size)
	{
		List<Cargo> list = cargoMapper.selectHavingCargo(index, size);
		return list;
	}

	/**
	 * 5、查询表中库存量>0的货物信息的数目
	 * @return
	 */
	public int findCountOfHavingCargo()
	{
		return cargoMapper.selectCountOfHavingCargo();
	}

	/**
	 * 6、添加货物
	 * @param cargo
	 * @return
	 */
	public int addCargo(Cargo cargo)
	{
		return cargoMapper.insertCargo(cargo);
	}
	
	/**
	 * 7、更新货物信息
	 * @param cargo
	 */
	public int editCargo(Cargo cargo)
	{
		return cargoMapper.updateCargo(cargo);
	}
	
	/**
	 * 8、修改当前该货物的库存量(减,传入负值; 加,传入正值)
	 */
	public int editCargoStore(int amount, int c_id)
	{
		return cargoMapper.updateCargoStore(amount, c_id);
	}
	
	/**
	 * 9、根据货物编号查找该货物的重量
	 */
	public int findWeightById(int c_id)
	{
		return cargoMapper.selectWeightById(c_id);
	}
	
}
