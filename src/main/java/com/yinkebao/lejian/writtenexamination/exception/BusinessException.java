package com.yinkebao.lejian.writtenexamination.exception;

import com.yinkebao.lejian.writtenexamination.constant.ExceptionConstant;
import com.yinkebao.lejian.writtenexamination.domain.model.error.ErrorInfo;
import lombok.NoArgsConstructor;

/**
 * @ClassName BusinessException
 * @Description 自定义业务异常
 * @Author ykb
 * @Date 2020/9/28
 */
@NoArgsConstructor
public class BusinessException extends RuntimeException {
	private ErrorInfo errorInfo;

	public BusinessException(Error error, Object... args) {
		super(((ErrorInfo)ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args).getMsg());
		this.errorInfo = ((ErrorInfo)ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args);
	}

	public BusinessException(Error error, Throwable cause, Object... args) {
		super(((ErrorInfo)ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args).getMsg(), cause);
		this.errorInfo = ((ErrorInfo)ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	protected BusinessException(Error error, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... args) {
		super(((ErrorInfo) ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args).getMsg(), cause, enableSuppression, writableStackTrace);
		this.errorInfo = ((ErrorInfo)ExceptionConstant.ERROR_INFO_STORE.get(error.getName())).msgFormatter(args);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public String getErrorCode() {
		return this.errorInfo.getCode();
	}

	public ErrorInfo getErrorInfo() {
		return this.errorInfo;
	}
}
