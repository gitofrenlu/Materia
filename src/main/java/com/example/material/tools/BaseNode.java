package com.example.material.tools;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class BaseNode implements INode {
	protected Integer id;
	protected Integer parentId;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<INode> children = new ArrayList();

	public BaseNode() {
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public List<INode> getChildren() {
		return this.children;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setParentId(final Integer parentId) {
		this.parentId = parentId;
	}

	public void setChildren(final List<INode> children) {
		this.children = children;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BaseNode)) {
			return false;
		} else {
			BaseNode other = (BaseNode)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label47: {
					Object this$id = this.getId();
					Object other$id = other.getId();
					if (this$id == null) {
						if (other$id == null) {
							break label47;
						}
					} else if (this$id.equals(other$id)) {
						break label47;
					}

					return false;
				}

				Object this$parentId = this.getParentId();
				Object other$parentId = other.getParentId();
				if (this$parentId == null) {
					if (other$parentId != null) {
						return false;
					}
				} else if (!this$parentId.equals(other$parentId)) {
					return false;
				}

				Object this$children = this.getChildren();
				Object other$children = other.getChildren();
				if (this$children == null) {
					if (other$children != null) {
						return false;
					}
				} else if (!this$children.equals(other$children)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BaseNode;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $id = this.getId();
		 result = result * 59 + ($id == null ? 43 : $id.hashCode());
		Object $parentId = this.getParentId();
		result = result * 59 + ($parentId == null ? 43 : $parentId.hashCode());
		Object $children = this.getChildren();
		result = result * 59 + ($children == null ? 43 : $children.hashCode());
		return result;
	}

	public String toString() {
		return "BaseNode(id=" + this.getId() + ", parentId=" + this.getParentId() + ", children=" + this.getChildren() + ")";
	}
}
