package com.test.dsa.util.graph;

import java.util.*;

public class Solution {
    public List<Integer> getTaskOrdering(List<List<Integer>> taskDependencies) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        Map<Integer, Set<Integer>> taskDependenciesMap = new HashMap<>();
        for (List<Integer> edge : taskDependencies) {
            int dependent = edge.get(0);
            int dependency = edge.get(1);
            if (!nodeMap.containsKey(dependent)) {
                Node node = new Node(dependent, new ArrayList<>());
                nodeMap.put(dependent, node);
            }
            if (!nodeMap.containsKey(dependency)) {
                Node node = new Node(dependency, new ArrayList<>());
                nodeMap.put(dependency, node);
            }
            Set<Integer> dependentSet = taskDependenciesMap.getOrDefault(dependent, new HashSet<>());
            dependentSet.add(dependency);
            taskDependenciesMap.put(dependent, dependentSet);
            Set<Integer> dependencySet = taskDependenciesMap.getOrDefault(dependency, new HashSet<>());
            taskDependenciesMap.put(dependency, dependencySet);
            nodeMap.get(dependency).dependents.add(dependent);
        }
        List<Integer> result = new ArrayList<>();
        while (result.size() != nodeMap.size()) {
            int node = getTaskWithNoDependency(taskDependenciesMap);
            if (node == -1) {
                return List.of(-1);
            }
            result.add(node);
            removeDependency(taskDependenciesMap, nodeMap.get(node).dependents, node);
        }
        return result;
    }


    private int getTaskWithNoDependency(Map<Integer, Set<Integer>> taskDependenciesMap) {
        for (Integer node : taskDependenciesMap.keySet()) {
            if (taskDependenciesMap.get(node).size() == 0) {
                taskDependenciesMap.remove(node);
                return node;
            }
        }
        return -1;
    }

    private void removeDependency(Map<Integer, Set<Integer>> taskDependenciesMap, List<Integer> dependents, int dependency) {
        for (int dependent : dependents) {
            taskDependenciesMap.get(dependent).remove(dependency);
        }
    }

    public static void main(String[] args) {
        //[1, 0], [2, 0], [3, 1], [3, 2]] 

        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 0));
        input.add(Arrays.asList(0, 1));
        input.add(Arrays.asList(3, 1));
        input.add(Arrays.asList(3, 2));
        Solution soln = new Solution();
        System.out.println(soln.getTaskOrdering(input));

    }
}

class Node {
    public int value;
    public List<Integer> dependents;

    public Node(int value, List<Integer> dependents) {
        this.value = value;
        this.dependents = dependents;
    }
}