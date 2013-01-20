import java.util.Map;
import java.util.HashMap;

public class Q1_Fibonacci {
  interface FibonacciStrategy {
    int fibonacci(int n);
  }

  static class IterativeFibonacciStrategy implements FibonacciStrategy {
    @Override
    public int fibonacci(int n) {
      if (n < 0) {
        throw new IllegalArgumentException("negative number not allowed");
      }
      if (n < 2) {
        return n;
      }
      int prev = 0, curr = 1;
      for (int i = 2; i <= n; i++) {
        int currTemp = curr;
        curr = prev + curr;
        prev = currTemp;
      }
      return curr;
    }
  }

  static class RecursiveFibonacciStrategy implements FibonacciStrategy {
    @Override
    public int fibonacci(int n) {
      if (n < 0) {
        throw new IllegalArgumentException("negative number not allowed");
      }
      if (n < 2) {
        return n;
      }
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  static class MemoizedRecursiveFibonacciStrategy implements FibonacciStrategy {
    private final Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    @Override
    public int fibonacci(int n) {
      if (n < 0) {
        throw new IllegalArgumentException("negative number not allowed");
      }
      if (n < 2) {
        return n;
      }
      Integer result = cache.get(n);
      if (result == null) {
        result = fibonacci(n - 1) + fibonacci(n - 2);
        cache.put(n, result);
      }
      return result;
    }
  }

  public static void main(String[] args) {
    testStrategy(new IterativeFibonacciStrategy());
    testStrategy(new RecursiveFibonacciStrategy());
    testStrategy(new MemoizedRecursiveFibonacciStrategy());
    System.out.println("All tests passed");
  }
  
  private static void testStrategy(FibonacciStrategy strategy) {
    assertEquals(0, strategy.fibonacci(0));
    assertEquals(1, strategy.fibonacci(1));
    assertEquals(1, strategy.fibonacci(2));
    assertEquals(2, strategy.fibonacci(3));
    assertEquals(3, strategy.fibonacci(4));
    assertEquals(5, strategy.fibonacci(5));
    assertEquals(8, strategy.fibonacci(6));
    assertEquals(13, strategy.fibonacci(7));
    assertEquals(21, strategy.fibonacci(8));
    assertEquals(34, strategy.fibonacci(9));
    assertEquals(55, strategy.fibonacci(10));
    
    long before = System.currentTimeMillis();
    System.out.println(strategy.getClass().getSimpleName() + ": fib(40) = " + strategy.fibonacci(40));
    long after = System.currentTimeMillis();
    System.out.println("Time taken for fib(40) = " + (after - before) + "ms");
  }

  private static void assertEquals(int expected, int actual) {
    if (expected != actual) {
      throw new RuntimeException("Expected = " + expected + ", actual = " + actual);
    }
  }
}
