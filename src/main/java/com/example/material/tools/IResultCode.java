package com.example.material.tools;

import java.io.Serializable;

public interface IResultCode extends Serializable {
	String getMessage();

	int getCode();
}
