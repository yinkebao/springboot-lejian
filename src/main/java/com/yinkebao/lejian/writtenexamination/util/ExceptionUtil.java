package com.yinkebao.lejian.writtenexamination.util;

import com.yinkebao.lejian.writtenexamination.constant.enums.ErrorCode;
import org.slf4j.Logger;

/**
 * @ClassName ExceptionUtil
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
public class ExceptionUtil {

	/**
	 * 抛出系统自定义异常
	 * 去掉事务回滚
	 *
	 * @param logger 异常所在类的log
	 * @param errorCode 异常编码
	 */
	public static void throwError(Logger logger, ErrorCode errorCode){
		logger.error(errorCode.toJsonObject().toJSONString());
		throw new RuntimeException(errorCode.getError());
	}

	/**
	 * 抛出系统自定义异常
	 *
	 * @param errorCode 异常编码
	 */
	public static void throwError(ErrorCode errorCode){
		throw new RuntimeException(errorCode.getError());
	}

}
