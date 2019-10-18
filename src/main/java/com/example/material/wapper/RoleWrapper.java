/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.material.wapper;

import com.example.material.constant.CommonConstant;
import com.example.material.model.Role;
import com.example.material.model.RoleVO;
import com.example.material.service.IRoleService;
import com.example.material.tools.ForestNodeMerger;
import com.example.material.tools.INode;
import com.example.material.utils.BeanUtil;
import com.example.material.utils.Func;
import com.example.material.utils.SpringUtil;

import java.util.List;
import java.util.stream.Collectors;



/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class RoleWrapper extends BaseEntityWrapper<Role, RoleVO> {

	private static IRoleService roleService;

	static {
		roleService = SpringUtil.getBean(IRoleService.class);
	}

	public static RoleWrapper build() {
		return new RoleWrapper();
	}

	@Override
	public RoleVO entityVO(Role role) {
		RoleVO roleVO = BeanUtil.copy(role, RoleVO.class);
		if (Func.equals(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Role parent = roleService.getById(role.getParentId());
			roleVO.setParentName(parent.getRoleName());
		}
		return roleVO;
	}

	public List<INode> listNodeVO(List<Role> list) {
		List<INode> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
