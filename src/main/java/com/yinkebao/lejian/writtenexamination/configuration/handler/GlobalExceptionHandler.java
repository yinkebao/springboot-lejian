package com.yinkebao.lejian.writtenexamination.configuration.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.yinkebao.lejian.writtenexamination.application.responseresult.ResponseResult;
import com.yinkebao.lejian.writtenexamination.application.responseresult.ResponseResultFactory;
import com.yinkebao.lejian.writtenexamination.exception.BusinessException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author ykb
 * @Date 2020/9/28
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public GlobalExceptionHandler() {
	}

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResponseResult exceptionExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
		log.error("[yinkebao]GlobalExceptionHandler return exceptioon: " + ExceptionUtil.stacktraceToString(exception));
		return ResponseResultFactory.errorResult(exception.getMessage());
	}

	@ExceptionHandler({BusinessException.class})
	@ResponseBody
	public ResponseResult businessExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
		BusinessException businessException = (BusinessException)exception;
		return ResponseResultFactory.errorResult(businessException.getErrorCode(), exception.getMessage());
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public ResponseResult methodArgumentNotValidHandler(HttpServletRequest request, Exception exception) throws Exception {
		MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)exception;
		List<ObjectError> errorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
		StringBuffer stringBuffer = new StringBuffer();
		if (!errorList.isEmpty()) {
			errorList.stream().forEach((item) -> {
				stringBuffer.append(item.getDefaultMessage());
				stringBuffer.append(";");
			});
		}
		return ResponseResultFactory.errorResult(stringBuffer.toString());
	}
}
