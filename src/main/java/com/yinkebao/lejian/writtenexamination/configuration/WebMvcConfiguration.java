package com.yinkebao.lejian.writtenexamination.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfiguration
 * @Description WebMvc配置
 * @Author ykb
 * @Date 2020/9/28
 */
@Configuration
@ServletComponentScan
@EnableConfigurationProperties
public class WebMvcConfiguration implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(WebMvcConfiguration.class);
	@Value("${yinkebao.fastjson.date-format:yyyy-MM-dd HH:mm:ss}")
	private String dateFormat;

	public WebMvcConfiguration() {
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("[yinkebao]CorsMappings has been initialized ");
		registry.addMapping("/**").allowedOrigins(new String[]{"*"}).allowedHeaders(new String[]{"*"}).allowedMethods(new String[]{"*"}).allowCredentials(true).maxAge(3600L);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.info("[yinkebao]MessageConverters has been initialized ");
		converters.clear();
		converters.add(this.messageBodyFastjsonConverter());
	}

	@Bean
	public StringHttpMessageConverter responseBodyStringConverter() {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		return stringConverter;
	}

	@Bean
	public FastJsonHttpMessageConverter messageBodyFastjsonConverter() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat});
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastConverter.setFastJsonConfig(fastJsonConfig);
		List<MediaType> fastMediaTypes = new ArrayList();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		return fastConverter;
	}

	@Bean
	@Primary
	DefaultErrorAttributes defaultErrorAttributes() {
		log.info("[yinkebao]CustomErrorAttributes has been initialized ");
		return new DefaultErrorAttributes();
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		return autoProxyCreator;
	}

	@Bean
	public Converter<String, LocalDateTime> LocalDateTimeConvert() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime date = null;

				try {
					date = LocalDateTime.parse(source, df);
				} catch (Exception var7) {
					WebMvcConfiguration.log.warn("convert LocalDateTime error , maybe convert LocalDate , param : {}", source);

					try {
						date = LocalDateTime.parse(source + " 00:00:00", df);
					} catch (Exception var6) {
						var6.printStackTrace();
					}
				}

				return date;
			}
		};
	}

	@Bean
	public Converter<String, LocalDate> LocalDateConvert() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = null;

				try {
					date = LocalDate.parse(source, df);
				} catch (Exception var5) {
					var5.printStackTrace();
				}

				return date;
			}
		};
	}

	@Bean
	public Converter<String, LocalTime> LocalTimeConvert() {
		return new Converter<String, LocalTime>() {
			@Override
			public LocalTime convert(String source) {
				DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalTime date = null;

				try {
					date = LocalTime.parse(source, df);
				} catch (Exception var5) {
					var5.printStackTrace();
				}

				return date;
			}
		};
	}

	@Bean
	public LocalDateTimeSerializer localDateTimeDeserializer() {
		return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	@Bean
	public LocalDateSerializer localDateDeserializer() {
		return new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	@Bean
	public LocalTimeSerializer localTimeDeserializer() {
		return new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return (builder) -> {
			builder.serializerByType(LocalDateTime.class, this.localDateTimeDeserializer());
		};
	}
}

