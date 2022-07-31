package com.test.dsa.util.graph;

import java.util.*;

// O(E * logV) time complexity. and space : O(V^2 + E)
public class NetworkDelay {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Item>> edges = new HashMap<>();
        for (int[] ints : times) {
            edges.putIfAbsent(ints[0], new ArrayList<>());
            edges.get(ints[0]).add(new Item(ints[1], ints[2]));
        }
        PriorityQueue<Item> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        Set<Integer> visited = new HashSet<>();
        q.offer(new Item(k, 0));
        int time = 0;
        while (visited.size() < n && !q.isEmpty()) {
            Item nextNode = q.poll();
            if (visited.contains(nextNode.id)) {
                continue;
            }
            time = Math.max(time, nextNode.distance);
            visited.add(nextNode.id);
            for (Item edge : edges.getOrDefault(nextNode.id, new ArrayList<>())) {
                if (!visited.contains(edge.id)) {
                    q.offer(new Item(edge.id, edge.distance + nextNode.distance));
                }
            }
        }
        return visited.size() == n ? time == 0 ? -1 : time : -1;
    }

    public static class Item {
        public int id;
        public int distance;

        public Item(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }
}
