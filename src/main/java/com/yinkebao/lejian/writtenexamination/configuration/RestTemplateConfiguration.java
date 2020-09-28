package com.yinkebao.lejian.writtenexamination.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateConfiguration
 * @Description restTemplate配置
 * @Author ykb
 * @Date 2020/9/27
 */
@Configuration
public class RestTemplateConfiguration {
	private static final Logger log = LoggerFactory.getLogger(RestTemplateConfiguration.class);

	public RestTemplateConfiguration() {
	}

	@Bean(
			name = {"restTemplate"}
	)
	@LoadBalanced
	public RestTemplate restTemplate() {
		log.info("[yinkebao]RestTemplate has been initialized ");
		return new RestTemplate();
	}
}
