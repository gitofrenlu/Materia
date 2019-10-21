
package com.example.material.controller;


import java.util.Map;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.material.constant.CommonConstant;

import com.example.material.model.BladeUser;
import com.example.material.model.User;
import com.example.material.model.UserVO;
import com.example.material.secure.SecureUtil;
import com.example.material.service.IUserService;
import com.example.material.tools.Condition;
import com.example.material.tools.Query;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import com.example.material.wapper.UserWrapper;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.AllArgsConstructor;

/**
 * 控制器
 *
 * @author Chill
 */

@RestController
@RequestMapping(CommonConstant.API_PREFIX +"user")
@AllArgsConstructor
//@Api(value = "用户", tags = "用户")
public class UserController {

	private IUserService userService;

	/**
	 * 查询单条
	 */
	//@ApiOperationSupport(order = 1)
	//@ApiOperation(value = "查看详情", notes = "传入id")
	@GetMapping("/detail")
	public R<UserVO> detail(User user) {
		User detail = userService.getOne(Condition.getQueryWrapper(user));
		return R.data(UserWrapper.build().entityVO(detail));
	}

	/**
	 * 用户列表
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "account", value = "账号名", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "realName", value = "姓名", paramType = "query", dataType = "string")
	//})
	//@ApiOperationSupport(order = 2)
	//@ApiOperation(value = "列表", notes = "传入account和realName")
	public R<IPage<UserVO>> list(@RequestParam Map<String, Object> user, Query query) {
		QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(user, User.class);
		IPage<User> pages = userService.page(
				Condition.getPage(query),queryWrapper);
		return R.data(UserWrapper.build().pageVO(pages));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@ApiOperationSupport(order = 3)
	//@ApiOperation(value = "新增或修改", notes = "传入User")
	public R submit(@Valid @RequestBody User user) {
		return R.status(userService.submit(user));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	//@ApiOperationSupport(order = 4)
	//@ApiOperation(value = "修改", notes = "传入User")
	public R update(@Valid @RequestBody User user) {
		return R.status(userService.updateById(user));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@ApiOperationSupport(order = 5)
	//@ApiOperation(value = "删除", notes = "传入地基和")
	public R remove(@RequestParam String ids) {
		return R.status(userService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 设置菜单权限
	 *
	 * @param userIds
	 * @param roleIds
	 * @return
	 */
	@PostMapping("/grant")
	//@ApiOperationSupport(order = 6)
	//@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public R grant(//@ApiParam(value = "userId集合", required = true)
					@RequestParam String userIds,
				   //@ApiParam(value = "roleId集合", required = true)
				   @RequestParam String roleIds) {
		boolean temp = userService.grant(userIds, roleIds);
		return R.status(temp);
	}

	@PostMapping("/reset-password")
	//@ApiOperationSupport(order = 7)
	//@ApiOperation(value = "初始化密码", notes = "传入userId集合")
	public R resetPassword(
			//@ApiParam(value = "userId集合", required = true)
	 @RequestParam String userIds) {
		boolean temp = userService.resetPassword(userIds);
		return R.status(temp);
	}

}
