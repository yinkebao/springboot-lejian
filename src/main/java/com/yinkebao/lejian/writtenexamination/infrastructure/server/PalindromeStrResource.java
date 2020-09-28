package com.yinkebao.lejian.writtenexamination.infrastructure.server;

import com.yinkebao.lejian.writtenexamination.application.ApplicationServiceRegistry;
import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
import com.yinkebao.lejian.writtenexamination.application.command.StringCommand;
import com.yinkebao.lejian.writtenexamination.application.responseresult.ResponseResult;
import com.yinkebao.lejian.writtenexamination.application.responseresult.ResponseResultFactory;
import com.yinkebao.lejian.writtenexamination.infrastructure.server.vo.SimpleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PalindromeStrResource
 * @Description
 * @Author ykb
 * @Date 2020/9/28
 */
@RestController
@Api(tags = "回文串查询api")
@RequestMapping("/palindromeStrResource")
public class PalindromeStrResource {

	@Resource
	ApplicationServiceRegistry applicationServiceRegistry;

	/**
	 * 注册手机号
	 *
	 * @param command 注册命令
	 */
	@PostMapping("/find")
	@ApiOperation(value = "回文串查询")
	public ResponseResult<SimpleVo<List<String>>> register(@RequestBody StringCommand command) {
		return ResponseResultFactory.successResult(new SimpleVo<>(
				applicationServiceRegistry.getPalindromeStrApplicationService()
						.findLongestPalindromeStr(command)));
	}

}
