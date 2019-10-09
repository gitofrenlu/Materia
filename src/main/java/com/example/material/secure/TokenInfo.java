
package com.example.material.secure;

public class TokenInfo {
	private String token;
	private int expire;

	public TokenInfo() {
	}

	public String getToken() {
		return this.token;
	}

	public int getExpire() {
		return this.expire;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public void setExpire(final int expire) {
		this.expire = expire;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof TokenInfo)) {
			return false;
		} else {
			TokenInfo other = (TokenInfo)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$token = this.getToken();
				Object other$token = other.getToken();
				if (this$token == null) {
					if (other$token == null) {
						return this.getExpire() == other.getExpire();
					}
				} else if (this$token.equals(other$token)) {
					return this.getExpire() == other.getExpire();
				}

				return false;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof TokenInfo;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $token = this.getToken();
		result = result * 59 + ($token == null ? 43 : $token.hashCode());
		result = result * 59 + this.getExpire();
		return result;
	}

	public String toString() {
		return "TokenInfo(token=" + this.getToken() + ", expire=" + this.getExpire() + ")";
	}
}
