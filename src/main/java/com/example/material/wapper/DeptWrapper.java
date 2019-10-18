
package com.example.material.wapper;

import com.example.material.constant.CommonConstant;
import com.example.material.model.Dept;
import com.example.material.model.DeptVO;
import com.example.material.service.IDeptService;
import com.example.material.tools.ForestNodeMerger;
import com.example.material.tools.INode;
import com.example.material.utils.BeanUtil;
import com.example.material.utils.Func;
import com.example.material.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;



/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@Component
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO> {

	@Autowired
	private  IDeptService deptService;

	private static DeptWrapper wapper;

	//初始化静态参数
	@PostConstruct
	public void init() {
		wapper = this;
		wapper.deptService = this.deptService;
	}

	public static DeptWrapper build() {
		return new DeptWrapper();
	}


	@Override
	public DeptVO entityVO(Dept dept) {
		DeptVO deptVO = BeanUtil.copy(dept, DeptVO.class);
		if (Func.equals(dept.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptService.getById(dept.getParentId());
			deptVO.setParentName(parent.getDeptName());
		}
		return deptVO;
	}

	public List<INode> listNodeVO(List<Dept> list) {
		List<INode> collect = list.stream().map(dept -> BeanUtil.copy(dept, DeptVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
