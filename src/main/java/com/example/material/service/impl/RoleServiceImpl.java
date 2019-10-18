
package com.example.material.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.material.constant.CommonConstant;
import com.example.material.mapper.RoleMapper;
import com.example.material.model.Role;
import com.example.material.model.RoleMenu;
import com.example.material.model.RoleVO;
import com.example.material.secure.SecureUtil;
import com.example.material.service.IRoleMenuService;
import com.example.material.service.IRoleService;
import com.example.material.tools.ForestNodeMerger;
import com.example.material.utils.CollectionUtil;
import com.example.material.utils.Func;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.AllArgsConstructor;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@Validated
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	IRoleMenuService roleMenuService;

	@Override
	public IPage<RoleVO> selectRolePage(IPage<RoleVO> page, RoleVO role) {
		return page.setRecords(baseMapper.selectRolePage(page, role));
	}

	@Override
	public List<RoleVO> tree(String tenantId) {
		String userRole = SecureUtil.getUserRole();
		String excludeRole = null;
		if (!CollectionUtil.contains(Func.toStrArray(userRole), CommonConstant.ADMIN)) {
			excludeRole = CommonConstant.ADMIN;
		}
		return ForestNodeMerger.merge(baseMapper.tree(tenantId, excludeRole));
	}

	@Override
	public boolean grant(@NotEmpty List<Integer> roleIds, @NotEmpty List<Integer> menuIds) {
		// 删除角色配置的菜单集合
		roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
		// 组装配置
		List<RoleMenu> roleMenus = new ArrayList<>();
		roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenus.add(roleMenu);
		}));
		// 新增配置
		return roleMenuService.saveBatch(roleMenus);
	}

}
