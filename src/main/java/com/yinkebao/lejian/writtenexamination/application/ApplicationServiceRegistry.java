package com.yinkebao.lejian.writtenexamination.application;

import com.yinkebao.lejian.writtenexamination.application.applicationservice.MobileApplicationService;
import com.yinkebao.lejian.writtenexamination.application.applicationservice.PalindromeStrApplicationService;
import javax.annotation.Resource;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationServiceRegistry
 * @Description applicationService 工厂类
 * @Author ykb
 * @Date 2020/9/27
 */
@Getter
@Component
public class ApplicationServiceRegistry {

	@Resource
	private MobileApplicationService mobileApplicationService;

	@Resource
	private PalindromeStrApplicationService palindromeStrApplicationService;

}
