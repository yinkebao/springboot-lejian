package com.yinkebao.lejian.writtenexamination.configuration.runner;

import com.yinkebao.lejian.writtenexamination.constant.ExceptionConstant;
import com.yinkebao.lejian.writtenexamination.domain.model.error.ErrorInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExceptionRunner
 * @Description 加载配置文件的自定义异常信息到集合
 * @Author ykb
 * @Date 2020/9/28
 */
@Component
@Order(1)
public class ExceptionRunner implements ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(ExceptionRunner.class);

	public ExceptionRunner() {
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource("classpath:./exception.properties");
		Properties properties = new Properties();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
			properties.load(bf);
			properties.forEach((o1, o2) -> {
				String errorName = o1.toString();
				String errorInfoStr = o2.toString();
				String[] strs = errorInfoStr.split(",");
				ErrorInfo errorInfo = new ErrorInfo(strs[0], strs[1]);
				ExceptionConstant.ERROR_INFO_STORE.put(errorName, errorInfo);
			});
		} catch (IOException var6) {
			log.error("加载异常信息配置文件 exception.properties 失败", var6);
		}
		ExceptionConstant.ERROR_INFO_STORE.put(null, new ErrorInfo((String)null, (String)null));
	}
}
