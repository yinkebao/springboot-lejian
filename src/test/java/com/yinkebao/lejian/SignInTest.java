package com.yinkebao.lejian;

import com.yinkebao.lejian.writtenexamination.application.ApplicationServiceRegistry;
import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
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
public class SignInTest {

	@Autowired
	ApplicationServiceRegistry applicationServiceRegistry;

	@Test
	public void sign() {
		applicationServiceRegistry.getLeJanApplicationService()
				.signIn(MobileSignInCommand.builder().mobile("15556532756").build());
	}

}
