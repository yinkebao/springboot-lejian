package com.yinkebao.lejian.writtenexamination.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description swagger配置
 * @Author ykb
 * @Date 2020/9/28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("LeJian API Doc")
				.description("This is a restful api document of LeJian Test.")
				.version("1.0")
				.build();
	}

}
