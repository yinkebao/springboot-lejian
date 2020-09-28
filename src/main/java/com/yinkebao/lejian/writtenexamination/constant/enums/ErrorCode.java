package com.yinkebao.lejian.writtenexamination.constant.enums;

import com.alibaba.fastjson.JSONObject;
import com.yinkebao.lejian.writtenexamination.exception.Error;
import lombok.Getter;

/**
 * @ClassName ErrorCode
 * @Description 异常信息枚举
 * @Author ykb
 * @Date 2020/9/27
 */
@Getter
public enum ErrorCode implements Error {

	/**
	 * 异常信息枚举类型
	 */
	MOBILE_EXIST("yinkebao.error.mobile.not.exists"),
	MOBILE_ILLEGAL("yinkebao.error.mobile.illegal"),
	MOBILE_CHINA_ILLEGAL("yinkebao.error.mobile.china.illegal")
	;

	ErrorCode(String error) {
		this.error = error;
	}

	/**
	 * 异常信息
	 */
	private final String error;


	public String getErrorMsg() {
		return this.getError();
	}

	@Override
	public String getName() {
		return this.getErrorMsg();
	}
}
