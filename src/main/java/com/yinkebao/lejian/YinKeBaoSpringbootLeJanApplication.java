package com.yinkebao.lejian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName YinKeBaoSpringbootLeJanApplication
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@ComponentScan(basePackages = "com.yinkebao.lejian.**")
@ServletComponentScan(basePackages = "com.yinkebao.lejian.**")
@EnableTransactionManagement
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},scanBasePackages = "com.yinkebao.lejian.**")
public class YinKeBaoSpringbootLeJanApplication {

  public static void main(String[] args) {
    SpringApplication.run(YinKeBaoSpringbootLeJanApplication.class, args);
  }
}
