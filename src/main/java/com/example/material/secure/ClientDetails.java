package com.example.material.secure;

public class ClientDetails implements IClientDetails {
	// ("客户端id")
	private String clientId;
	//("客户端密钥")
	private String clientSecret;
	//("令牌过期秒数" 1小时)
	private Integer accessTokenValidity;
	//("刷新令牌过期秒数")
	private Integer refreshTokenValidity;

	public ClientDetails() {
	}

	public String getClientId() {
		return this.clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	public Integer getAccessTokenValidity() {
		return this.accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(final String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setAccessTokenValidity(final Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public void setRefreshTokenValidity(final Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof ClientDetails)) {
			return false;
		} else {
			ClientDetails other = (ClientDetails)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label59: {
					Object this$clientId = this.getClientId();
					Object other$clientId = other.getClientId();
					if (this$clientId == null) {
						if (other$clientId == null) {
							break label59;
						}
					} else if (this$clientId.equals(other$clientId)) {
						break label59;
					}

					return false;
				}

				Object this$clientSecret = this.getClientSecret();
				Object other$clientSecret = other.getClientSecret();
				if (this$clientSecret == null) {
					if (other$clientSecret != null) {
						return false;
					}
				} else if (!this$clientSecret.equals(other$clientSecret)) {
					return false;
				}

				Object this$accessTokenValidity = this.getAccessTokenValidity();
				Object other$accessTokenValidity = other.getAccessTokenValidity();
				if (this$accessTokenValidity == null) {
					if (other$accessTokenValidity != null) {
						return false;
					}
				} else if (!this$accessTokenValidity.equals(other$accessTokenValidity)) {
					return false;
				}

				Object this$refreshTokenValidity = this.getRefreshTokenValidity();
				Object other$refreshTokenValidity = other.getRefreshTokenValidity();
				if (this$refreshTokenValidity == null) {
					if (other$refreshTokenValidity != null) {
						return false;
					}
				} else if (!this$refreshTokenValidity.equals(other$refreshTokenValidity)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof ClientDetails;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $clientId = this.getClientId();
		result = result * 59 + ($clientId == null ? 43 : $clientId.hashCode());
		Object $clientSecret = this.getClientSecret();
		result = result * 59 + ($clientSecret == null ? 43 : $clientSecret.hashCode());
		Object $accessTokenValidity = this.getAccessTokenValidity();
		result = result * 59 + ($accessTokenValidity == null ? 43 : $accessTokenValidity.hashCode());
		Object $refreshTokenValidity = this.getRefreshTokenValidity();
		result = result * 59 + ($refreshTokenValidity == null ? 43 : $refreshTokenValidity.hashCode());
		return result;
	}

	public String toString() {
		return "ClientDetails(clientId=" + this.getClientId() + ", clientSecret=" + this.getClientSecret() + ", accessTokenValidity=" + this.getAccessTokenValidity() + ", refreshTokenValidity=" + this.getRefreshTokenValidity() + ")";
	}
}
