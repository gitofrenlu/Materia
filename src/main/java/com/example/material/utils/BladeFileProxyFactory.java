

package com.example.material.utils;

import com.example.material.constant.SystemConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;


public class BladeFileProxyFactory implements IFileProxy {
	public BladeFileProxyFactory() {
	}

	public File rename(File f, String path) {
		File dest = new File(path);
		f.renameTo(dest);
		return dest;
	}

	public String[] path(File f, String dir) {
		long time = System.nanoTime();
		StringBuilder uploadPath = (new StringBuilder()).append(getFileDir(dir, SystemConstant.me().getUploadRealPath())).append(time).append(getFileExt(f.getName()));
		StringBuilder virtualPath = (new StringBuilder()).append(getFileDir(dir, SystemConstant.me().getUploadCtxPath())).append(time).append(getFileExt(f.getName()));
		return new String[]{BladeFileUtil.formatUrl(uploadPath.toString()), BladeFileUtil.formatUrl(virtualPath.toString())};
	}

	public static String getFileExt(String fileName) {
		return !fileName.contains(".") ? ".jpg" : fileName.substring(fileName.lastIndexOf("."));
	}

	public static String getFileDir(String dir, String saveDir) {
		StringBuilder newFileDir = new StringBuilder();
		newFileDir.append(saveDir).append(File.separator).append(dir).append(File.separator).append(DateUtil.format(new Date(), "yyyyMMdd")).append(File.separator);
		return newFileDir.toString();
	}

	public void compress(String path) {
		try {
			ImageUtil.zoomScale(ImageUtil.readImage(path), new FileOutputStream(new File(path)), (String)null, SystemConstant.me().getCompressScale(), SystemConstant.me().isCompressFlag());
		} catch (FileNotFoundException var3) {
			var3.printStackTrace();
		}

	}
}
