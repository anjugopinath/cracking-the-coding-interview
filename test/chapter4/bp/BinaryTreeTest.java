/**
 * 
 */
package chapter4.bp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import chapter4.bp.BinaryTree;
import chapter4.bp.BinaryTree.Balanced;
import chapter4.bp.BinaryTree.Node;


/**
 * @author bailey
 *
 */
public class BinaryTreeTest {
	
	@Test
	public void testCreateFromArrayHappy() {
		BinaryTree<Integer> bt = BinaryTree.createFromArray(new Integer[]{3, 4 ,5});
		assertTrue(bt.isBalanced().balanced);
	}
	
	@Test
	public void testFindNode() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5);
		Node<Integer> node5 = new Node<Integer>(5);
		assertEquals(bt.findNode(node5), node5);
	}
	
	@Test
	public void testFindNodeLeft() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4);
		Node<Integer> node = new Node<Integer>(4);
		assertEquals(node, bt.findNode(node));
	}
	
	@Test
	public void testFindNodeRight() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6);
		Node<Integer> node = new Node<Integer>(6);
		assertEquals(node, bt.findNode(node));
	}
	
	@Test
	public void testFindNodeRightComplex() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6).add(8).add(7);
		Node<Integer> node = new Node<Integer>(7);
		assertEquals(node, bt.findNode(node));
	}
	
	@Test
	public void testEqualsWithin_RootNode() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6);
		assertTrue(bt.equalsWithin(new Node<Integer>(5), bt.root));
	}
	
	@Test
	public void testEqualsWithin_LeftNode() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6);
		assertTrue(bt.equalsWithin(new Node<Integer>(4), bt.root.left));
	}
	
	@Test
	public void testEqualsWithin_NotEqual() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6);
		assertFalse(bt.equalsWithin(new Node<Integer>(4), bt.root));
	}
	
	@Test
	public void testEqualsWithin_ComplexEquals() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6).add(8).add(7).add(9).add(11).add(10);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(5).add(6).add(8).add(9);
		assertTrue(bt.equalsWithin(otherTree.root, bt.root));
	}
	
	@Test
	public void testEqualsWithin_ComplexNotEquals() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(6).add(8).add(7).add(9).add(11).add(10);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(5).add(6).add(8).add(10);
		assertFalse(bt.equalsWithin(otherTree.root, bt.root));
	}
	
	@Test
	public void testContainsTree_oneNode() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(5);
		assertTrue(bt.containsTree(otherTree));
	}
	
	@Test
	public void testContainsTree_TwoNodes() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(5).add(6);
		assertTrue(bt.containsTree(otherTree));
	}
	
	@Test
	public void testContainsTree_NotContains() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(5).add(6).add(7);
		assertFalse(bt.containsTree(otherTree));
	}
	
	@Test
	public void testContainsTree_ComplexContains() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(9).add(7).add(8);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(9).add(7).add(8);
		assertTrue(bt.containsTree(otherTree));
	}
	
	@Test
	public void testContainsTree_ComplexNotContains() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(4).add(9).add(7).add(8);
		BinaryTree<Integer> otherTree = new BinaryTree<Integer>();
		otherTree.add(4).add(3);
		assertFalse(bt.containsTree(otherTree));
	}
	
	@Test
	public void testFindInsertionPoint_lower() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(6);
		node5.right = node6;
		Node<Integer> result = bt.findInsertionPoint(node5, 4);
		Assert.assertEquals(node5, result);
	}
	
	@Test
	public void testFindInsertionPoint_higher() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(6);
		node5.right = node6;
		Node<Integer> result = bt.findInsertionPoint(node5, 7);
		Assert.assertEquals(node6, result);
	}
	
	@Test
	public void testFindInsertionPoint_higherInTheMiddle() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node7 = new Node<Integer>(7);
		node5.right = node7;
		Node<Integer> result = bt.findInsertionPoint(node5, 6);
		assertEquals(node7, result);
	}
	
	@Test
	public void testAddRoot() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		assertEquals(bt.add(5).root, bt.root);
	}
	
	@Test
	public void testAddRight() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6);
		assertEquals(bt.root.right, new Node<Integer>(6));
	}
	
	@Test
	public void testAddLeft() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6).add(4);
		assertEquals(bt.root.left, new Node<Integer>(4));
		assertEquals(bt.root.right, new Node<Integer>(6));	
	}
	
	@Test
	public void testAddLeftAndLeft() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6).add(4).add(3);
		assertEquals(bt.root.left, new Node<Integer>(4));
		assertEquals(bt.root.right, new Node<Integer>(6));	
		assertEquals(bt.root.left.left, new Node<Integer>(3));
	}
	
	@Test
	public void testAddLeftThenRight() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(6).add(3).add(4);
		assertEquals(bt.root.left.value.intValue(), 3);
		assertEquals(bt.root.right.value.intValue(), 6);	
		assertEquals(bt.root.left.right.value.intValue(), 4);
	}
	
	@Test
	public void testAddRightThenLeft() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(5).add(3).add(7).add(6);
		assertEquals(bt.root.left.value.intValue(), 3);
		assertEquals(bt.root.right.value.intValue(), 7);	
		assertEquals(bt.root.right.left.value.intValue(), 6);
	}
	
	@Test
	public void testIsBalanced_happy() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(5).add(4).add(6);
		
		Balanced balanced = binaryTree.isBalanced();
		assertNotNull(balanced);
		assertEquals(balanced.height, 1);
	}
	
	@Test
	public void testIsBalanced_oneNodeOffIsStillBalanced() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(5).add(4).add(6).add(3);
		
		Balanced balanced = binaryTree.isBalanced();
		assertNotNull(balanced);
		assertEquals(balanced.height, 2);
	}
	
	@Test
	public void testIsBalanced_Depth2() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(5).add(4).add(6).add(3).add(7);
		
		Balanced balanced = binaryTree.isBalanced();
		assertNotNull(balanced);
		assertEquals(balanced.height, 2);
	}
	
	@Test
	public void testIsBalanced_notBalanced() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(4).add(5).add(6);
		
		assertNull(binaryTree.isBalanced());
	}
	
	@Test
	public void testIsBalanced_oneNode() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(4);
		Balanced balanced = binaryTree.isBalanced();
		assertNotNull(balanced);
		assertEquals(balanced.height, 0);
	}
}
