package com.yinkebao.lejian.writtenexamination.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.misc.Cache;

/**
 * @ClassName InitCacheInfo
 * @Description 初始化缓存数据
 * @Author ykb
 * @Date 2020/9/27
 */
@Component
public class InitCacheInfo implements CommandLineRunner {

	/**
	 * 项目启动时初始化一些缓存数据
	 */
	@Override
	public void run(String... args) throws Exception {
		Cache cache = new Cache();
		cache.put("15556574657","15556574657");
		cache.put("17308765764","17308765764");
		cache.put("15908745647","15908745647");
		cache.put("13987876567","13987876567");
	}
}
