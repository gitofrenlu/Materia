
package com.example.material.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.material.constant.CommonConstant;

import com.example.material.model.BladeUser;
import com.example.material.model.Role;
import com.example.material.model.RoleVO;
import com.example.material.secure.SecureUtil;
import com.example.material.service.IRoleService;
import com.example.material.tools.Condition;
import com.example.material.tools.INode;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import com.example.material.wapper.RoleWrapper;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.AllArgsConstructor;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping(CommonConstant.API_PREFIX + "role")
//@Api(value = "角色", tags = "角色")
public class RoleController extends BladeController {

	private IRoleService roleService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	//@ApiOperationSupport(order = 1)
	//@ApiOperation(value = "详情", notes = "传入role")
	public R<RoleVO> detail(Role role) {
		Role detail = roleService.getOne(Condition.getQueryWrapper(role));
		return R.data(RoleWrapper.build().entityVO(detail));
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "roleName", value = "参数名称", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "roleAlias", value = "角色别名", paramType = "query", dataType = "string")
	//})
	//@ApiOperationSupport(order = 2)
	//@ApiOperation(value = "列表", notes = "传入role")
	public R<List<INode>> list(@RequestParam Map<String, Object> role) {
		QueryWrapper<Role> queryWrapper = Condition.getQueryWrapper(role, Role.class);
		BladeUser bladeUser = SecureUtil.getUser();
		List<Role> list = roleService.list((!bladeUser.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(Role::getTenantId, bladeUser.getTenantId()) : queryWrapper);
		return R.data(RoleWrapper.build().listNodeVO(list));
	}

	/**
	 * 获取角色树形结构
	 */
	@GetMapping("/tree")
	//@ApiOperationSupport(order = 3)
	//@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<RoleVO>> tree(String tenantId, BladeUser bladeUser) {
		List<RoleVO> tree = roleService.tree(Func.toStr(tenantId, bladeUser.getTenantId()));
		return R.data(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@ApiOperationSupport(order = 4)
	//@ApiOperation(value = "新增或修改", notes = "传入role")
	public R submit(@Valid @RequestBody Role role, BladeUser user) {
		if (Func.isEmpty(role.getId())) {
			role.setTenantId(user.getTenantId());
		}
		return R.status(roleService.saveOrUpdate(role));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@ApiOperationSupport(order = 5)
	//@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(//@ApiParam(value = "主键集合", required = true)
	 @RequestParam String ids) {
		return R.status(roleService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 设置菜单权限
	 *
	 * @param roleIds
	 * @param menuIds
	 * @return
	 */
	@PostMapping("/grant")
	//@ApiOperationSupport(order = 6)
	//@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public R grant(
			//@ApiParam(value = "roleId集合", required = true)
	 		@RequestParam String roleIds,
		   //@ApiParam(value = "menuId集合", required = true)
			@RequestParam String menuIds) {
		boolean temp = roleService.grant(Func.toIntList(roleIds), Func.toIntList(menuIds));
		return R.status(temp);
	}

}
