package com.mk.mapper;

import java.util.List;

import com.mk.entry.Cargo;

/**
 * 货物信息管理接口
 * 
 * @Description
 * @author hanyu
 * @Date 2018年4月14日 下午4:42:32
 * @version
 */
public interface CargoMapper
{
	/**
	 * 1、查询货物表中所有记录
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Cargo> selectCargos(int index, int size);
	/**
	 * 2、返回表中记录总数
	 * @return
	 */
	public int selectCountOfCargo();
	/**
	 * 3、根据编号查询货物信息
	 * @param id
	 * @return
	 */
	public Cargo selectCargoById(int id);
	/**
	 * 4、添加货物
	 * @param cargo
	 */
	public int insertCargo(Cargo cargo);
	/**
	 * 5、更新货物信息
	 * @param cargo
	 */
	public int updateCargo(Cargo cargo);
	
	/**  业务相关  **/
	/**
	 * 6、查询表中库存量>0的所有货物信息
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Cargo> selectHavingCargo(int index, int size);
	/**
	 * 7、查询表中库存量>0的货物信息的数目
	 * @return
	 */
	public int selectCountOfHavingCargo();
	/**
	 * 8、修改当前该货物的库存量(减,传入负值; 加,传入正值)
	 */
	public int updateCargoStore(int amount, int c_id);
	/**
	 * 9、根据货物编号查找该货物的重量
	 */
	public int selectWeightById(int c_id);
	
}
