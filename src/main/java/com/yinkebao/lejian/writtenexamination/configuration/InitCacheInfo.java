package com.yinkebao.lejian.writtenexamination.configuration;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitCacheInfo
 * @Description 初始化缓存数据
 * @Author ykb
 * @Date 2020/9/27
 */
@Component
public class InitCacheInfo implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 项目启动时初始化一些缓存数据
	 */
	@Override
	public void run(String... args) throws Exception {
		redisTemplate.opsForValue().set("15556574657","15556574657");
		redisTemplate.opsForValue().set("17308765764","17308765764");
		redisTemplate.opsForValue().set("15908745647","15908745647");
		redisTemplate.opsForValue().set("13987876567","13987876567");
		logger.info("[yinkebao]初始化数据完成！");
	}
}
