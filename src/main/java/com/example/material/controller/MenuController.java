package com.example.material.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.material.constant.CommonConstant;
import com.example.material.model.BladeUser;
import com.example.material.model.Menu;
import com.example.material.model.MenuVO;
import com.example.material.secure.SecureUtil;
import com.example.material.service.IMenuService;
import com.example.material.tools.Condition;
import com.example.material.tools.Kv;
import com.example.material.utils.Func;
import com.example.material.utils.R;


import com.example.material.wapper.MenuWrapper;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping(CommonConstant.API_PREFIX + "menu")
public class MenuController  {

	private IMenuService menuService;

	/**
	 * 详情
	 */
	//@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	//@ApiOperationSupport(order = 1)
	//@ApiOperation(value = "详情", notes = "传入menu")
	@GetMapping("/detail")
	public R<MenuVO> detail(Menu menu) {
		Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
		return R.data(MenuWrapper.build().entityVO(detail));
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "code", value = "菜单编号", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "name", value = "菜单名称", paramType = "query", dataType = "string")
	//})
	//@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	//@ApiOperationSupport(order = 2)
	//@ApiOperation(value = "列表", notes = "传入menu")
	public R<List<MenuVO>> list( @RequestParam Map<String, Object> menu) {
		@SuppressWarnings("unchecked")
		List<Menu> list = menuService.list(Condition.getQueryWrapper(menu, Menu.class).lambda().orderByAsc(Menu::getSort));
		return R.data(MenuWrapper.build().listNodeVO(list));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	//@ApiOperationSupport(order = 3)
	//@ApiOperation(value = "新增或修改", notes = "传入menu")
	public R submit(@Valid @RequestBody Menu menu) {
		return R.status(menuService.saveOrUpdate(menu));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	//@ApiOperationSupport(order = 4)
	//@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@RequestParam String ids) {
		return R.status(menuService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 前端菜单数据
	 */
	@GetMapping("/routes")
	//@ApiOperationSupport(order = 5)
	//@ApiOperation(value = "前端菜单数据", notes = "前端菜单数据")
	public R<List<MenuVO>> routes() {
		List<MenuVO> list = menuService.routes(SecureUtil.getUserRoleId());
		return R.data(list);
	}

	/**
	 * 前端按钮数据
	 */
	@GetMapping("/buttons")
	//@ApiOperationSupport(order = 6)
	//@ApiOperation(value = "前端按钮数据", notes = "前端按钮数据")
	public R<List<MenuVO>> buttons() {
		List<MenuVO> list = menuService.buttons(SecureUtil.getUserRoleId());
		return R.data(list);
	}

	/**
	 * 获取菜单树形结构
	 */
	@GetMapping("/tree")
	//@ApiOperationSupport(order = 7)
	//@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<MenuVO>> tree() {
		List<MenuVO> tree = menuService.tree();
		return R.data(tree);
	}

	/**
	 * 获取权限分配树形结构
	 */
	@GetMapping("/grant-tree")
	//@ApiOperationSupport(order = 8)
	//@ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
	public R<List<MenuVO>> grantTree(BladeUser user) {
		return R.data(menuService.grantTree(user));
	}

	/**
	 * 获取权限分配树形结构
	 */
	@GetMapping("/role-tree-keys")
	//@ApiOperationSupport(order = 9)
	//@ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
	public R<List<String>> roleTreeKeys(String roleIds) {
		return R.data(menuService.roleTreeKeys(roleIds));
	}

	/**
	 * 获取配置的角色权限
	 */
	@GetMapping("auth-routes")
	//@ApiOperationSupport(order = 10)
	//@ApiOperation(value = "菜单的角色权限")
	public R<List<Kv>> authRoutes(BladeUser user) {
		return R.data(menuService.authRoutes(user));
	}

}
