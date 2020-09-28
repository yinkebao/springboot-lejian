package com.yinkebao.lejian.writtenexamination.infrastructure.server.controller;

import com.yinkebao.lejian.writtenexamination.application.ApplicationServiceRegistry;
import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SignInController
 * @Description MobileController
 * @Author ykb
 * @Date 2020/9/27
 */
@RestController
@Api(tags = "手机信息管理api")
@RequestMapping("/mobileManage")
public class MobileController {

	@Resource
	ApplicationServiceRegistry applicationServiceRegistry;

	/**
	 * 注册手机号
	 *
	 * @param command 注册命令
	 */
	@PostMapping("/register")
	@ApiOperation(value = "注册手机号")
	public void register(@RequestBody MobileSignInCommand command){
		applicationServiceRegistry.getLeJanApplicationService().register(command);
	}

}
