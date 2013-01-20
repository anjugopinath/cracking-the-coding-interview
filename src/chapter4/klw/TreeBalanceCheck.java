package chapter4.klw;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

/*
Implement a function to check if a tree is balanced. For the purposes of this question,
a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
from the root by more than one.
*/
public class TreeBalanceCheck {
  private static final class TreeNode {
    private final TreeNode leftChild, rightChild;

    public TreeNode() {
      this(null, null);
    }

    public TreeNode(TreeNode leftChild, TreeNode rightChild) {
      this.leftChild = leftChild;
      this.rightChild = rightChild;
    }
  }

  private static final class TreeNodeWithLevel {
    private final TreeNode node;
    private final int level;

    public TreeNodeWithLevel(TreeNode node, int level) {
      if (level <= 0) {
        throw new IllegalArgumentException("level must be a positive number");
      }
      this.node = node;
      this.level = level;
    }
  }

  private static boolean isBalanced(TreeNode root) {
    if (root == null) {
      throw new IllegalArgumentException("root is null");
    }

    Queue<TreeNodeWithLevel> queue = new LinkedList<TreeNodeWithLevel>();
    queue.add(new TreeNodeWithLevel(root, 1));
    int levelOfFirstUnbalancedNode = 0;
    while (!queue.isEmpty()) {
      TreeNodeWithLevel curr = queue.remove();
      if (levelOfFirstUnbalancedNode > 0 && curr.level > levelOfFirstUnbalancedNode + 1) {
        return false;
      }
      TreeNode leftChild = curr.node.leftChild;
      TreeNode rightChild = curr.node.rightChild;
      if (levelOfFirstUnbalancedNode == 0 && (leftChild == null || rightChild == null)) {
        levelOfFirstUnbalancedNode = curr.level;
      }
      if (leftChild != null) {
        queue.add(new TreeNodeWithLevel(leftChild, curr.level + 1));
      }
      if (rightChild != null) {
        queue.add(new TreeNodeWithLevel(rightChild, curr.level + 1));
      }
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    // Single node test
    TreeNode root = new TreeNode();
    if (isBalanced(root)) {
      System.out.println("Single node test passed");
    } else {
      throw new Exception("Single node test failed");
    }

    // Single child test
    TreeNode leftChild = new TreeNode();
    root = new TreeNode(leftChild, null);
    if (isBalanced(root)) {
      System.out.println("Single child test passed");
    } else {
      throw new Exception("Single child test failed");
    }

    // Single node with multiple children test
    TreeNode rightChild = new TreeNode();
    root = new TreeNode(leftChild, rightChild);
    if (isBalanced(root)) {
      System.out.println("Multiple children test passed");
    } else {
      throw new Exception("Multiple children test failed");
    }

    // Single grandchild test
    leftChild = new TreeNode(new TreeNode(), null);
    root = new TreeNode(leftChild, rightChild);
    if (isBalanced(root)) {
      System.out.println("Single grandchild test passed");
    } else {
      throw new Exception("Single grandchild test failed");
    }

    // Single great grandchild test
    leftChild = new TreeNode(new TreeNode(new TreeNode(), null), null);
    root = new TreeNode(leftChild, rightChild);
    if (!isBalanced(root)) {
      System.out.println("Single great grandchild test passed");
    } else {
      throw new Exception("Single great grandchild test failed");
    }

    // Single great grandchild with all grandchildren present test
    rightChild = new TreeNode(new TreeNode(), null);
    root = new TreeNode(leftChild, rightChild);
    if (!isBalanced(root)) {
      System.out.println("Single great grandchild with all grandchildren present test passed");
    } else {
      throw new Exception("Single great grandchild test with all grandchildren present test failed");
    }

    // Degenerate tree test
    root = new TreeNode(new TreeNode(new TreeNode(new TreeNode(new TreeNode(), null), null), null), null);
    if (!isBalanced(root)) {
      System.out.println("Degenerate tree test passed");
    } else {
      throw new Exception("Degenerate tree test failed");
    }
  }
}
