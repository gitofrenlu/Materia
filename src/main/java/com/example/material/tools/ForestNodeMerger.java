package com.example.material.tools;

import java.util.List;


public class ForestNodeMerger {
	public ForestNodeMerger() {
	}

	public static <T extends INode> List<T> merge(List<T> items) {
		ForestNodeManager<T> forestNodeManager = new ForestNodeManager(items);
		items.forEach((forestNode) -> {
			if (forestNode.getParentId() != 0) {
				INode node = forestNodeManager.getTreeNodeAT(forestNode.getParentId());
				if (node != null) {
					node.getChildren().add(forestNode);
				} else {
					forestNodeManager.addParentId(forestNode.getId());
				}
			}

		});
		return forestNodeManager.getRoot();
	}
}
