package com.yinkebao.lejian.writtenexamination.application.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("手机号注册命令")
public class MobileSignInCommand implements Serializable {

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号", required = true)
	private String mobile;

	@Builder
	public MobileSignInCommand(String mobile) {
		this.mobile = mobile;
	}
}
