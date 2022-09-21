package com.test.dsa.util.binarysearchtree;

/**
 * You are given three nodes that are contained in the same binary search tree:
 * nodeOne, nodeTwo, nodeThree. Write a function that returns a boolean
 * representing whether one of nodeOne or nodeThree is an ancestor of nodeTwo and
 * the other node is a descendant of nodeTwo.
 */
public class ValidateThreeNodes {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        boolean foundTwo = false;
        BST node = nodeOne;
        while (node != null) {
            if (nodeTwo.value == node.value) {
                foundTwo = true;
            }

            if (nodeThree.value < node.value) {
                node = node.left;
            } else if (nodeThree.value < node.value) {
                node = node.right;
            } else {
                if (foundTwo) {
                    return true;
                }
                break;
            }
        }

        foundTwo = false;
        node = nodeThree;
        while (node != null) {
            if (nodeTwo.value == node.value) {
                foundTwo = true;
            }
            if (nodeOne.value < node.value) {
                node = node.left;
            } else if (nodeOne.value > node.value) {
                node = node.right;
            } else {
                return foundTwo;
            }
        }
        return false;
    }
}
