package com.example.material.utils;

import com.example.material.entity.TokenConstant;
import com.example.material.model.AuthInfo;
import com.example.material.model.User;
import com.example.material.model.UserInfo;
import com.example.material.secure.SecureUtil;
import com.example.material.secure.TokenInfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 认证工具类
 *
 * @author Chill
 */
public class TokenUtil {

	public final static String USER_TYPE_HEADER_KEY = "User-Type";
	public final static String DEFAULT_USER_TYPE = "web";
	public final static String USER_NOT_FOUND = "用户名或密码错误";

	/**
	 * 创建认证token
	 *
	 * @param userInfo 用户信息
	 * @return token
	 */
	public static AuthInfo createAuthInfo(UserInfo userInfo) throws UnsupportedEncodingException {
		User user = userInfo.getUser();

		//设置jwt参数
		Map<String, String> param = new HashMap<>(16);
		param.put(TokenConstant.TOKEN_TYPE, TokenConstant.ACCESS_TOKEN);
		param.put(TokenConstant.USER_ID, Func.toStr(user.getId()));
		param.put(TokenConstant.ROLE_ID, user.getRoleId());
		param.put(TokenConstant.ACCOUNT, user.getAccount());
		param.put(TokenConstant.USER_NAME, user.getAccount());
		param.put(TokenConstant.ROLE_NAME, Func.join(userInfo.getRoles()));

		TokenInfo accessToken = SecureUtil.createJWT(param, "audience", "issuser", TokenConstant.ACCESS_TOKEN);
		AuthInfo authInfo = new AuthInfo();
		authInfo.setAccount(user.getAccount());
		authInfo.setUserName(user.getRealName());
		authInfo.setAuthority(Func.join(userInfo.getRoles()));
		authInfo.setAccessToken(accessToken.getToken());
		authInfo.setExpiresIn(accessToken.getExpire());
		authInfo.setRefreshToken(createRefreshToken(userInfo).getToken());
		authInfo.setTokenType(TokenConstant.BEARER);
		authInfo.setLicense(TokenConstant.LICENSE_NAME);

		return authInfo;
	}

	/**
	 * 创建refreshToken
	 *
	 * @param userInfo 用户信息
	 * @return refreshToken
	 */
	private static TokenInfo createRefreshToken(UserInfo userInfo) throws UnsupportedEncodingException {
		User user = userInfo.getUser();
		Map<String, String> param = new HashMap<>(16);
		param.put(TokenConstant.TOKEN_TYPE, TokenConstant.REFRESH_TOKEN);
		param.put(TokenConstant.USER_ID, Func.toStr(user.getId()));
		return SecureUtil.createJWT(param, "audience", "issuser", TokenConstant.REFRESH_TOKEN);
	}

}
