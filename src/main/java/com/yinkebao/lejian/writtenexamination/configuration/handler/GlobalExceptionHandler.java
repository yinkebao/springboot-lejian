package com.tianque.grid.workdairy.configuration.handler;

import com.tianque.grid.ddd.ui.ResponseResult;
import com.tianque.grid.ddd.ui.ResponseResultFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常捕获
 * @Author ykb
 * @Date 2020/1/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  public ResponseResult illegalArgumentExceptionExceptionHandler(HttpServletRequest request,
      IllegalArgumentException exception) throws Exception {
    return ResponseResultFactory.errorResult(exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseResult exceptionExceptionHandler(HttpServletRequest request, Exception exception)
      throws Exception {
    return ResponseResultFactory.errorResult("系统出现异常，请联系管理员!", exception.getMessage());
  }
}
