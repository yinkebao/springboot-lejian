package com.yinkebao.lejian.writtenexamination.domain.model.mobile;

/**
 * @ClassName MobileRespository
 * @Description
 * @Author ykb
 * @Date 2020/9/27
 */
public interface MobileRepository {

	/**
	 * 持久化手机信息
	 *
	 * @param mobile 手机信息
	 */
	public void save(Mobile mobile);

	/**
	 * 根据手机号获取手机信息
	 *
	 * @param mobile 手机号获取手机
	 * @return Mobile
	 */
	public Mobile get(String mobile);

}
