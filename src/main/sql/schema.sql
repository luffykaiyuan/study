--数据库初始化脚本

--创建数据库
CREATE database seckill;
--使用数据库
use seckill;
--创建数据库表
CREATE table seckill(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
	name varchar(120) NOT NULL COMMENT '商品名称',
	number int NOT NULL COMMENT '库存数量',
	start_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开始时间',
	end_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--初始化数据
insert into
	seckill(name,number,start_time,end_time)
value
	('1000元秒杀iphone6',100,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
	('500元秒杀iPad Air2',200,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
	('300元秒杀小米',300,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
	('200元秒杀红米',400,'2019-11-01 00:00:00','2019-11-02 00:00:00');
	
--秒杀成功明细
--用户登录认证相关信息
CREATE table success_killed(
	seckill_id bigint NOT NULL COMMENT '秒杀商品ID',
	user_phone bigint NOT NULL COMMENT '用户手机号',
	state tinyint NOT NULL DEFAULT -1 COMMENT '状态标示：-1：无效 0：成功1：已付款2：已发货 ',
	create_time timestamp NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';
	
--连接数据库控制台
mysql -uroot -p
