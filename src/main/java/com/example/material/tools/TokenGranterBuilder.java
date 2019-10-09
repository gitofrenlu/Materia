package com.example.material.tools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.example.material.secure.SecureException;
import com.example.material.service.PasswordTokenGranter;
import com.example.material.service.RefreshTokenGranter;
import com.example.material.utils.Func;
import com.example.material.utils.SpringUtil;
import lombok.AllArgsConstructor;

/**
 * TokenGranterBuilder
 *
 * @author Chill
 */
@AllArgsConstructor
public class TokenGranterBuilder {
	public static final String GRANT_TYPE = "refresh_token";

	/**
	 * TokenGranter缓存池
	 */
	private static Map<String, ITokenGranter> granterPool = new ConcurrentHashMap<>();

	static {
		granterPool.put(PasswordTokenGranter.GRANT_TYPE, SpringUtil.getBean(PasswordTokenGranter.class));
		granterPool.put(GRANT_TYPE, SpringUtil.getBean(RefreshTokenGranter.class));
	}

	/**
	 * 获取TokenGranter
	 *
	 * @param grantType 授权类型
	 * @return ITokenGranter
	 */
	public static ITokenGranter getGranter(String grantType) {
		ITokenGranter tokenGranter = granterPool.get(Func.toStr(grantType, PasswordTokenGranter.GRANT_TYPE));
		if (tokenGranter == null) {
			throw new SecureException("no grantType was found");
		} else {
			return tokenGranter;
		}
	}

}
