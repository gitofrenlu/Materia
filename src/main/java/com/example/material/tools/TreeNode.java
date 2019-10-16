package com.example.material.tools;

public class TreeNode extends BaseNode{
	private String title;
	private Integer key;
	private Integer value;

	public TreeNode() {
	}

	public String getTitle() {
		return this.title;
	}

	public Integer getKey() {
		return this.key;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setKey(final Integer key) {
		this.key = key;
	}

	public void setValue(final Integer value) {
		this.value = value;
	}

	public String toString() {
		return "TreeNode(title=" + this.getTitle() + ", key=" + this.getKey() + ", value=" + this.getValue() + ")";
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof TreeNode)) {
			return false;
		} else {
			TreeNode other = (TreeNode)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label47: {
					Object this$title = this.getTitle();
					Object other$title = other.getTitle();
					if (this$title == null) {
						if (other$title == null) {
							break label47;
						}
					} else if (this$title.equals(other$title)) {
						break label47;
					}

					return false;
				}

				Object this$key = this.getKey();
				Object other$key = other.getKey();
				if (this$key == null) {
					if (other$key != null) {
						return false;
					}
				} else if (!this$key.equals(other$key)) {
					return false;
				}

				Object this$value = this.getValue();
				Object other$value = other.getValue();
				if (this$value == null) {
					if (other$value != null) {
						return false;
					}
				} else if (!this$value.equals(other$value)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof TreeNode;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $title = this.getTitle();
		 result = result * 59 + ($title == null ? 43 : $title.hashCode());
		Object $key = this.getKey();
		result = result * 59 + ($key == null ? 43 : $key.hashCode());
		Object $value = this.getValue();
		result = result * 59 + ($value == null ? 43 : $value.hashCode());
		return result;
	}
}
