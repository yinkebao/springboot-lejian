package com.yinkebao.lejian;

import com.yinkebao.lejian.writtenexamination.application.ApplicationServiceRegistry;
import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
import com.yinkebao.lejian.writtenexamination.util.ManacherAlgorithmUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SignInTest
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeJianTest {

	@Autowired
	ApplicationServiceRegistry applicationServiceRegistry;

	/**
	 * 手机号注册测试
	 */
	@Test
	public void register() {
		applicationServiceRegistry.getMobileApplicationService()
				.register(MobileSignInCommand.builder().mobile("15556574657").build());
	}

	/**
	 * 回文串获取测试
	 */
	@Test
	public void longestStr(){
		ManacherAlgorithmUtil.findLongestPalindromeStr("hdbghgyuioo-iuyn-bvc-cvbnwedf").forEach(
				System.out::println);
	}

}
