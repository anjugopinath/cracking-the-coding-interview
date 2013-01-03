import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

/*
You have two very large binary trees: T1, with millions of nodes, and T2, with hun-
dreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
*/
public class SubtreePresence {
  private static final class TreeNode {
    private final TreeNode leftChild, rightChild;
    private final int value;

    public TreeNode(int value) {
      this(null, null, value);
    }

    public TreeNode(TreeNode leftChild, TreeNode rightChild) {
      this(leftChild, rightChild, 0);
    }

    public TreeNode(TreeNode leftChild, TreeNode rightChild, int value) {
      this.leftChild = leftChild;
      this.rightChild = rightChild;
      this.value = value;
    }
  }

  private static boolean hasSubtree(TreeNode T1, TreeNode T2) {
    if (T1 == null || T2 == null) {
      return false;
    }
    Queue<TreeNode> T1Nodes = new LinkedList<TreeNode>();
    T1Nodes.add(T1);
    while (!T1Nodes.isEmpty()) {
      TreeNode curr = T1Nodes.remove();
      if (match(curr, T2)) {
        return true;
      }
      if (curr.leftChild != null) {
        T1Nodes.add(curr.leftChild);
      }
      if (curr.rightChild != null) {
        T1Nodes.add(curr.rightChild);
      }
    }
    return false;
  }

  // pre-order traversal
  private static boolean match(TreeNode n1, TreeNode n2) {
    if (n2 == null) {
      return true;
    }
    if (n1 == null) {
      return false;
    }
    if (n1.value != n2.value) {
      return false;
    }
    return (match(n1.leftChild, n2.leftChild) && match(n1.rightChild, n2.rightChild));
  }

  public static void main(String[] args) throws Exception {
    TreeNode one = new TreeNode(1);
    TreeNode three = new TreeNode(3);
    TreeNode two = new TreeNode(one, three, 2);
    TreeNode five = new TreeNode(5);
    TreeNode seven = new TreeNode(7);
    TreeNode six = new TreeNode(five, seven, 6);
    TreeNode four = new TreeNode(two, six, 4);
    TreeNode root = four;

    // Test 1: Test subtree not present
    if (hasSubtree(root, new TreeNode(8))) {
      throw new RuntimeException("Test 1 failed");
    }

    // Test 2: Test each individual node is subtree
    for (int i : new int[] {1,2,3,4,5,6,7}) {
      if (!hasSubtree(root, new TreeNode(i))) {
        throw new RuntimeException("Test 2 failed for individual node of value " + i);
      }
    }

    // Test 3
    if (!hasSubtree(root, test3Tree())) {
      throw new RuntimeException("Test 3 failed");
    }

    // Test 4
    if (!hasSubtree(root, test4Tree())) {
      throw new RuntimeException("Test 4 failed");
    }

    // Test 5
    if (!hasSubtree(root, test5Tree())) {
      throw new RuntimeException("Test 5 failed");
    }

    // Test 6
    if (!hasSubtree(root, test6Tree())) {
      throw new RuntimeException("Test 6 failed");
    }

    // Test 7
    if (!hasSubtree(root, test7Tree())) {
      throw new RuntimeException("Test 7 failed");
    }

    // Test 8
    if (!hasSubtree(root, root)) {
      throw new RuntimeException("Test 8 failed");
    }

    System.out.println("All tests passed");
  }

  private static TreeNode test3Tree() {
    TreeNode one = new TreeNode(1);
    TreeNode three = new TreeNode(3);
    TreeNode two = new TreeNode(one, three, 2);
    return two;
  }

  private static TreeNode test4Tree() {
    TreeNode one = new TreeNode(1);
    TreeNode two = new TreeNode(one, null, 2);
    TreeNode four = new TreeNode(two, null, 4);
    return four;
  }

  private static TreeNode test5Tree() {
    TreeNode seven = new TreeNode(7);
    TreeNode six = new TreeNode(null, seven, 6);
    TreeNode four = new TreeNode(null, six, 4);
    return four;
  }

  private static TreeNode test6Tree() {
    TreeNode three = new TreeNode(3);
    TreeNode two = new TreeNode(null, three, 2);
    TreeNode four = new TreeNode(two, null, 4);
    return four;
  }

  private static TreeNode test7Tree() {
    TreeNode five = new TreeNode(5);
    TreeNode six = new TreeNode(five, null, 6);
    TreeNode four = new TreeNode(null, six, 4);
    return four;
  }
}
