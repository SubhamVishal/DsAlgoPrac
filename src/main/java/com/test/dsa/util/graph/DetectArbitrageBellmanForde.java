package com.test.dsa.util.graph;
/*        0:USD 1:CAD 2:GBP
    0:USD [1.0, 1.27, 0.718]
    1:CAD [0.74, 1.0, 0.56]
    2:GBP [1.39, 1.77, 1.0]
 */

import java.util.ArrayList;
import java.util.Arrays;
//the logic is even after relaxing the node distances for number of nodes time,
// still the distance is decreasing, that means there is a negative weight cycle in the graph
// O(n^3) time | O(n^2) space - where n is the number of currencies
public class DetectArbitrageBellmanForde {
    public boolean detectArbitrage(ArrayList<ArrayList<Double>> exchangeRates) {
        /*
            To use exchange rates as edge weights, we must be able to add them.
            Since log(a * b) = log(a) + log(b), we can convert all rates to
            -log10(rate) to use them as edge weights.
         */
        ArrayList<ArrayList<Double>> logExchangeRates = convertToLogMatrix(exchangeRates);
        // A negative weight cycle indicates an arbitrage
        return foundNegativeWeightCycle(logExchangeRates, 0);
    }

    private boolean foundNegativeWeightCycle(ArrayList<ArrayList<Double>> graph, int start) {
        double[] distanceFromStart = new double[graph.size()];
        Arrays.fill(distanceFromStart, Double.MAX_VALUE);
        distanceFromStart[start] = 0;
        for(int unused = 0; unused < graph.size(); unused++) {
            if(!relaxEdgesAndUpdateDistances(graph, distanceFromStart)) {
                return false;
            }
        }
        return relaxEdgesAndUpdateDistances(graph, distanceFromStart);
    }

    private boolean relaxEdgesAndUpdateDistances(ArrayList<ArrayList<Double>> graph, double[] distances) {
        boolean updated = false;
        for(int sourceIdx = 0; sourceIdx < graph.size(); sourceIdx++) {
            ArrayList<Double> edges = graph.get(sourceIdx);
            for(int destinationEdge = 0; destinationEdge < edges.size(); destinationEdge++) {
                double edgeWeight = edges.get(destinationEdge);
                double newDistanceToDestination  = distances[sourceIdx] + edgeWeight;
                if(newDistanceToDestination < distances[destinationEdge]) {
                    updated = true;
                    distances[destinationEdge] = newDistanceToDestination;
                }
            }
        }
        return updated;
    }

    private ArrayList<ArrayList<Double>> convertToLogMatrix(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<ArrayList<Double>> newMatrix = new ArrayList<>();
        for(int row = 0; row < matrix.size(); row++) {
            ArrayList<Double> rates = matrix.get(row);
            newMatrix.add(new ArrayList<>());
            for(Double rate : rates) {
                newMatrix.get(row).add(-Math.log10(rate));
            }
        }
        return newMatrix;
    }
}
