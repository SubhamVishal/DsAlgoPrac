package com.test.dsa.util.graph;

import java.util.*;

public class MinReorders {
    /**
     * start at city 0
     * recursively check its neighbors
     * count outgoing edges
     */
    public int minReorder(int n, int[][] connections) {
        Set<Pair> edges = new HashSet<>();
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        for (int i = 0; i < n; i++) {
            neighbors.put(i, new ArrayList<>());
        }
        for (int[] connection : connections) {
            edges.add(new Pair(connection[0], connection[1]));
            neighbors.get(connection[0]).add(connection[1]);
            neighbors.get(connection[1]).add(connection[0]);
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        return dfs(0, edges, neighbors, visited);
    }

    public int dfs(Integer city, Set<Pair> edges, Map<Integer, List<Integer>> neighbors, Set<Integer> visited) {
        int changes = 0;
        for (int neighbor : neighbors.get(city)) {
            if (visited.contains(neighbor)) {
                continue;
            }
            // check if this neighbor can reach city 0
            if (!edges.contains(new Pair(neighbor, city))) {
                changes += 1;
            }
            visited.add(neighbor);
            changes += dfs(neighbor, edges, neighbors, visited);
        }
        return changes;
    }


    static class Pair {
        int from;
        int to;

        public Pair(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return from == pair.from && to == pair.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
