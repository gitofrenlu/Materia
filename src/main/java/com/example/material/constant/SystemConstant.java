package com.example.material.constant;

public class SystemConstant {
	private boolean devMode = false;
	private boolean remoteMode = false;
	private String domain = "http://localhost:8888";
	private String remotePath = System.getProperty("user.dir") + "/target/blade";
	private String uploadPath = "/upload";
	private String downloadPath = "/download";
	private boolean compress = false;
	private Double compressScale = 2.0D;
	private boolean compressFlag = false;
	private String realPath = System.getProperty("user.dir");
	private String contextPath = "/";
	private static final SystemConstant ME = new SystemConstant();

	private SystemConstant() {
	}

	public static SystemConstant me() {
		return ME;
	}

	public String getUploadRealPath() {
		return (this.remoteMode ? this.remotePath : this.realPath) + this.uploadPath;
	}

	public String getUploadCtxPath() {
		return this.contextPath + this.uploadPath;
	}

	public boolean isDevMode() {
		return this.devMode;
	}

	public boolean isRemoteMode() {
		return this.remoteMode;
	}

	public String getDomain() {
		return this.domain;
	}

	public String getRemotePath() {
		return this.remotePath;
	}

	public String getUploadPath() {
		return this.uploadPath;
	}

	public String getDownloadPath() {
		return this.downloadPath;
	}

	public boolean isCompress() {
		return this.compress;
	}

	public Double getCompressScale() {
		return this.compressScale;
	}

	public boolean isCompressFlag() {
		return this.compressFlag;
	}

	public String getRealPath() {
		return this.realPath;
	}

	public String getContextPath() {
		return this.contextPath;
	}

	public void setDevMode(final boolean devMode) {
		this.devMode = devMode;
	}

	public void setRemoteMode(final boolean remoteMode) {
		this.remoteMode = remoteMode;
	}

	public void setDomain(final String domain) {
		this.domain = domain;
	}

	public void setRemotePath(final String remotePath) {
		this.remotePath = remotePath;
	}

	public void setUploadPath(final String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void setDownloadPath(final String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public void setCompress(final boolean compress) {
		this.compress = compress;
	}

	public void setCompressScale(final Double compressScale) {
		this.compressScale = compressScale;
	}

	public void setCompressFlag(final boolean compressFlag) {
		this.compressFlag = compressFlag;
	}

	public void setRealPath(final String realPath) {
		this.realPath = realPath;
	}

	public void setContextPath(final String contextPath) {
		this.contextPath = contextPath;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof SystemConstant)) {
			return false;
		} else {
			SystemConstant other = (SystemConstant)o;
			if (!other.canEqual(this)) {
				return false;
			} else if (this.isDevMode() != other.isDevMode()) {
				return false;
			} else if (this.isRemoteMode() != other.isRemoteMode()) {
				return false;
			} else {
				label108: {
					Object this$domain = this.getDomain();
					Object other$domain = other.getDomain();
					if (this$domain == null) {
						if (other$domain == null) {
							break label108;
						}
					} else if (this$domain.equals(other$domain)) {
						break label108;
					}

					return false;
				}

				Object this$remotePath = this.getRemotePath();
				Object other$remotePath = other.getRemotePath();
				if (this$remotePath == null) {
					if (other$remotePath != null) {
						return false;
					}
				} else if (!this$remotePath.equals(other$remotePath)) {
					return false;
				}

				label94: {
					Object this$uploadPath = this.getUploadPath();
					Object other$uploadPath = other.getUploadPath();
					if (this$uploadPath == null) {
						if (other$uploadPath == null) {
							break label94;
						}
					} else if (this$uploadPath.equals(other$uploadPath)) {
						break label94;
					}

					return false;
				}

				label87: {
					Object this$downloadPath = this.getDownloadPath();
					Object other$downloadPath = other.getDownloadPath();
					if (this$downloadPath == null) {
						if (other$downloadPath == null) {
							break label87;
						}
					} else if (this$downloadPath.equals(other$downloadPath)) {
						break label87;
					}

					return false;
				}

				if (this.isCompress() != other.isCompress()) {
					return false;
				} else {
					Object this$compressScale = this.getCompressScale();
					Object other$compressScale = other.getCompressScale();
					if (this$compressScale == null) {
						if (other$compressScale != null) {
							return false;
						}
					} else if (!this$compressScale.equals(other$compressScale)) {
						return false;
					}

					if (this.isCompressFlag() != other.isCompressFlag()) {
						return false;
					} else {
						label71: {
							Object this$realPath = this.getRealPath();
							Object other$realPath = other.getRealPath();
							if (this$realPath == null) {
								if (other$realPath == null) {
									break label71;
								}
							} else if (this$realPath.equals(other$realPath)) {
								break label71;
							}

							return false;
						}

						Object this$contextPath = this.getContextPath();
						Object other$contextPath = other.getContextPath();
						if (this$contextPath == null) {
							if (other$contextPath != null) {
								return false;
							}
						} else if (!this$contextPath.equals(other$contextPath)) {
							return false;
						}

						return true;
					}
				}
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof SystemConstant;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		 result = result * 59 + (this.isDevMode() ? 79 : 97);
		result = result * 59 + (this.isRemoteMode() ? 79 : 97);
		Object $domain = this.getDomain();
		result = result * 59 + ($domain == null ? 43 : $domain.hashCode());
		Object $remotePath = this.getRemotePath();
		result = result * 59 + ($remotePath == null ? 43 : $remotePath.hashCode());
		Object $uploadPath = this.getUploadPath();
		result = result * 59 + ($uploadPath == null ? 43 : $uploadPath.hashCode());
		Object $downloadPath = this.getDownloadPath();
		result = result * 59 + ($downloadPath == null ? 43 : $downloadPath.hashCode());
		result = result * 59 + (this.isCompress() ? 79 : 97);
		Object $compressScale = this.getCompressScale();
		result = result * 59 + ($compressScale == null ? 43 : $compressScale.hashCode());
		result = result * 59 + (this.isCompressFlag() ? 79 : 97);
		Object $realPath = this.getRealPath();
		result = result * 59 + ($realPath == null ? 43 : $realPath.hashCode());
		Object $contextPath = this.getContextPath();
		result = result * 59 + ($contextPath == null ? 43 : $contextPath.hashCode());
		return result;
	}

	public String toString() {
		return "SystemConstant(devMode=" + this.isDevMode() + ", remoteMode=" + this.isRemoteMode() + ", domain=" + this.getDomain() + ", remotePath=" + this.getRemotePath() + ", uploadPath=" + this.getUploadPath() + ", downloadPath=" + this.getDownloadPath() + ", compress=" + this.isCompress() + ", compressScale=" + this.getCompressScale() + ", compressFlag=" + this.isCompressFlag() + ", realPath=" + this.getRealPath() + ", contextPath=" + this.getContextPath() + ")";
	}
}
