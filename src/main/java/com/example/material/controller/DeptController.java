package com.example.material.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import com.example.material.constant.CommonConstant;
import com.example.material.model.BladeUser;
import com.example.material.model.Dept;
import com.example.material.model.DeptVO;
import com.example.material.secure.SecureUtil;
import com.example.material.service.IDeptService;
import com.example.material.tools.Condition;
import com.example.material.tools.INode;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import com.example.material.wapper.DeptWrapper;
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
@RequestMapping(CommonConstant.API_PREFIX + "dept")
//@Api(value = "部门", tags = "部门")
public class DeptController extends BladeController {

	private IDeptService deptService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	//@ApiOperationSupport(order = 1)
	//@ApiOperation(value = "详情", notes = "传入dept")
	public R<DeptVO> detail(Dept dept) {
		Dept detail = deptService.getOne(Condition.getQueryWrapper(dept));
		return R.data(DeptWrapper.build().entityVO(detail));
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "fullName", value = "部门全称", paramType = "query", dataType = "string")
	//})
	//@ApiOperationSupport(order = 2)
	//@ApiOperation(value = "列表", notes = "传入dept")
	public R<List<INode>> list(@RequestParam Map<String, Object> dept) {
		QueryWrapper<Dept> queryWrapper = Condition.getQueryWrapper(dept, Dept.class);
		List<Dept> list = deptService.list(queryWrapper);
		return R.data(DeptWrapper.build().listNodeVO(list));
	}

	/**
	 * 获取部门树形结构
	 *
	 * @return
	 */
	@GetMapping("/tree")
	//@ApiOperationSupport(order = 3)
	//@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<DeptVO>> tree(String tenantId, BladeUser bladeUser) {
		List<DeptVO> tree = deptService.tree(Func.toStr(tenantId, bladeUser.getTenantId()));
		return R.data(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@ApiOperationSupport(order = 4)
	//@ApiOperation(value = "新增或修改", notes = "传入dept")
	public R submit(@Valid @RequestBody Dept dept, BladeUser user) {
		if (Func.isEmpty(dept.getId())) {
			dept.setTenantId(user.getTenantId());
		}
		return R.status(deptService.saveOrUpdate(dept));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@ApiOperationSupport(order = 5)
	//@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(
			//@ApiParam(value = "主键集合", required = true)
	 @RequestParam String ids) {
		return R.status(deptService.removeByIds(Func.toIntList(ids)));
	}


}
