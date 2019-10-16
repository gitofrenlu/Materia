package com.example.material.tools;

import java.util.List;

public interface INode {
	Integer getId();

	Integer getParentId();

	List<INode> getChildren();
}
