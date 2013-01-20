/**
 * 
 */
package chapter4.bp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bailey
 *
 */
public class BinaryTree<T extends Comparable<T>> {

	Node<T> root;
	
	public static <T extends Comparable<T>> BinaryTree<T> createFromArray(T[] values) {
		BinaryTree<T> bt = new BinaryTree<T>();
		
		if (values.length == 0) {
			return bt;
		}
		if (values.length == 1) {
			bt.add(values[0]);
			return bt;
		}
		
		Arrays.sort(values);
		int midPoint = values.length / 2;
		int otherMidPoint = midPoint - 1;
		
		int i, j;
		for (i = midPoint, j = otherMidPoint; i < values.length && j >= 0; i++, j--) {
			bt.add(values[i]);
			bt.add(values[j]);
		}
		
		if (values.length % 2 != 0) {
			bt.add(values[i]);
		}
		
		return bt;
	}
	
	public boolean containsTree(BinaryTree<T> binaryTree) {
		Node<T> subTreeRoot = findNode(binaryTree.root);
		if (subTreeRoot == null) {
			return false;
		}
		
		return equalsWithin(binaryTree.root, subTreeRoot);
	}
	
	Node<T> findNode(Node<T> node) {
		Node<T> currentNode = root;
		while (currentNode != null) {			
			int comparison = node.compareTo(currentNode);
			if (comparison == 0) {
				return currentNode;
			} else if (comparison < 0) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		return null;
	}
	
	boolean equalsWithin(Node<T> otherNode, Node<T> thisNode) {
		if (otherNode == null) {
			return true;
		}
		
		if (thisNode == null) {
			return false;
		}
		
		if (!otherNode.value.equals(thisNode.value)) {
			return false;
		}
		
		return equalsWithin(otherNode.left, thisNode.left) && equalsWithin(otherNode.right, thisNode.right);
	}
	
	public BinaryTree<T> add(T value) {
		Node<T> newNode = new Node<T>(value);
		if (root == null) {
			root = newNode;
			return this;
		}
		
		Node<T> node = findInsertionPoint(root, value);
		int comparison = value.compareTo(node.value);
		if (comparison == 0) {
			newNode.left = node.left;
			newNode.right = node.right;
			node.left = null;
			node.right = newNode;
		} else if (comparison < 0) { // node is smaller than newNode
			newNode.left = node.left;
			node.left = newNode;
		} else { // node is greater than newNode
			newNode.right = node.right;
			node.right = newNode;
		}
		
		return this;
	}
	
	Node<T> findInsertionPoint(Node<T> node, T value) {
		while (node != null) {
			Node<T> next = node;
			int comparison = value.compareTo(next.value);
			if (comparison >= 0) {
				next = next.right;
			} else {
				next = next.left;
			}
			if (next != null) {
				node = next;
			} else {
				return node;
			}
		}
		
		throw new IllegalStateException("At the bottom of tree.");
	}
	
	public Balanced isBalanced() {
		Map<Node<T>, Integer> nodeHeights = new HashMap<BinaryTree.Node<T>, Integer>();
		boolean balanced = isHeightBalanced(root, nodeHeights);
		if (balanced) {
			Balanced balancedObj = new Balanced();
			balancedObj.balanced = balanced;
			balancedObj.height = Math.max(getHeight(nodeHeights, root) - 1, 0);
			return  balancedObj;
		}
		return null;
	}
	
	boolean isHeightBalanced(Node<T> node, Map<Node<T>, Integer> nodeHeights) {
		if (node == null) {
			return true;
		}
		
		if (node.left == null && node.right == null) { // at leaf
			nodeHeights.put(node, 1);
			return true;
		}
		
		boolean leftBalanced = isHeightBalanced(node.left, nodeHeights);
		boolean rightBalanced = isHeightBalanced(node.right, nodeHeights);
		int leftHeight = getHeight(nodeHeights, node.left);
		int rightHeight = getHeight(nodeHeights, node.right);
		
		boolean balanced = (leftBalanced && rightBalanced
				&& Math.abs(leftHeight - rightHeight) <= 1);
		if (balanced) {
			nodeHeights.put(node, Math.max(leftHeight, rightHeight) + 1);	
		}
		
		return balanced;
	}
	
	private static <T extends Comparable<T>> int getHeight(Map<Node<T>, Integer> nodeHeights, Node<T> node) {
		Integer height = nodeHeights.get(node); 
		return height == null ? 0 : height;
	}
	
	static class Balanced {
		public boolean balanced;
		public int height;
	}
	
	static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
		public Node<T> left;
		public Node<T> right;
		public final T value;
		
		public Node(T value) {
			this.value = value;
		}
		
		@Override
		public int compareTo(Node<T> o) {
			return value.compareTo(o.value);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Node) {
				Node<T> other = (Node<T>) obj;
				return value.equals(other.value);
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return value.hashCode();
		}
		
		@Override
		public String toString() {
			return value.toString();
		}
	}
}
