package com.yinkebao.lejian.writtenexamination.domain.model.error;

import java.io.Serializable;

/**
 * @ClassName ErrorInfo
 * @Description 异常信息类
 * @Author ykb
 * @Date 2020/9/28
 */
public class ErrorInfo implements Serializable {
	private static final long serialVersionUID = -8341070242774078741L;
	private String code = "";
	private String msg = "";

	public ErrorInfo(String code, String msg) {
		if (code == null) {
			code = "";
		}

		if (msg == null) {
			msg = "";
		}

		this.code = code;
		this.msg = msg;
	}

	public ErrorInfo msgFormatter(Object... args) {
		return new ErrorInfo(this.getCode(), String.format(this.msg, args));
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public ErrorInfo() {
	}
}
