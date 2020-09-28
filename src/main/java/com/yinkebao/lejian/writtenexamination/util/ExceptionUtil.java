package com.yinkebao.lejian.writtenexamination.util;

import com.yinkebao.lejian.writtenexamination.constant.ExceptionConstant;
import com.yinkebao.lejian.writtenexamination.constant.enums.ErrorCode;
import com.yinkebao.lejian.writtenexamination.exception.BusinessException;
import org.slf4j.Logger;

/**
 * @ClassName ExceptionUtil
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
public class ExceptionUtil {

	/**
	 * 抛出系统自定义异常并打印日志
	 * 去掉事务回滚
	 *
	 * @param logger 异常所在类的log
	 * @param errorCode 异常编码
	 */
	public static void throwError(Logger logger, ErrorCode errorCode){
		logger.error(ExceptionConstant.ERROR_INFO_STORE.get(errorCode.getError())
				.getMsg());
		throw new BusinessException(errorCode);
	}

	/**
	 * 抛出系统自定义异常不打印日志
	 *
	 * @param errorCode 异常编码
	 */
	public static void throwError(ErrorCode errorCode){
		throw new BusinessException(errorCode);
	}

}
