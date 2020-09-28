package com.yinkebao.lejian.writtenexamination.infrastructure.server.redis.repositoryimpl;

import com.yinkebao.lejian.writtenexamination.domain.model.mobile.Mobile;
import com.yinkebao.lejian.writtenexamination.domain.model.mobile.MobileRepository;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName MobileRepositoryImpl
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
@Service
public class MobileRepositoryImpl implements MobileRepository {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 持久化手机信息(模拟)
	 *
	 * @param mobile 手机信息
	 */
	@Override
	public void save(Mobile mobile) {
		redisTemplate.opsForValue().set(mobile.getMobile(),mobile.getMobile());
	}

	/**
	 * 判断手机号是否存在
	 *
	 * @param mobile 手机号
	 */
	@Override
	public Boolean ifExist(String mobile) {
		return redisTemplate.hasKey(mobile);
	}

}
