package com.yinkebao.lejian.writtenexamination.util;

import com.yinkebao.lejian.writtenexamination.application.validate.StringValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManacherAlgorithmUtil
 * @Description 马拉车算法处理最长回文串
 * @Author ykb
 * @Date 2020/9/28
 */
public class ManacherAlgorithmUtil {

	/**
	 * 预处理字符串，在两个字符之间加上#，并过滤掉特殊字符
	 *
	 * @param s 待处理串
	 * @return String
	 */
	private static String preHandleStr(String s) {
		StringBuffer sb = new StringBuffer();
		int len = s.length();
		sb.append('#');
		for(int i = 0; i < len; i++) {
			if (StringValidator.charAndNumberValidate(String.valueOf(s.charAt(i)))){
				sb.append(s.charAt(i));
				sb.append('#');
			}
		}
		return sb.toString();
	}

	/**
	 * 寻找最长回文字串
	 *
	 * @param s 待处理串
	 * @return List<String>
	 */
	public static List<String> findLongestPalindromeStr(String s) {
		// 先预处理字符串
		String str = ManacherAlgorithmUtil.preHandleStr(s);
		// 处理后的字串长度
		int len = str.length();
		// 右边界
		int rightSide = 0;
		// 右边界对应的回文串中心
		int rightSideCenter = 0;
		// 保存以每个字符为中心的回文长度一半（向下取整）
		int[] halfLenArr = new int[len];
		// 记录回文中心
		int center = 0;
		// 记录最长回文长度
		int longestHalf = 0;
		List<String> strList = new ArrayList<>();
		for(int i = 0; i < len; i++) {
			// 是否需要中心扩展
			boolean needCalc = true;
			// 如果在右边界的覆盖之内
			if(rightSide > i) {
				// 计算相对rightSideCenter的对称位置
				int leftCenter = 2 * rightSideCenter - i;
				// 根据回文性质得到的结论
				halfLenArr[i] = halfLenArr[leftCenter];
				// 如果超过了右边界，进行调整
				if(i + halfLenArr[i] > rightSide) {
					halfLenArr[i] = rightSide - i;
				}
				// 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
				if(i + halfLenArr[leftCenter] < rightSide) {
					// 直接推出结论
					needCalc = false;
				}
			}
			// 中心扩展
			if(needCalc) {
				while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
					if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
						halfLenArr[i]++;
					} else {
						break;
					}
				}
				// 更新右边界及中心
				rightSide = i + halfLenArr[i];
				rightSideCenter = i;
				// 记录最长回文串
				if(halfLenArr[i] > longestHalf) {
					strList = new ArrayList<>();
					center = i;
					longestHalf = halfLenArr[i];
					strList.add(center+","+longestHalf);
				}else if (halfLenArr[i] == longestHalf){
					strList.add(i+","+longestHalf);
				}
			}
		}
		List<String> outStr = new ArrayList<>();
		StringBuffer buffer ;
		for (String s1 : strList) {
			buffer = new StringBuffer();
			String[] c = s1.split(",");
			for(int i = Integer.parseInt(c[0]) - Integer.parseInt(c[1]) + 1; i <= Integer.parseInt(c[0]) + Integer.parseInt(c[1]); i += 2) {
				buffer.append(str.charAt(i));
			}
			outStr.add(buffer.toString());
		}
		return outStr;
	}

}
