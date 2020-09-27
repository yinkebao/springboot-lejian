package com.yinkebao.lejian.writtenexamination.infrastructure.server.cache.repositoryimpl;

import com.yinkebao.lejian.writtenexamination.domain.model.mobile.Mobile;
import com.yinkebao.lejian.writtenexamination.domain.model.mobile.MobileRepository;
import org.springframework.stereotype.Service;
import sun.misc.Cache;

/**
 * @ClassName MobileRepositoryImpl
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@Service
public class MobileRepositoryImpl implements MobileRepository {

	/**
	 * 持久化手机信息
	 *
	 * @param mobile 手机信息
	 */
	@Override
	public void save(Mobile mobile) {
		Cache cache = new Cache();
		cache.put(mobile.getMobile(),mobile.getMobile());
	}

	/**
	 * 根据手机号获取手机信息
	 *
	 * @param mobile 手机号获取手机
	 */
	@Override
	public Mobile get(String mobile) {
		Cache cache = new Cache();
		Object o = cache.get(mobile);
		return o == null ? null : Mobile.builder().mobile((String) o).build();
	}

}
