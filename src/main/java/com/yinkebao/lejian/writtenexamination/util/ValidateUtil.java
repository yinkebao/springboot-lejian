package com.tianque.grid.workdairy.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.util.StringUtils;

/**
 * @ClassName ValidateUtil
 * @Description 简单字段/对象校验工具类
 * @Author ykb
 * @Date 2020/2/6
 */
public class ValidateUtil {

  public static void isNullObj(Object o, String msg) {
    if (!ObjectUtil.isNotNull(o)) {
      throw new IllegalArgumentException(msg);
    }
  }

  public static void isNullStr(String str, String msg) {
    if (StringUtils.isEmpty(str)) {
      throw new IllegalArgumentException(msg);
    }
  }
}
