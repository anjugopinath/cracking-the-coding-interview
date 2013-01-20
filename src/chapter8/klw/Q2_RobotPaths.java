package chapter8.klw;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Q2_RobotPaths {
  private static class PathFinder {
    private final int gridSize;
    private final Cell targetCell;

    public PathFinder(int gridSize) {
      Map<Cell, Collection<List<Cell>>> foundPaths = new HashMap<Cell, Collection<List<Cell>>>();
      this.gridSize = gridSize;
      this.targetCell = new Cell(this.gridSize - 1, this.gridSize - 1);
    }

    public Collection<List<Cell>> find() {
      return findPathsToTargetCell(new Cell(0, 0));
    }

    private Collection<List<Cell>> findPathsToTargetCell(Cell fromCell) {
      if (fromCell.x >= gridSize || fromCell.y >= gridSize) {
        return null;
      }
      Cell eastCell = new Cell(fromCell.x + 1, fromCell.y);
      Collection<List<Cell>> eastPaths = findPathsToTargetCell(eastCell);
      Cell southCell = new Cell(fromCell.x, fromCell.y + 1);
      Collection<List<Cell>> southPaths = findPathsToTargetCell(southCell);

      Collection<List<Cell>> allPaths = new LinkedList<List<Cell>>();
      if (eastPaths == null && southPaths == null) {
        if (fromCell.equals(targetCell)) {
          List<Cell> path = new LinkedList<Cell>();
          path.add(fromCell);
          allPaths.add(path);
        } else {
          return null;
        }
      } else {
        if (southPaths != null) {
          for (List<Cell> southPath : southPaths) {
            southPath.add(0, fromCell);
            allPaths.add(southPath);
          }
        }
        if (eastPaths != null) {
          for (List<Cell> eastPath : eastPaths) {
            eastPath.add(0, fromCell);
            allPaths.add(eastPath);
          }
        }
      }
      return allPaths;
    }
  }

  private static class Cell {
    private final int x, y;
    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object object) {
      Cell other = (Cell) object;
      return asString().equals(other.asString());
    }

    @Override
    public int hashCode() {
      return asString().hashCode();
    }

    private String asString() {
      return x + y + "";
    }
  }

  public static void main(String[] args) {
    testPathFinder(0);
    testPathFinder(1);
    testPathFinder(2);
    testPathFinder(3);
    testPathFinder(4);
    testPathFinder(5);
  }

  private static void testPathFinder(int gridSize) {
    PathFinder pf = new PathFinder(gridSize);
    Collection<List<Cell>> paths = pf.find();
    int numPaths = paths == null ? 0 : paths.size();
    System.out.println("Grid of size " + gridSize + " has " + numPaths + " paths");
    if (paths != null) {
      System.out.println("Paths are: " );
      for (List<Cell> path : paths) {
        String sep = "";
        for (Cell c : path) {
          System.out.printf("%s[%s,%s]", sep, c.x, c.y);
          sep = ", ";
        }
        System.out.println();
      }
    }
  }
}
