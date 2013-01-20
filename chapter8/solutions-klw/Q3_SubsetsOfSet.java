import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Q3_SubsetsOfSet {
  private static <T> Collection<Set<T>> findSubsets(Set<T> set) {
    Collection<Set<T>> subsets = new LinkedList<Set<T>>();
    if (set.isEmpty()) {
      subsets.add(new HashSet<T>());
    } else {
      T remove = set.iterator().next();
      set.remove(remove);
      Collection<Set<T>> subsets2 = findSubsets(set);
      subsets.addAll(subsets2);
      for (Set<T> subset : subsets2) {
        Set<T> s = new HashSet<T>(subset);
        s.add(remove);
        subsets.add(s);
      }
    }
    return subsets;
  }
  
  public static void main(String[] args) {
    Set<Integer> set = new HashSet<Integer>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.add(4);
    set.add(5);
    Collection<Set<Integer>> subsets = findSubsets(set);
    System.out.println("subset count: " + subsets.size());
    System.out.println(subsets);
  }
}
