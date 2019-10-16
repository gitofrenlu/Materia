

package com.example.material.utils;

import java.io.OutputStream;

public interface IMultiOutputStream {
	OutputStream buildOutputStream(Integer... params);
}
