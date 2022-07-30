package com.test.dsa.util.graph;

import java.util.Arrays;

public class TwoEdgeConnectedGraph {
    public boolean twoEdgeConnectedGraph(int[][] edges) {
        if (edges.length == 0) {
            return true;
        }
        int[] arrivalTimes = new int[edges.length];
        Arrays.fill(arrivalTimes, -1);
        int startVertex = 0;
        if (getMinimumArrivalTimeOfAncestors(startVertex, -1, 0, arrivalTimes, edges) == -1) {
            return false;
        }
        return areAllVerticesVisited(arrivalTimes);
    }

    public boolean areAllVerticesVisited(int[] arrivalTime) {
        for (int time : arrivalTime) {
            if (time == -1) {
                return false;
            }
        }
        return true;
    }

    public int getMinimumArrivalTimeOfAncestors(
            int currentVertex, int parent, int currentTime, int[] arrivalTimes, int[][] edges
    ) {
        arrivalTimes[currentVertex] = currentTime;
        int minimumArrivalTime = currentTime;
        for (int destination : edges[currentVertex]) {
            if (arrivalTimes[destination] == -1) {
                minimumArrivalTime = Math.min(minimumArrivalTime, getMinimumArrivalTimeOfAncestors(
                        destination, currentVertex, currentTime + 1, arrivalTimes, edges
                ));
            } else if (destination != parent) {
                minimumArrivalTime = Math.min(minimumArrivalTime, arrivalTimes[destination]);
            }
        }
        // A bridge was detected, which means the graph isn't two-edge-connected.
        if (minimumArrivalTime == currentTime && parent != -1) {
            return -1;
        }
        return minimumArrivalTime;
    }
}
