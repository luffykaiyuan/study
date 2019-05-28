package org.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	//注入Dao实现类依赖
	@Resource
	private SeckillDao seckillDao;

	/**
	 * @throws Exception
	 */
	@Test
	public void testQueryById() throws Exception{
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
		/*
		 * 结果：
		 * 1000元秒杀iphone6
		 * Seckill [seckilled=0,
		 * name=1000元秒杀iphone6, 
		 * number=100, 
		 * startTime=Fri Nov 01 00:00:00 CST 2019, 
		 * endTime=Sat Nov 02 00:00:00 CST 2019, 
		 * createTime=Tue May 21 11:23:43 CST 2019]
		 */
	}

	@Test
	public void testQueryAll() throws Exception{
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}
		/**
		 * 结果：
		 * Seckill [seckilled=1000, name=1000元秒杀iphone6, number=100, startTime=Fri Nov 01 00:00:00 CST 2019, endTime=Sat Nov 02 00:00:00 CST 2019, createTime=Tue May 21 11:23:43 CST 2019]
		 * Seckill [seckilled=1001, name=500元秒杀iPad Air2, number=200, startTime=Fri Nov 01 00:00:00 CST 2019, endTime=Sat Nov 02 00:00:00 CST 2019, createTime=Tue May 21 11:23:43 CST 2019]
		 * Seckill [seckilled=1002, name=300元秒杀小米, number=300, startTime=Fri Nov 01 00:00:00 CST 2019, endTime=Sat Nov 02 00:00:00 CST 2019, createTime=Tue May 21 11:23:43 CST 2019]
		 * Seckill [seckilled=1003, name=200元秒杀红米, number=400, startTime=Fri Nov 01 00:00:00 CST 2019, endTime=Sat Nov 02 00:00:00 CST 2019, createTime=Tue May 21 11:23:43 CST 2019]
		 */
	}
	
	@Test
	public void testReduceNumber() throws Exception{
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println(updateCount);
		/*
		 * 结果：
		 * 0
		 */
	}
}
