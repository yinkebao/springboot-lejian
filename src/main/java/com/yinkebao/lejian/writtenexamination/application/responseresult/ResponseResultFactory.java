package com.yinkebao.lejian.writtenexamination.application.responseresult;

import com.yinkebao.lejian.writtenexamination.infrastructure.server.vo.SimpleVo;

/**
 * @ClassName ResponseResultFactory
 * @Description 返回响应构造
 * @Author ykb
 * @Date 2020/9/28
 */
public class ResponseResultFactory {
	private static final Integer ERROR_CODE = -1;
	private static final Integer SUCCESS_CODE = 200;

	public ResponseResultFactory() {
	}

	public static <T> ResponseResult<T> successResult(T t) {
		ResponseResult result = ResponseResult.instance();
		result.setCode(SUCCESS_CODE);
		result.setSuccess(Boolean.TRUE);
		result.setData(t);
		return result;
	}

	public static <T> ResponseResult<T> successResult() {
		ResponseResult result = ResponseResult.instance();
		result.setSuccess(Boolean.TRUE);
		result.setCode(SUCCESS_CODE);
		return result;
	}

	public static <T> ResponseResult successResult(String message, T t) {
		ResponseResult result = successResult(t);
		result.setSuccess(Boolean.TRUE);
		result.setMsg(message);
		return result;
	}

	public static ResponseResult errorResult(String message) {
		ResponseResult result = errorMsg(message);
		return result;
	}

	public static ResponseResult errorResult(String errorCode, String message) {
		ResponseResult result = errorMsg(errorCode, message);
		return result;
	}

	public static ResponseResult errorMsg(String message) {
		ResponseResult result = ResponseResult.instance();
		result.setCode(ERROR_CODE);
		result.setMsg(message);
		result.setSuccess(Boolean.FALSE);
		return result;
	}

	public static ResponseResult errorMsg(String errorCode, String message) {
		ResponseResult result = ResponseResult.instance();
		result.setErrorCode(errorCode);
		result.setCode(ERROR_CODE);
		result.setMsg(message);
		result.setSuccess(Boolean.FALSE);
		return result;
	}

	public static <T> ResponseResult successWrapperResult(T t) {
		ResponseResult result = ResponseResult.instance();
		result.setCode(SUCCESS_CODE);
		result.setData(new SimpleVo(t));
		result.setSuccess(Boolean.TRUE);
		return result;
	}

	public static <T> ResponseResult successWrapperResult(String message, T t) {
		ResponseResult result = successResult(new SimpleVo(t));
		result.setMsg(message);
		return result;
	}
}
