package com.mk.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CarryServiceTest
{
	@Autowired
	CarryService carryService;
	
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testReach()
	{
		carryService.reach("DDD");
	}

	@Test
	public void testDepart()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindCarLoaction()
	{
		fail("Not yet implemented");
	}

}
