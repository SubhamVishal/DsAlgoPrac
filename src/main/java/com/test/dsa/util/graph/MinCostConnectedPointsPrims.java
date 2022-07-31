package com.test.dsa.util.graph;

import java.util.*;
//https://leetcode.com/problems/min-cost-to-connect-all-points
public class MinCostConnectedPointsPrims {
    public int minCostConnectedPoints(int[][] points) {
        int n = points.length;
        if(points.length == 1) {
            return points[0][1];
        }
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adj.putIfAbsent(i, new ArrayList<>());
                adj.putIfAbsent(j, new ArrayList<>());
                adj.get(i).add(new Edge(dist, j));
                adj.get(j).add(new Edge(dist, i));
            }
        }
        int result = 0;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.add(new Edge(0, 0));
        while (visited.size() < n) {
            Edge edge = pq.poll();
            if (edge == null || visited.contains(edge.toIndex)) {
                continue;
            }
            result += edge.weight;
            visited.add(edge.toIndex);
            for (Edge neighbor : adj.get(edge.toIndex)) {
                if (!visited.contains(neighbor.toIndex)) {
                    pq.add(neighbor);
                }
            }
        }
        return result;
    }

    static class Edge {
        int weight;
        int toIndex;

        public Edge(int weight, int toIndex) {
            this.weight = weight;
            this.toIndex = toIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge pair = (Edge) o;
            return weight == pair.weight && toIndex == pair.toIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, toIndex);
        }
    }
}
