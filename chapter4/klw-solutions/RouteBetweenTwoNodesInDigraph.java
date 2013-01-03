import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

/*
Given a directed graph, design an algorithm to find out whether there is a route be-
tween two nodes.
*/
public class RouteBetweenTwoNodesInDigraph {
  private static final class Node {
    private final List<Node> children = new LinkedList<Node>();

    public void addChild(Node child) {
      children.add(child);
    }

    public List<Node> getChildren() {
      return children;
    }
  }

  private static boolean hasRoute(Node node1, Node node2) {
    if (node1 == null || node2 == null) {
      throw new IllegalArgumentException("node1 and node2 cannot be null");
    }

    if (node1 == node2) {
      return true;
    }
    List<Node> children = node1.getChildren();
    for (Node child : children) {
      return hasRoute(child, node2);
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    Node startNode = new Node();
    Node endNode = new Node();
    if (!hasRoute(startNode, endNode)) {
      System.out.println("Test 1 passed");
    } else {
      throw new RuntimeException("Test 1 failed");
    }

    startNode.addChild(endNode);
    if (hasRoute(startNode, endNode)) {
      System.out.println("Test 2 passed");
    } else {
      throw new RuntimeException("Test 2 failed");
    }

    startNode = new Node();
    Node n1 = new Node();
    startNode.addChild(n1);
    endNode = new Node();
    if (!hasRoute(startNode, endNode)) {
      System.out.println("Test 3 passed");
    } else {
      throw new RuntimeException("Test 3 failed");
    }

    n1.addChild(endNode);
    if (hasRoute(startNode, endNode)) {
      System.out.println("Test 4 passed");
    } else {
      throw new RuntimeException("Test 4 failed");
    }
  }
}
