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

/**
 * @ClassName MobileApplicationService
 * @Description 手机信息应用服务层
 * @Author ykb
 * @Date 2020/9/27
 */
@Service
public class MobileApplicationService {

	private static Logger logger = LoggerFactory.getLogger(MobileApplicationService.class);

	@Resource
	MobileDomainService mobileDomainService;

	@Resource
	MobileRepository mobileRepository;

	/**
	 * 注册手机号
	 * 使用真正db层时添加@Transactional(rollbackFor = Exception.class)开启事务
	 *
	 * @param command 注册命令
	 */
	public void register(MobileSignInCommand command){
		String mobileStr = command.getMobile().replace(" ","");
		if (!StringValidator.numberValidate(mobileStr)){
			ExceptionUtil.throwError(logger, ErrorCode.MOBILE_ILLEGAL);
		}
		if (!StringValidator.mobileValidate(command.getMobile())){
			ExceptionUtil.throwError(logger,ErrorCode.MOBILE_CHINA_ILLEGAL);
		}
		if (mobileRepository.ifExist(command.getMobile())){
			ExceptionUtil.throwError(logger,ErrorCode.MOBILE_EXIST);
		}
		mobileDomainService.register(Mobile.builder().mobile(command.getMobile()).build());
	}

}
