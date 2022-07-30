package com.test.dsa.util.graph;

public class TwoEdgeConnectedGraphV2 {
    private int timer = 0;
    private int[] inTime;
    private int[] connections;
    private int[] used;
    private int[][] edges;
    private boolean noBridge = true;

    //time O(V + E), space O(V)
    public boolean twoEdgeConnectedGraph(int[][] edges) {
        if (edges.length == 0) {
            return true;
        }
        init(edges);
        dfs(0, 0);
        for (int use : used) {
            if (use == 0) {
                return false;
            }
        }
        return noBridge;
    }

    private void init(int[][] edges) {
        inTime = new int[edges.length];
        connections = new int[edges.length];
        used = new int[edges.length];
        this.edges = edges;
    }

    private void dfs(int v, int p) {
        used[v] = 1;
        inTime[v] = timer++;
        connections[v] = inTime[v];
        for (int u : edges[v]) {
            if (used[u] == 0) {
                dfs(u, v);
                connections[v] = Math.min(connections[v], connections[u]);
                if (connections[u] > inTime[v]) {
                    noBridge = false;
                }
            } else if (u != p) {
                connections[v] = Math.min(connections[v], inTime[u]);
            }
        }
    }
}
