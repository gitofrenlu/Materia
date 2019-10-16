

package com.example.material.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.example.material.model.BladeUser;
import com.example.material.secure.SecureUtil;
import com.example.material.utils.BladeFile;
import com.example.material.utils.BladeFileUtil;
import com.example.material.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class BladeController {
	@Autowired
	private HttpServletRequest request;

	public BladeController() {
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public BladeUser getUser() {
		return SecureUtil.getUser();
	}

	public <T> R<T> data(T data) {
		return R.data(data);
	}

	public <T> R<T> data(T data, String msg) {
		return R.data(data, msg);
	}

	public <T> R<T> data(T data, String msg, int code) {
		return R.data(code, data, msg);
	}

	public R success(String msg) {
		return R.success(msg);
	}

	public R fail(String msg) {
		return R.fail(msg);
	}

	public R status(boolean flag) {
		return R.status(flag);
	}

	public BladeFile getFile(MultipartFile file) {
		return BladeFileUtil.getFile(file);
	}

	public BladeFile getFile(MultipartFile file, String dir) {
		return BladeFileUtil.getFile(file, dir);
	}

	public BladeFile getFile(MultipartFile file, String dir, String path, String virtualPath) {
		return BladeFileUtil.getFile(file, dir, path, virtualPath);
	}

	public List<BladeFile> getFiles(List<MultipartFile> files) {
		return BladeFileUtil.getFiles(files);
	}

	public List<BladeFile> getFiles(List<MultipartFile> files, String dir) {
		return BladeFileUtil.getFiles(files, dir);
	}

	public List<BladeFile> getFiles(List<MultipartFile> files, String dir, String path, String virtualPath) {
		return BladeFileUtil.getFiles(files, dir, path, virtualPath);
	}
}
