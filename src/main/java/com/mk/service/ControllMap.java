package com.mk.service;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ControllMap extends Thread
{
	Map<String, Carry> m;
	boolean temp = true;
	
	public boolean isTemp()
	{
		return temp;
	}
	public void setTemp(boolean temp)
	{
		this.temp = temp;
	}

	/**
	 * 构造器
	 */
	public ControllMap()
	{
	}
	
	public Map<String, Carry> getM()
	{
		return m;
	}
	public void setM(Map<String, Carry> m)
	{
		this.m = m;
	}

	@Override
	public void run()
	{
		while(temp)
		{
			try {
				sleep(2_000); //2s一次运行
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
			
			String licensenumber = null;
			Carry carry = null;
			Iterator<String> iter = m.keySet().iterator();
			while (iter.hasNext()) 
			{
				licensenumber = (String)iter.next();
				carry = (Carry)m.get(licensenumber);
				
				//判断.执行完的线程清除
				if( !carry.isAlive() )
					m.remove(licensenumber);
			}
		}
	}
	
	
	
}
