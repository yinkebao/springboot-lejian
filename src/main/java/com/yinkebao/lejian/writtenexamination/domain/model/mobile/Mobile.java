package com.yinkebao.lejian.writtenexamination.domain.model.mobile;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName Mobile
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@Getter
@Setter
@NoArgsConstructor
public class Mobile implements Serializable {

	/**
	 * 手机号
	 */
	private String mobile;

	@Builder
	public Mobile(String mobile) {
		this.mobile = mobile;
	}
}
