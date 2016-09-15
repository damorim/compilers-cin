package callret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Util {
  
  private final boolean COMPUTE_STDEV = true; 
  
  public static class PartitionInfo {
    public List<Stack<String>> partitions;
    public Map<String,Set<Stack<String>>> partitionToReqs;

    public PartitionInfo (List<Stack<String>> partitions,
        Map<String, Set<Stack<String>>> partitionToReqs) {
      this.partitions = partitions;
      this.partitionToReqs = partitionToReqs;
    }
  }

  public static PartitionInfo partitionStacks(List<Stack<String>> reqs,
      String meth, int k) {
    Map<String, Stack<String>> map = new HashMap<String, Stack<String>>();
    Map<String, Integer> counter = new HashMap<String, Integer>();
    Map<String, Set<Stack<String>>> partitionToReqs = new HashMap<String, Set<Stack<String>>>();
    for (Stack<String> stack : reqs) {
      for (int i = 0; i < stack.size(); i++) {
        if (stack.get(i).equals(meth)) {
          int a = i - k / 2;
          int b = i + k / 2;
          boolean pred1 = a >= 0;
          boolean pred2 = b < stack.size();
          StringBuffer key = new StringBuffer();
          Stack<String> substack = new Stack<String>();
          // System.err.println("a=" + a + " b=" + b + " size=" + stack.size());
          if (pred1 && pred2) {
            for (int j = a; j <= b; j++) {
              key.append(stack.get(j));
            }
            substack.addAll(stack.subList(a, b + 1));
          } else if (pred1) {
            for (int j = a; j < stack.size(); j++) {
              key.append(stack.get(j));
            }
            substack.addAll(stack.subList(a, stack.size()));
          } else if (pred2) {
            for (int j = 0; j < b; j++) {
              key.append(stack.get(j));
            }
            substack.addAll(stack.subList(0, b));
          } else {
            for (int j = 0; j < stack.size(); j++) {
              key.append(stack.get(j));
            }
            substack.addAll(stack);
          }
          String keyStr = key.toString();
          // System.err.println("keystr found: " + keyStr);
          Stack<String> repr = map.get(keyStr);
          if (repr == null) {
            // map.put(keyStr, stack);
            // store only the partition
            map.put(keyStr, substack);
            counter.put(keyStr, 1);
            Set<Stack<String>> reqset = new HashSet<Stack<String>>();
            reqset.add(stack);
            partitionToReqs.put(keyStr, reqset);
          } else {
            int currentTotal = counter.get(keyStr);
            counter.put(keyStr, currentTotal + 1);
            partitionToReqs.get(keyStr).add(stack);
          }
          break;
        }
      }
    }
    computeStdDev(new ArrayList<Integer>(counter.values()));
    List<Stack<String>> partitions = new ArrayList<Stack<String>>(map.values());
    return new PartitionInfo(partitions, partitionToReqs);
  }

  private static void computeStdDev(List<Integer> nStacks) {

    int total = 0;
    for (Integer i : nStacks) {
      total += i;
    }

    double mean = ((double) total) / nStacks.size();
    double aggregate = 0;

    for (Integer datapoint : nStacks) {
      double square = Math.pow((datapoint - mean), 2);
      aggregate += square;
    }

    double stdev = Math.sqrt(aggregate / nStacks.size());

    System.err.println("partition distribution:");
    System.err.println(nStacks);
    System.err.println("stdev: " + stdev);
  }

}
