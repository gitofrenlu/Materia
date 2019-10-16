
package com.example.material.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.example.material.constant.SystemConstant;
import org.springframework.web.multipart.MultipartFile;

public class BladeFile {
	private Object fileId;
	private MultipartFile file;
	private String dir;
	private String uploadPath;
	private String uploadVirtualPath;
	private String fileName;
	private String originalFileName;

	public BladeFile() {
	}

	public BladeFile(MultipartFile file, String dir) {
		this.dir = dir;
		this.file = file;
		this.fileName = file.getName();
		this.originalFileName = file.getOriginalFilename();
		this.uploadPath = BladeFileUtil.formatUrl(File.separator + SystemConstant.me().getUploadRealPath() + File.separator + dir + File.separator + DateUtil.format(new Date(), "yyyyMMdd") + File.separator + this.originalFileName);
		this.uploadVirtualPath = BladeFileUtil.formatUrl(SystemConstant.me().getUploadCtxPath().replace(SystemConstant.me().getContextPath(), "") + File.separator + dir + File.separator + DateUtil.format(new Date(), "yyyyMMdd") + File.separator + this.originalFileName);
	}

	public BladeFile(MultipartFile file, String dir, String uploadPath, String uploadVirtualPath) {
		this(file, dir);
		if (null != uploadPath) {
			this.uploadPath = BladeFileUtil.formatUrl(uploadPath);
			this.uploadVirtualPath = BladeFileUtil.formatUrl(uploadVirtualPath);
		}

	}

	public void transfer() {
		this.transfer(SystemConstant.me().isCompress());
	}

	public void transfer(boolean compress) {
		IFileProxy fileFactory = FileProxyManager.me().getDefaultFileProxyFactory();
		this.transfer(fileFactory, compress);
	}

	public void transfer(IFileProxy fileFactory, boolean compress) {
		try {
			File file = new File(this.uploadPath);
			if (null != fileFactory) {
				String[] path = fileFactory.path(file, this.dir);
				this.uploadPath = path[0];
				this.uploadVirtualPath = path[1];
				file = fileFactory.rename(file, path[0]);
			}

			File pfile = file.getParentFile();
			if (!pfile.exists()) {
				pfile.mkdirs();
			}

			this.file.transferTo(file);
			if (compress) {
				fileFactory.compress(this.uploadPath);
			}
		} catch (IOException | IllegalStateException var5) {
			var5.printStackTrace();
		}

	}

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getUploadPath() {
		return this.uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUploadVirtualPath() {
		return this.uploadVirtualPath;
	}

	public void setUploadVirtualPath(String uploadVirtualPath) {
		this.uploadVirtualPath = uploadVirtualPath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return this.originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
}
