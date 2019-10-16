package com.example.material.utils;

import java.io.File;

public interface IFileProxy {
	String[] path(File file, String dir);

	File rename(File file, String path);

	void compress(String path);
}
