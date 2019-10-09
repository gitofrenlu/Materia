package com.example.material.model;

public class AuthInfo {
	// ("令牌")
	private String accessToken;
	// ("令牌类型")
	private String tokenType;
	// ("刷新令牌")
	private String refreshToken;
	// ("头像")
	private String avatar = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
	// ("角色名")
	private String authority;
	// ("用户名")
	private String userName;
	// ("账号名")
	private String account;
	// ("过期时间")
	private long expiresIn;
	// ("许可证")
	private String license = "powered by blade";

	public AuthInfo() {
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public String getTokenType() {
		return this.tokenType;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public String getAuthority() {
		return this.authority;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getAccount() {
		return this.account;
	}

	public long getExpiresIn() {
		return this.expiresIn;
	}

	public String getLicense() {
		return this.license;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}

	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setAvatar(final String avatar) {
		this.avatar = avatar;
	}

	public void setAuthority(final String authority) {
		this.authority = authority;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public void setAccount(final String account) {
		this.account = account;
	}

	public void setExpiresIn(final long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setLicense(final String license) {
		this.license = license;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof AuthInfo)) {
			return false;
		} else {
			AuthInfo other = (AuthInfo)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label111: {
					Object this$accessToken = this.getAccessToken();
					Object other$accessToken = other.getAccessToken();
					if (this$accessToken == null) {
						if (other$accessToken == null) {
							break label111;
						}
					} else if (this$accessToken.equals(other$accessToken)) {
						break label111;
					}

					return false;
				}

				Object this$tokenType = this.getTokenType();
				Object other$tokenType = other.getTokenType();
				if (this$tokenType == null) {
					if (other$tokenType != null) {
						return false;
					}
				} else if (!this$tokenType.equals(other$tokenType)) {
					return false;
				}

				Object this$refreshToken = this.getRefreshToken();
				Object other$refreshToken = other.getRefreshToken();
				if (this$refreshToken == null) {
					if (other$refreshToken != null) {
						return false;
					}
				} else if (!this$refreshToken.equals(other$refreshToken)) {
					return false;
				}

				label90: {
					Object this$avatar = this.getAvatar();
					Object other$avatar = other.getAvatar();
					if (this$avatar == null) {
						if (other$avatar == null) {
							break label90;
						}
					} else if (this$avatar.equals(other$avatar)) {
						break label90;
					}

					return false;
				}

				label83: {
					Object this$authority = this.getAuthority();
					Object other$authority = other.getAuthority();
					if (this$authority == null) {
						if (other$authority == null) {
							break label83;
						}
					} else if (this$authority.equals(other$authority)) {
						break label83;
					}

					return false;
				}

				Object this$userName = this.getUserName();
				Object other$userName = other.getUserName();
				if (this$userName == null) {
					if (other$userName != null) {
						return false;
					}
				} else if (!this$userName.equals(other$userName)) {
					return false;
				}

				Object this$account = this.getAccount();
				Object other$account = other.getAccount();
				if (this$account == null) {
					if (other$account != null) {
						return false;
					}
				} else if (!this$account.equals(other$account)) {
					return false;
				}

				if (this.getExpiresIn() != other.getExpiresIn()) {
					return false;
				} else {
					Object this$license = this.getLicense();
					Object other$license = other.getLicense();
					if (this$license == null) {
						if (other$license != null) {
							return false;
						}
					} else if (!this$license.equals(other$license)) {
						return false;
					}

					return true;
				}
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof AuthInfo;
	}

	public int hashCode() {
		int result = 1;
		Object $accessToken = this.getAccessToken();
		result = result * 59 + ($accessToken == null ? 43 : $accessToken.hashCode());
		Object $tokenType = this.getTokenType();
		result = result * 59 + ($tokenType == null ? 43 : $tokenType.hashCode());
		Object $refreshToken = this.getRefreshToken();
		result = result * 59 + ($refreshToken == null ? 43 : $refreshToken.hashCode());
		Object $avatar = this.getAvatar();
		result = result * 59 + ($avatar == null ? 43 : $avatar.hashCode());
		Object $authority = this.getAuthority();
		result = result * 59 + ($authority == null ? 43 : $authority.hashCode());
		Object $userName = this.getUserName();
		result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
		Object $account = this.getAccount();
		result = result * 59 + ($account == null ? 43 : $account.hashCode());
		long $expiresIn = this.getExpiresIn();
		result = result * 59 + (int)($expiresIn >>> 32 ^ $expiresIn);
		Object $license = this.getLicense();
		result = result * 59 + ($license == null ? 43 : $license.hashCode());
		return result;
	}

	public String toString() {
		return "AuthInfo(accessToken=" + this.getAccessToken() + ", tokenType=" + this.getTokenType() + ", refreshToken=" + this.getRefreshToken() + ", avatar=" + this.getAvatar() + ", authority=" + this.getAuthority() + ", userName=" + this.getUserName() + ", account=" + this.getAccount() + ", expiresIn=" + this.getExpiresIn() + ", license=" + this.getLicense() + ")";
	}
}
