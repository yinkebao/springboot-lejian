package com.tianque.grid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName GridCloudSpringbootGridManageApplication
 * @Description
 * @Author ykb
 * @Date 2020/2/6
 */
@ServletComponentScan
@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.tianque.grid.**")
@MapperScan("com.tianque.grid.workdiary.domain.model")
@EnableTransactionManagement
public class GridCloudSpringbootWorkDiaryApplication {

  public static void main(String[] args) {
    SpringApplication.run(GridCloudSpringbootWorkDiaryApplication.class, args);
  }
}
