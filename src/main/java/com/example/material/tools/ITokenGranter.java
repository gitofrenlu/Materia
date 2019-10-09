package com.example.material.tools;

import com.example.material.model.UserInfo;

public interface ITokenGranter {
	/**
	 * 获取用户信息
	 *
	 * @param tokenParameter 授权参数
	 * @return UserInfo
	 */
	UserInfo grant(TokenParameter tokenParameter);
}
