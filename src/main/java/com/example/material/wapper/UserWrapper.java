
package com.example.material.wapper;

import com.example.material.model.User;
import com.example.material.model.UserVO;
import com.example.material.service.IDictClient;
import com.example.material.service.IUserService;
import com.example.material.utils.BeanUtil;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import com.example.material.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;



/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@Component
public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

	@Autowired
	private  IUserService userService;
	@Autowired
	private  IDictClient dictClient;

	private static UserWrapper wapper;

	//初始化静态参数
	@PostConstruct
	public void init() {
		wapper = this;
		wapper.userService = this.userService;
		wapper.dictClient = this.dictClient;
	}



	public static UserWrapper build() {
		return new UserWrapper();
	}

	@Override
	public UserVO entityVO(User user) {
		UserVO userVO = BeanUtil.copy(user, UserVO.class);
		List<String> roleName = wapper.userService.getRoleName(user.getRoleId());
		List<String> deptName = wapper.userService.getDeptName(user.getDeptId());
		userVO.setRoleName(Func.join(roleName));
		userVO.setDeptName(Func.join(deptName));
		R<String> dict = wapper.dictClient.getValue("sex", Func.toInt(user.getSex()));
		if (dict.isSuccess()) {
			userVO.setSexName(dict.getData());
		}
		return userVO;
	}

}
