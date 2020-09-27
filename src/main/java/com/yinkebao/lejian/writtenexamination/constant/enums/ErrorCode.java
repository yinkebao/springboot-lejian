package com.yinkebao.lejian.writtenexamination.constant.enums;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

/**
 * @ClassName ErrorCode
 * @Description 异常信息枚举
 * @Author ykb
 * @Date 2020/9/27
 */
@Getter
public enum ErrorCode {

	/**
	 * 异常信息枚举类型
	 */
	MOBILE_EXIST("手机号已经被其他用户注册",1001),
	MOBILE_ILLEGAL("非法手机号,本手机号无法注册",1002),
	MOBILE_CHINA_ILLEGAL("中国大陆非法手机号码",1003),
	;

	ErrorCode(String error, Integer code) {
		this.error = error;
		this.code = code;
	}

	/**
	 * 异常信息
	 */
	private final String error;

	/**
	 * 异常编码
	 */
	private final Integer code;


	public String getErrorMsg() {
		return this.getError();
	}

	public Integer getErrorCode() {
		return this.getCode();
	}

	public JSONObject toJsonObject(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", this.error);
		jsonObject.put("code", this.code);
		return jsonObject;
	}

}
