package com.test.dsa.util.stack;

import java.util.ArrayList;
import java.util.Stack;

public class LargestRectangleUnderSkyline {
    // O(n) time | O(n) space - where n is the number of buildings
    public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
        Stack<Integer> pillarIndices = new Stack<>();
        int maxArea = 0;

        ArrayList<Integer> extendedBuildings = new ArrayList<>(buildings);
        extendedBuildings.add(0);
        for(int idx = 0; idx < extendedBuildings.size(); idx++) {
            int height = extendedBuildings.get(idx);
            while(!pillarIndices.isEmpty() && extendedBuildings.get(pillarIndices.peek()) >= height) {
                int pillarHeight = extendedBuildings.get(pillarIndices.pop());
                int width = (pillarIndices.isEmpty()) ? idx : idx - pillarIndices.peek() - 1;
                maxArea = Math.max(width * pillarHeight, maxArea);
            }
            pillarIndices.push(idx);
        }
        return maxArea;
    }

    public int largestRectangleUnderSkylineV2(ArrayList<Integer> buildings) {
        int maxArea = 0;
        for(int pillarIdx = 0; pillarIdx < buildings.size(); pillarIdx++) {
            int currentHeight = buildings.get(pillarIdx);

            int furthestLeft = pillarIdx;
            while(furthestLeft > 0 && buildings.get(furthestLeft - 1) >= currentHeight) {
                furthestLeft -= 1;
            }

            int furthestRight = pillarIdx;
            while(furthestRight < buildings.size() - 1 && buildings.get(furthestRight + 1) >= currentHeight) {
                furthestRight += 1;
            }

            int areaWithCurrentBuilding = (furthestRight - furthestLeft + 1) * currentHeight;
            maxArea = Math.max(areaWithCurrentBuilding, maxArea);
        }
        return maxArea;
    }
}
