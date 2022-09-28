package com.test.dsa.util.tree;

import java.util.*;

public class NodeAtDistanceK {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class Pair<U, V> {
        public final U first;
        public final V second;

        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    public ArrayList<Integer> findNodesDistanceK(TreeNode root, int target, int k) {
        HashMap<Integer, TreeNode> nodesToParents = new HashMap<>();
        populateNodesToParents(root, nodesToParents, null);
        TreeNode targetNode = getNodeFromValue(target, root, nodesToParents);
        return breadthFirstSearchForNodesDistanceK(targetNode, nodesToParents, k);
    }

    public ArrayList<Integer> breadthFirstSearchForNodesDistanceK(TreeNode targetNode,
                                                                  HashMap<Integer, TreeNode> nodesToParents, int k) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<TreeNode, Integer>(targetNode, 0));
        HashSet<Integer> seen = new HashSet<>();
        seen.add(targetNode.val);
        while (queue.size() > 0) {
            Pair<TreeNode, Integer> vals = queue.poll();
            TreeNode currentNode = vals.first;
            int distanceFromTarget = vals.second;

            if (distanceFromTarget == k) {
                ArrayList<Integer> nodeDistanceK = new ArrayList<>();
                for (Pair<TreeNode, Integer> pair : queue) {
                    nodeDistanceK.add(pair.first.val);
                }
                nodeDistanceK.add(currentNode.val);
                return nodeDistanceK;
            }

            List<TreeNode> connectedNodes = new ArrayList<>();
            connectedNodes.add(currentNode.left);
            connectedNodes.add(currentNode.right);
            connectedNodes.add(nodesToParents.get(currentNode.val));
            for (TreeNode node : connectedNodes) {
                if (node == null) continue;
                if (seen.contains(node.val)) continue;
                seen.add(node.val);
                queue.add(new Pair<TreeNode, Integer>(node, distanceFromTarget + 1));
            }
        }
        return new ArrayList<>();
    }

    public TreeNode getNodeFromValue(int value, TreeNode tree, HashMap<Integer, TreeNode> nodesToParents) {
        if (tree.val == value) return tree;
        TreeNode nodeParent = nodesToParents.get(value);
        if (nodeParent.left != null && nodeParent.left.val == value) {
            return nodeParent.left;
        }
        return nodeParent.right;
    }

    public void populateNodesToParents(TreeNode node, Map<Integer, TreeNode> nodesToParents, TreeNode parent) {
        if (node != null) {
            nodesToParents.put(node.val, parent);
            populateNodesToParents(node.left, nodesToParents, node);
            populateNodesToParents(node.right, nodesToParents, node);
        }
    }
}
