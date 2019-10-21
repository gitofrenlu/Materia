package com.example.material.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import com.example.material.codeSupport.BladeCodeGenerator;
import com.example.material.constant.CommonConstant;
import com.example.material.entity.Code;
import com.example.material.entity.Datasource;
import com.example.material.service.ICodeService;
import com.example.material.service.IDatasourceService;
import com.example.material.tools.Condition;
import com.example.material.tools.Query;
import com.example.material.utils.Func;
import com.example.material.utils.R;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.AllArgsConstructor;

/**
 * 控制器
 *
 * @author Chill
 */
//@ApiIgnore
@RestController
@AllArgsConstructor
@RequestMapping(CommonConstant.API_PREFIX+"code")
//@Api(value = "代码生成", tags = "代码生成")
//@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
public class CodeController extends BladeController {

	private ICodeService codeService;
	private IDatasourceService datasourceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	//@ApiOperationSupport(order = 1)
	//@ApiOperation(value = "详情", notes = "传入code")
	public R<Code> detail(Code code) {
		Code detail = codeService.getOne(Condition.getQueryWrapper(code));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	//@ApiImplicitParams({
	//	@ApiImplicitParam(name = "codeName", value = "模块名", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "tableName", value = "表名", paramType = "query", dataType = "string"),
	//	@ApiImplicitParam(name = "modelName", value = "实体名", paramType = "query", dataType = "string")
	//})
	//@ApiOperationSupport(order = 2)
	//@ApiOperation(value = "分页", notes = "传入code")
	public R<IPage<Code>> list(
			 @RequestParam Map<String, Object> code, Query query) {
		IPage<Code> pages = codeService.page(Condition.getPage(query), Condition.getQueryWrapper(code, Code.class));
		return R.data(pages);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	//@ApiOperationSupport(order = 3)
	//@ApiOperation(value = "新增或修改", notes = "传入code")
	public R submit(@Valid @RequestBody Code code) {
		return R.status(codeService.submit(code));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	//@ApiOperationSupport(order = 4)
	//@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(
			//@ApiParam(value = "主键集合", required = true)
			 @RequestParam String ids) {
		return R.status(codeService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 复制
	 */
	@PostMapping("/copy")
	//@ApiOperationSupport(order = 5)
	//@ApiOperation(value = "复制", notes = "传入id")
	public R copy(
			//@ApiParam(value = "主键", required = true)
			 @RequestParam Integer id) {
		Code code = codeService.getById(id);
		code.setId(null);
		code.setCodeName(code.getCodeName() + "-copy");
		return R.status(codeService.save(code));
	}

	/**
	 * 代码生成
	 */
	@PostMapping("/gen-code")
	//@ApiOperationSupport(order = 6)
	//@ApiOperation(value = "代码生成", notes = "传入ids")
	public R genCode(
			//@ApiParam(value = "主键集合", required = true)
			@RequestParam String ids, @RequestParam(defaultValue = "sword") String system) {
		Collection<Code> codes = codeService.listByIds(Func.toIntList(ids));
		codes.forEach(code -> {
			BladeCodeGenerator generator = new BladeCodeGenerator();
			// 设置数据源
			Datasource datasource = datasourceService.getById(code.getDatasourceId());
			generator.setDriverName(datasource.getDriverClass());
			generator.setUrl(datasource.getUrl());
			generator.setUsername(datasource.getUsername());
			generator.setPassword(datasource.getPassword());
			// 设置基础配置
			generator.setSystemName(system);
			generator.setServiceName(code.getServiceName());
			generator.setPackageName(code.getPackageName());
			generator.setPackageDir(code.getApiPath());
			generator.setPackageWebDir(code.getWebPath());
			generator.setTablePrefix(Func.toStrArray(code.getTablePrefix()));
			generator.setIncludeTables(Func.toStrArray(code.getTableName()));
			// 设置是否继承基础业务字段
			generator.setHasSuperEntity(code.getBaseMode() == 2);
			// 设置是否开启包装器模式
			generator.setHasWrapper(code.getWrapMode() == 2);
			generator.run();
		});
		return R.success("代码生成成功");
	}

}
