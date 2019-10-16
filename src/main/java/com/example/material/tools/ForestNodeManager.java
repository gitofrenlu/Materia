package com.example.material.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForestNodeManager<T extends INode> {
	private List<T> list;
	private List<Integer> parentIds = new ArrayList();

	public ForestNodeManager(List<T> items) {
		this.list = items;
	}

	public INode getTreeNodeAT(int id) {
		Iterator var2 = this.list.iterator();

		INode forestNode;
		do {
			if (!var2.hasNext()) {
				return null;
			}

			forestNode = (INode)var2.next();
		} while(forestNode.getId() != id);

		return forestNode;
	}

	public void addParentId(Integer parentId) {
		this.parentIds.add(parentId);
	}

	public List<T> getRoot() {
		List<T> roots = new ArrayList();
		Iterator var2 = this.list.iterator();

		while(true) {
			INode forestNode;
			do {
				if (!var2.hasNext()) {
					return roots;
				}

				forestNode = (INode)var2.next();
			} while(forestNode.getParentId() != 0 && !this.parentIds.contains(forestNode.getId()));

			roots.add((T) forestNode);
		}
	}
}
