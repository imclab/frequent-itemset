package com.gavinmhackeling.frequentitemset;

import java.util.List;

import com.google.common.collect.Lists;

public class Node {

	private int count;
	private String value;
	private Node parent;
	private List<Node> children;
	private Node twin;
	
	public Node(String value)
	{
		this.count = 1;
		this.value = value;
		this.parent = parent;
		this.children = Lists.newArrayList();
	}

	public void increment() {
		count++;
	}
	
	public Node getChildWithValue(String value) {
		for (Node node: children)
			if (node.getValue().equals(value)) return node;
		return null;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public void addChild(Node node) {
		children.add(node);
	}
	
	public boolean hasChild(Node node) {
		return (children.contains(node)) ? true : false;
	}
	
	public boolean hasChildWithValue(String value) {
		for (Node node: children)
			if (node.getValue().equals(value)) return true;
		return false;
	}

	public Node getTwin() {
		return twin;
	}

	public void setTwin(Node twin) {
		this.twin = twin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + count;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((twin == null) ? 0 : twin.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (count != other.count)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (twin == null) {
			if (other.twin != null)
				return false;
		} else if (!twin.equals(other.twin))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [count=" + count + ", value=" + value + ", parent="
				+ parent + ", children=" + children + ", twin=" + twin + "]";
	}
	
	
	

}
