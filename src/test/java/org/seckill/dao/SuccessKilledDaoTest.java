package org.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	//注入Dao实现类依赖
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() throws Exception{
		long seckillId = 1000L;
		long userPhone = 13212345676L;
		int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
		System.out.println(insertCount);
		/*
		 * 结果：
		 * 第一次执行：1
		 *第二次执行：0（联合主键，所以不能重复插入）
		 */
	}

	@Test
	public void testQueryByIdWithSeckill() throws Exception{
		long seckillId = 1000L;
		long userPhone = 13212345676L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
		/*
		 * 结果：
		 * SuccessKilled [seckillId=1000, 
		 * 					userPhone=13212345676, 
		 * 						state=-1, 
		 * 							createTime=Thu May 23 15:05:47 CST 2019]
			Seckill [seckilled=0, 
						name=1000元秒杀iphone6, 
							number=100, 
								startTime=Fri Nov 01 00:00:00 CST 2019, 
									endTime=Sat Nov 02 00:00:00 CST 2019, 
										createTime=Tue May 21 11:23:43 CST 2019]
		 */
	}

}
