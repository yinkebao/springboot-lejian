package com.yinkebao.lejian.writtenexamination.application.applicationservice;

import com.yinkebao.lejian.writtenexamination.application.command.StringCommand;
import com.yinkebao.lejian.writtenexamination.util.ManacherAlgorithmUtil;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @ClassName PalindromeStrApplicationService
 * @Description 回文串应用服务层
 * @Author ykb
 * @Date 2020/9/28
 */
@Service
public class PalindromeStrApplicationService {

	private static Logger logger = LoggerFactory.getLogger(PalindromeStrApplicationService.class);

	/**
	 * 寻找最长回文串
	 *
	 * @param command 寻找最长回文串命令
	 * @return String>
	 */
	public List<String> findLongestPalindromeStr(StringCommand command){
		if (StringUtils.isEmpty(command.getStr())){
			return new ArrayList<>();
		}
		return ManacherAlgorithmUtil
				.findLongestPalindromeStr(command.getStr());
	}

}
