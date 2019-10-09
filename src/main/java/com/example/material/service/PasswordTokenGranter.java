package com.example.material.service;


import com.example.material.entity.BladeUserEnum;
import com.example.material.mapper.UserMapper;
import com.example.material.model.User;
import com.example.material.model.UserInfo;
import com.example.material.tools.ITokenGranter;
import com.example.material.tools.TokenParameter;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * PasswordTokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
@Slf4j
public class PasswordTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "password";

	@Resource
	private UserMapper userMapper;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String account = tokenParameter.getArgs().getStr("account");
		String password = tokenParameter.getArgs().getStr("password");
		User result ;
		UserInfo userInfo = new UserInfo();
		if (Func.isNoneBlank(account, password)) {
			// 获取用户类型
			String userType = tokenParameter.getArgs().getStr("userType");

			// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
			if (userType.equals(BladeUserEnum.WEB.getName())) {
				result = userMapper.get(account, password);

				log.info("tenantId: %s account: %s  password: %s",tenantId,account,password);
				userInfo.setUser(result);
				if (Func.isNotEmpty(result)) {
					List<String> roleAlias = userMapper.getRoleAlias(result.getRoleId());
					userInfo.setRoles(roleAlias);
				}

				R.data(result);
			} else if (userType.equals(BladeUserEnum.APP.getName())) {
				//result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
			} else {
				//result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
			}
			//userInfo = result.isSuccess() ? result.getData() : null;
		}
		return userInfo;
	}

}
