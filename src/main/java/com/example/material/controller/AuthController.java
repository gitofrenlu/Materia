package com.example.material.controller;


import com.example.material.model.AuthInfo;
import com.example.material.model.UserInfo;
import com.example.material.service.PasswordTokenGranter;
import com.example.material.tools.TokenParameter;
import com.example.material.utils.R;
import com.example.material.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * 认证模块
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/")
public class AuthController {

	@Autowired
	PasswordTokenGranter tokenGranter;

	@PostMapping("token")
	public R<AuthInfo> token(@RequestParam(defaultValue = "password", required = false) String grantType,
							 @RequestParam(required = false) String refreshToken,
							 @RequestParam(defaultValue = "000000", required = false) String tenantId,
							 @RequestParam(required = false) String account,
							 @RequestParam(required = false) String password) throws UnsupportedEncodingException {

		String userType = TokenUtil.DEFAULT_USER_TYPE;

		TokenParameter tokenParameter = new TokenParameter();
		tokenParameter.getArgs().set("tenantId", tenantId).set("account", account).set("password", password).set("grantType", grantType).set("refreshToken", refreshToken).set("userType", userType);

		//ITokenGranter granter = TokenGranterBuilder.getGranter(grantType);

		log.info(tokenParameter.toString());

		UserInfo userInfo = tokenGranter.grant(tokenParameter);

		if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
			return R.fail(TokenUtil.USER_NOT_FOUND);
		}

		return R.data(TokenUtil.createAuthInfo(userInfo));
	}

}
