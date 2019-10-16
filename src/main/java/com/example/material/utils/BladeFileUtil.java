

package com.example.material.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class BladeFileUtil {
	private static HashMap<String, String> extMap = new HashMap();
	private static String IS_DIR = "is_dir";
	private static String FILE_NAME = "filename";
	private static String FILE_SIZE = "filesize";
	private static String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

	public BladeFileUtil() {
	}

	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public static boolean testExt(String dir, String fileName) {
		String fileExt = getFileExt(fileName);
		String ext = (String)extMap.get(dir);
		return !StringUtil.isBlank(ext) && ext.indexOf(fileExt) != -1;
	}

	public static String formatUrl(String url) {
		return url.replaceAll("\\\\", "/");
	}

	public static BladeFile getFile(MultipartFile file) {
		return getFile(file, "image", (String)null, (String)null);
	}

	public static BladeFile getFile(MultipartFile file, String dir) {
		return getFile(file, dir, (String)null, (String)null);
	}

	public static BladeFile getFile(MultipartFile file, String dir, String path, String virtualPath) {
		return new BladeFile(file, dir, path, virtualPath);
	}

	public static List<BladeFile> getFiles(List<MultipartFile> files) {
		return getFiles(files, "image", (String)null, (String)null);
	}

	public static List<BladeFile> getFiles(List<MultipartFile> files, String dir) {
		return getFiles(files, dir, (String)null, (String)null);
	}

	public static List<BladeFile> getFiles(List<MultipartFile> files, String dir, String path, String virtualPath) {
		List<BladeFile> list = new ArrayList();
		Iterator var5 = files.iterator();

		while(var5.hasNext()) {
			MultipartFile file = (MultipartFile)var5.next();
			list.add(new BladeFile(file, dir, path, virtualPath));
		}

		return list;
	}

	static {
		extMap.put("image", ".gif,.jpg,.jpeg,.png,.bmp,.JPG,.JPEG,.PNG");
		extMap.put("flash", ".swf,.flv");
		extMap.put("media", ".swf,.flv,.mp3,.mp4,.wav,.wma,.wmv,.mid,.avi,.mpg,.asf,.rm,.rmvb");
		extMap.put("file", ".doc,.docx,.xls,.xlsx,.ppt,.htm,.html,.txt,.zip,.rar,.gz,.bz2");
		extMap.put("allfile", ".gif,.jpg,.jpeg,.png,.bmp,.swf,.flv,.mp3,.mp4,.wav,.wma,.wmv,.mid,.avi,.mpg,.asf,.rm,.rmvb,.doc,.docx,.xls,.xlsx,.ppt,.htm,.html,.txt,.zip,.rar,.gz,.bz2");
	}

	public static class TypeComparator implements Comparator {
		public TypeComparator() {
		}

		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if ((Boolean)hashA.get(BladeFileUtil.IS_DIR) && !(Boolean)hashB.get(BladeFileUtil.IS_DIR)) {
				return -1;
			} else {
				return !(Boolean)hashA.get(BladeFileUtil.IS_DIR) && (Boolean)hashB.get(BladeFileUtil.IS_DIR) ? 1 : ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}

	public static class SizeComparator implements Comparator {
		public SizeComparator() {
		}

		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if ((Boolean)hashA.get(BladeFileUtil.IS_DIR) && !(Boolean)hashB.get(BladeFileUtil.IS_DIR)) {
				return -1;
			} else if (!(Boolean)hashA.get(BladeFileUtil.IS_DIR) && (Boolean)hashB.get(BladeFileUtil.IS_DIR)) {
				return 1;
			} else if ((Long)hashA.get(BladeFileUtil.FILE_SIZE) > (Long)hashB.get(BladeFileUtil.FILE_SIZE)) {
				return 1;
			} else {
				return (Long)hashA.get(BladeFileUtil.FILE_SIZE) < (Long)hashB.get(BladeFileUtil.FILE_SIZE) ? -1 : 0;
			}
		}
	}

	public static class NameComparator implements Comparator {
		public NameComparator() {
		}

		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if ((Boolean)hashA.get(BladeFileUtil.IS_DIR) && !(Boolean)hashB.get(BladeFileUtil.IS_DIR)) {
				return -1;
			} else {
				return !(Boolean)hashA.get(BladeFileUtil.IS_DIR) && (Boolean)hashB.get(BladeFileUtil.IS_DIR) ? 1 : ((String)hashA.get(BladeFileUtil.FILE_NAME)).compareTo((String)hashB.get(BladeFileUtil.FILE_NAME));
			}
		}
	}

	public static enum FileSort {
		size,
		type,
		name;

		private FileSort() {
		}

		public static BladeFileUtil.FileSort of(String sort) {
			try {
				return valueOf(sort);
			} catch (Exception var2) {
				return name;
			}
		}
	}
}
