

package com.example.material.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class BaseEntity implements Serializable {
	//@ApiModelProperty("创建人")
	private Integer createUser;
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	//@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
	//@ApiModelProperty("更新人")
	private Integer updateUser;
	@DateTimeFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	//@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;
	//@ApiModelProperty("业务状态")
	private Integer status;
	@TableLogic
	//@ApiModelProperty("是否已删除")
	private Integer isDeleted;

	public BaseEntity() {
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public LocalDateTime getUpdateTime() {
		return this.updateTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreateUser(final Integer createUser) {
		this.createUser = createUser;
	}

	public void setCreateTime(final LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public void setUpdateUser(final Integer updateUser) {
		this.updateUser = updateUser;
	}

	public void setUpdateTime(final LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public void setIsDeleted(final Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BaseEntity)) {
			return false;
		} else {
			BaseEntity other = (BaseEntity)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$createUser = this.getCreateUser();
				Object other$createUser = other.getCreateUser();
				if (this$createUser == null) {
					if (other$createUser != null) {
						return false;
					}
				} else if (!this$createUser.equals(other$createUser)) {
					return false;
				}

				Object this$createTime = this.getCreateTime();
				Object other$createTime = other.getCreateTime();
				if (this$createTime == null) {
					if (other$createTime != null) {
						return false;
					}
				} else if (!this$createTime.equals(other$createTime)) {
					return false;
				}

				Object this$updateUser = this.getUpdateUser();
				Object other$updateUser = other.getUpdateUser();
				if (this$updateUser == null) {
					if (other$updateUser != null) {
						return false;
					}
				} else if (!this$updateUser.equals(other$updateUser)) {
					return false;
				}

				label62: {
					Object this$updateTime = this.getUpdateTime();
					Object other$updateTime = other.getUpdateTime();
					if (this$updateTime == null) {
						if (other$updateTime == null) {
							break label62;
						}
					} else if (this$updateTime.equals(other$updateTime)) {
						break label62;
					}

					return false;
				}

				label55: {
					Object this$status = this.getStatus();
					Object other$status = other.getStatus();
					if (this$status == null) {
						if (other$status == null) {
							break label55;
						}
					} else if (this$status.equals(other$status)) {
						break label55;
					}

					return false;
				}

				Object this$isDeleted = this.getIsDeleted();
				Object other$isDeleted = other.getIsDeleted();
				if (this$isDeleted == null) {
					if (other$isDeleted != null) {
						return false;
					}
				} else if (!this$isDeleted.equals(other$isDeleted)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BaseEntity;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $createUser = this.getCreateUser();
		 result = result * 59 + ($createUser == null ? 43 : $createUser.hashCode());
		Object $createTime = this.getCreateTime();
		result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
		Object $updateUser = this.getUpdateUser();
		result = result * 59 + ($updateUser == null ? 43 : $updateUser.hashCode());
		Object $updateTime = this.getUpdateTime();
		result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
		Object $status = this.getStatus();
		result = result * 59 + ($status == null ? 43 : $status.hashCode());
		Object $isDeleted = this.getIsDeleted();
		result = result * 59 + ($isDeleted == null ? 43 : $isDeleted.hashCode());
		return result;
	}

	public String toString() {
		return "BaseEntity(createUser=" + this.getCreateUser() + ", createTime=" + this.getCreateTime() + ", updateUser=" + this.getUpdateUser() + ", updateTime=" + this.getUpdateTime() + ", status=" + this.getStatus() + ", isDeleted=" + this.getIsDeleted() + ")";
	}
}
