package com.yinkebao.lejian.writtenexamination.application.command;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName SignInCommand
 * @Description 手机号注册命令
 * @Author ykb
 * @Date 2020/9/27
 */
@Getter
@Setter
@NoArgsConstructor
public class MobileSignInCommand implements Serializable {

	/**
	 * 手机号
	 */
	private String mobile;

	@Builder
	public MobileSignInCommand(String mobile) {
		this.mobile = mobile;
	}
}
