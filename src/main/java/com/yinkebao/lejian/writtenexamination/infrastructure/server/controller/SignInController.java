package com.yinkebao.lejian.writtenexamination.infrastructure.server.controller;

import com.yinkebao.lejian.writtenexamination.application.ApplicationServiceRegistry;
import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SignInController
 * @Description 手机号注册入口
 * @Author ykb
 * @Date 2020/9/27
 */
@RestController
@RequestMapping("/signInManage")
public class SignInController {

	@Resource
	ApplicationServiceRegistry applicationServiceRegistry;

	/**
	 * 注册手机号
	 *
	 * @param command 注册命令
	 */
	@PostMapping("/register")
	public void signIn(@RequestBody MobileSignInCommand command){
		applicationServiceRegistry.getLeJanApplicationService().register(command);
	}

}
