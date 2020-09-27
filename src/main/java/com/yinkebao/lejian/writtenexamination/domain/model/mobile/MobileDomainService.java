package com.yinkebao.lejian.writtenexamination.domain.model.mobile;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @ClassName MobileDomainService
 * @Description 手机信息领域行为服务层
 * @Author ykb
 * @Date 2020/9/27
 */
@Service
public class MobileDomainService {

	@Resource
	MobileRepository mobileRepository;

	/**
	 * 注册手机号
	 *
	 * @param mobile 手机信息
	 */
	public void register(Mobile mobile){
		mobileRepository.save(mobile);
	}

}
