package com.example.material.model;

import java.io.Serializable;

public class BladeUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clientId;

	private Integer userId;

	private String tenantId;

	private String userName;

	private String account;

	private String roleId;

	private String roleName;

	public BladeUser() {
	}

	public String getClientId() {
		return this.clientId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public String getTenantId() {
		return this.tenantId;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getAccount() {
		return this.account;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public void setAccount(final String account) {
		this.account = account;
	}

	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BladeUser)) {
			return false;
		} else {
			BladeUser other = (BladeUser)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label95: {
					Object this$clientId = this.getClientId();
					Object other$clientId = other.getClientId();
					if (this$clientId == null) {
						if (other$clientId == null) {
							break label95;
						}
					} else if (this$clientId.equals(other$clientId)) {
						break label95;
					}

					return false;
				}

				Object this$userId = this.getUserId();
				Object other$userId = other.getUserId();
				if (this$userId == null) {
					if (other$userId != null) {
						return false;
					}
				} else if (!this$userId.equals(other$userId)) {
					return false;
				}

				Object this$tenantId = this.getTenantId();
				Object other$tenantId = other.getTenantId();
				if (this$tenantId == null) {
					if (other$tenantId != null) {
						return false;
					}
				} else if (!this$tenantId.equals(other$tenantId)) {
					return false;
				}

				label74: {
					Object this$userName = this.getUserName();
					Object other$userName = other.getUserName();
					if (this$userName == null) {
						if (other$userName == null) {
							break label74;
						}
					} else if (this$userName.equals(other$userName)) {
						break label74;
					}

					return false;
				}

				label67: {
					Object this$account = this.getAccount();
					Object other$account = other.getAccount();
					if (this$account == null) {
						if (other$account == null) {
							break label67;
						}
					} else if (this$account.equals(other$account)) {
						break label67;
					}

					return false;
				}

				Object this$roleId = this.getRoleId();
				Object other$roleId = other.getRoleId();
				if (this$roleId == null) {
					if (other$roleId != null) {
						return false;
					}
				} else if (!this$roleId.equals(other$roleId)) {
					return false;
				}

				Object this$roleName = this.getRoleName();
				Object other$roleName = other.getRoleName();
				if (this$roleName == null) {
					if (other$roleName != null) {
						return false;
					}
				} else if (!this$roleName.equals(other$roleName)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BladeUser;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $clientId = this.getClientId();
		result = result * 59 + ($clientId == null ? 43 : $clientId.hashCode());
		Object $userId = this.getUserId();
		result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
		Object $tenantId = this.getTenantId();
		result = result * 59 + ($tenantId == null ? 43 : $tenantId.hashCode());
		Object $userName = this.getUserName();
		result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
		Object $account = this.getAccount();
		result = result * 59 + ($account == null ? 43 : $account.hashCode());
		Object $roleId = this.getRoleId();
		result = result * 59 + ($roleId == null ? 43 : $roleId.hashCode());
		Object $roleName = this.getRoleName();
		result = result * 59 + ($roleName == null ? 43 : $roleName.hashCode());
		return result;
	}

	public String toString() {
		return "BladeUser(clientId=" + this.getClientId() + ", userId=" + this.getUserId() + ", tenantId=" + this.getTenantId() + ", userName=" + this.getUserName() + ", account=" + this.getAccount() + ", roleId=" + this.getRoleId() + ", roleName=" + this.getRoleName() + ")";
	}
}
