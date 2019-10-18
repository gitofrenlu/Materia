
package com.example.material.wapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.material.constant.CommonConstant;
import com.example.material.model.Dict;
import com.example.material.model.DictVO;
import com.example.material.service.IDictService;
import com.example.material.tools.ForestNodeMerger;
import com.example.material.tools.INode;
import com.example.material.utils.BeanUtil;
import com.example.material.utils.Func;
import com.example.material.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class DictWrapper extends BaseEntityWrapper<Dict, DictVO> {


	@Autowired
	private  IDictService dictService;

	private static DictWrapper wapper;

	//初始化静态参数
	@PostConstruct
	public void init() {
		wapper = this;
		wapper.dictService = this.dictService;
	}


	public static DictWrapper build() {
		return new DictWrapper();
	}

	@Override
	public DictVO entityVO(Dict dict) {
		DictVO dictVO = BeanUtil.copy(dict, DictVO.class);
		if (Func.equals(dict.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			dictVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dict parent = dictService.getById(dict.getParentId());
			dictVO.setParentName(parent.getDictValue());
		}
		return dictVO;
	}

	public List<INode> listNodeVO(List<Dict> list) {
		List<INode> collect = list.stream().map(dict -> BeanUtil.copy(dict, DictVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
