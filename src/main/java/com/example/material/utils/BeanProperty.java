package com.example.material.utils;

public class BeanProperty {

	private final String name;
	private final Class<?> type;

	public String getName() {
		return this.name;
	}

	public Class<?> getType() {
		return this.type;
	}

	public BeanProperty(final String name, final Class<?> type) {
		this.name = name;
		this.type = type;
	}
}
