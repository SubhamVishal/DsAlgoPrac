package com.test.dsa.util.graph;

import java.util.ArrayList;
import java.util.List;

public class FindRedundantConnections {
    public int[] findRedundantConnection(int[][] edges) {
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        for (int i = 0; i <= edges.length; i++) {
            parent.add(i);
            rank.add(1);
        }

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1], parent, rank)) {
                return edge;
            }
        }
        return null;
    }

    public int find(int n, List<Integer> parentList) {
        int parent = parentList.get(n);
        while (parent != parentList.get(parent)) {
            parentList.set(parent, parentList.get(parent));
            parent = parentList.get(parent);
        }
        return parent;
    }

    public boolean union(int n1, int n2, List<Integer> parentList, List<Integer> rank) {
        int p1 = find(n1, parentList);
        int p2 = find(n2, parentList);
        if (p1 == p2) {
            return false;
        }
        if (rank.get(p1) > rank.get(p2)) {
            parentList.set(p2, p1);
            rank.set(p1, rank.get(p2) + rank.get(p1));
        } else {
            parentList.set(p1, p2);
            rank.set(p2, rank.get(p2) + rank.get(p1));
        }
        return true;
    }
}
