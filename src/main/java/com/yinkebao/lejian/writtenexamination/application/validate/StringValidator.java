package com.yinkebao.lejian.writtenexamination.application.validate;

import com.yinkebao.lejian.writtenexamination.constant.CommonConstant;
import java.util.regex.Pattern;

/**
 * @ClassName StringValidator
 * @Description 字符串校验器
 * @Author ykb
 * @Date 2020/9/27
 */
public class StringValidator {

	/**
	 * 校验字符串是否只有数字
	 *
	 * @param str 待校验字符串
	 * @return Boolean
	 */
	public static Boolean numberValidate(String str){
		if (str == null || "".equals(str)){
			return false;
		}
		Pattern pattern = Pattern.compile(CommonConstant.NUMBER_PATTERN);
		return pattern.matcher(str).matches();
	}

	/**
	 * 校验字符串只包含数字、字母
	 *
	 * @param str 待校验字符串
	 * @return Boolean
	 */
	public static Boolean charAndNumberValidate(String str){
		if (str == null || "".equals(str)){
			return false;
		}
		Pattern pattern = Pattern.compile(CommonConstant.CHAR_NUMBER_PATTERN);
		return pattern.matcher(str).matches();
	}

	/**
	 * 校验字符串是否是合法的手机号
	 *
	 * @param str 待校验字符串
	 * @return Boolean
	 */
	public static Boolean mobileValidate(String str){
		if (str == null || "".equals(str)){
			return false;
		}
		Pattern pattern = Pattern.compile(CommonConstant.MOBILE_PATTERN);
		return pattern.matcher(str).matches();
	}

}
