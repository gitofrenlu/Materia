
package com.example.material.model;

import java.util.ArrayList;
import java.util.List;


import com.example.material.tools.INode;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 视图实体类
 *
 * @author Chill
 * @since 2018-12-24
 */
@Data
public class DictVO extends Dict implements INode {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 父节点ID
	 */
	private Integer parentId;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<INode> children;

	@Override
	public List<INode> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		return this.children;
	}

	/**
	 * 上级字典
	 */
	private String parentName;
}
