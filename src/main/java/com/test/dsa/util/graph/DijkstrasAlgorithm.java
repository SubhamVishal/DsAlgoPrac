package com.test.dsa.util.graph;

import java.util.*;

public class DijkstrasAlgorithm {
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        int[] result = new int[edges.length];
        Arrays.fill(result, -1);

        PriorityQueue<Item> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        Set<Integer> visited = new HashSet<>();
        q.offer(new Item(start, 0));
        result[start] = 0;
        while (visited.size() < edges.length && !q.isEmpty()) {
            Item nextNode = q.poll();
            if (visited.contains(nextNode.id)) {
                continue;
            }
            visited.add(nextNode.id);
            result[nextNode.id] = nextNode.distance;
            for (int[] edge : edges[nextNode.id]) {
                if (!visited.contains(edge[0])) {
                    q.offer(new Item(edge[0], edge[1] + nextNode.distance));
                }
            }
        }
        return result;
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
