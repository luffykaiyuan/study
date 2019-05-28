package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml", 
										"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() throws Exception{
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetById() throws Exception{
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	//测试代码完整逻辑，注意可以重复执行
	@Test
	public void tesSeckillLogic() throws Exception{
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if (exposer.isExposed()) {
			logger.info("exposer={}", exposer);
			long phone = 11111111111L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
				logger.info("result={}", execution);
			} catch (SeckillCloseException e1) {
				logger.error(e1.getMessage());
			} catch (RepeatKillException e2) {
				logger.error(e2.getMessage());
			}
		} else {
			//秒杀未开启
			logger.warn("exposer={}", exposer);
		}
		
		/*
		 * 结果：
		 * result=SeckillExecution [seckillId=1000, state=1, stateInfo=秒杀成功, successKilled=SuccessKilled 
		 * 								[seckillId=1000, userPhone=11111111111, state=-1, createTime=Fri May 24 20:39:25 CST 2019]]
		 */
	}

}
