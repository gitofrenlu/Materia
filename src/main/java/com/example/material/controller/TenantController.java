package com.example.material.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.material.constant.CommonConstant;
import com.example.material.model.BladeUser;
import com.example.material.model.Tenant;
import com.example.material.secure.SecureUtil;
import com.example.material.service.ITenantService;
import com.example.material.tools.Condition;
import com.example.material.tools.Query;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import org.springframework.web.bind.annotation.*;


import lombok.AllArgsConstructor;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping(CommonConstant.API_PREFIX + "tenant")
//@Api(value = "租户管理", tags = "接口")
public class TenantController  {

	private ITenantService tenantService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	//@ApiOperation(value = "详情", notes = "传入tenant")
	public R<Tenant> detail(Tenant tenant) {
		Tenant detail = tenantService.getOne(Condition.getQueryWrapper(tenant));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "tenantId", value = "参数名称", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "tenantName", value = "角色别名", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", dataType = "string")
	//})
	//@ApiOperation(value = "分页", notes = "传入tenant")
	public R<IPage<Tenant>> list(@RequestParam Map<String, Object> tenant, Query query, BladeUser bladeUser) {
		QueryWrapper<Tenant> queryWrapper = Condition.getQueryWrapper(tenant, Tenant.class);
		IPage<Tenant> pages = tenantService.page(Condition.getPage(query), (!bladeUser.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(Tenant::getTenantId, bladeUser.getTenantId()) : queryWrapper);
		return R.data(pages);
	}

	/**
	 * 下拉数据源
	 */
	@GetMapping("/select")
	//@ApiOperation(value = "下拉数据源", notes = "传入tenant")
	public R<List<Tenant>> select(Tenant tenant, BladeUser bladeUser) {
		QueryWrapper<Tenant> queryWrapper = Condition.getQueryWrapper(tenant);
		List<Tenant> list = tenantService.list((!SecureUtil.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(Tenant::getTenantId, bladeUser.getTenantId()) : queryWrapper);
		return R.data(list);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	//@ApiOperation(value = "分页", notes = "传入tenant")
	public R<IPage<Tenant>> page(Tenant tenant, Query query) {
		IPage<Tenant> pages = tenantService.selectTenantPage(Condition.getPage(query), tenant);
		return R.data(pages);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@ApiOperation(value = "新增或修改", notes = "传入tenant")
	public R submit(@Valid @RequestBody Tenant tenant) {
		return R.status(tenantService.saveTenant(tenant));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@RequestParam String ids) {
		return R.status(tenantService.deleteLogic(Func.toIntList(ids)));
	}


}
