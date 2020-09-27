package com.yinkebao.lejian.writtenexamination.application.applicationservice;

import com.yinkebao.lejian.writtenexamination.application.command.MobileSignInCommand;
import com.yinkebao.lejian.writtenexamination.application.validate.StringValidator;
import com.yinkebao.lejian.writtenexamination.constant.enums.ErrorCode;
import com.yinkebao.lejian.writtenexamination.domain.model.mobile.Mobile;
import com.yinkebao.lejian.writtenexamination.domain.model.mobile.MobileDomainService;
import com.yinkebao.lejian.writtenexamination.domain.model.mobile.MobileRepository;
import com.yinkebao.lejian.writtenexamination.util.ExceptionUtil;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName LeJanApplicationService
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@Service
public class LeJanApplicationService {

	private static Logger logger = LoggerFactory.getLogger(LeJanApplicationService.class);

	@Resource
	MobileDomainService mobileDomainService;

	@Resource
	MobileRepository mobileRepository;

	/**
	 * 注册手机号
	 *
	 * @param command 注册命令
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public void signIn(MobileSignInCommand command){
		String mobileStr = command.getMobile().replace(""," ");
		if (StringValidator.numberValidate(mobileStr)){
			ExceptionUtil.throwError(logger, ErrorCode.MOBILE_ILLEGAL);
		}
		if (StringValidator.mobileValidate(command.getMobile())){
			ExceptionUtil.throwError(logger,ErrorCode.MOBILE_CHINA_ILLEGAL);
		}
		if (mobileRepository.get(command.getMobile()) != null){
			ExceptionUtil.throwError(logger,ErrorCode.MOBILE_EXIST);
		}
		mobileDomainService.signIn(Mobile.builder().mobile(command.getMobile()).build());
	}

}
