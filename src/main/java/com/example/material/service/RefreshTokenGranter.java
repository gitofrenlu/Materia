package com.example.material.service;

import java.util.Objects;


import com.example.material.entity.TokenConstant;
import com.example.material.model.UserInfo;
import com.example.material.secure.SecureUtil;
import com.example.material.tools.ITokenGranter;
import com.example.material.tools.TokenParameter;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * RefreshTokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class RefreshTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "refresh_token";

	//private IUserClient userClient;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		String grantType = tokenParameter.getArgs().getStr("grantType");
		String refreshToken = tokenParameter.getArgs().getStr("refreshToken");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(grantType, refreshToken) && grantType.equals(TokenConstant.REFRESH_TOKEN)) {
			Claims claims = SecureUtil.parseJWT(refreshToken);
			String tokenType = Func.toStr(Objects.requireNonNull(claims).get(TokenConstant.TOKEN_TYPE));
			if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
				//R<UserInfo> result = userClient.userInfo(Func.toLong(claims.get(TokenConstant.USER_ID)));
				R<UserInfo> result = null;
				userInfo = result.isSuccess() ? result.getData() : null;
			}
		}
		return userInfo;
	}
}
