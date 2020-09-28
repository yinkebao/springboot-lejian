package com.yinkebao.lejian.writtenexamination.application.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName StringCommand
 * @Description
 * @Author ykb
 * @Date 2020/9/28
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("字符串解析命令")
public class StringCommand implements Serializable {

	/**
	 * 字符串
	 */
	@ApiModelProperty(value = "字符串", required = true)
	private String str;

	@Builder
	public StringCommand(String str) {
		this.str = str;
	}
}
