package com.test.dsa.util.graph;

import org.w3c.dom.ls.LSInput;

import java.util.*;

public class CanFinish {

    public static boolean canFinish(int numOfCourses, List<List<Integer>> preRequisites) {
        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int i = 0; i < numOfCourses; i++) {
            preMap.putIfAbsent(i, new ArrayList<>());
        }
        for(List<Integer> courseVsPre : preRequisites) {
            preMap.get(courseVsPre.get(0)).add(courseVsPre.get(1));
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numOfCourses; i++) {
            if(!dfs(preMap, visited, i)) {
                return false;
            }
        }
        return true;
    }


    private static boolean dfs(Map<Integer, List<Integer>> preMap, Set<Integer> visited, Integer currentCourse) {
        if(visited.contains(currentCourse)) {
            return false;
        }
        if(preMap.get(currentCourse).size() == 0) {
            return true;
        }

        visited.add(currentCourse);
        for(Integer  preReq : preMap.get(currentCourse)) {
            if(!dfs(preMap, visited, preReq)) {
                return false;
            }
        }
        visited.remove(currentCourse);
        preMap.put(currentCourse, new ArrayList<>());
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> coursePrereq = new ArrayList<>();
        coursePrereq.add(Arrays.asList(0, 1));
        coursePrereq.add(Arrays.asList(0, 2));
        coursePrereq.add(Arrays.asList(1, 3));
        coursePrereq.add(Arrays.asList(1, 4));
        coursePrereq.add(Arrays.asList(3, 4));
        System.out.println(CanFinish.canFinish(5, coursePrereq));
        coursePrereq = new ArrayList<>();
        coursePrereq.add(Arrays.asList(0, 1));
        coursePrereq.add(Arrays.asList(0, 2));
        coursePrereq.add(Arrays.asList(1, 3));
        coursePrereq.add(Arrays.asList(1, 4));
        coursePrereq.add(Arrays.asList(3, 4));
        coursePrereq.add(Arrays.asList(4, 0));
        System.out.println(CanFinish.canFinish(5, coursePrereq));
    }
}
