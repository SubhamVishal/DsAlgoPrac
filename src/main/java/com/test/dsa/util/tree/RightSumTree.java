package com.test.dsa.util.tree;

public class RightSumTree {
    static int sum = 0;
    static Node Root;

    //Recursive function to transform a BST to sum tree.
    //This function traverses the tree in reverse inorder so
    // that we have visited all greater key nodes of the currently
    // visited node
    static void transformTreeUtil(Node root) {
        // Base case
        if (root == null) {
            return;
        }

        //Recur for right subtree
        transformTreeUtil(root.right);
        sum = sum + root.data;
        //store old sum in the current node
        root.data = sum - root.data;
        // Recur for left sub tree
        transformTreeUtil(root.left);
    }

    // A wrapper over transformTreeUtil()
    static void transformTree(Node root) {
        transformTreeUtil(root);
    }

    static void printInorder(Node root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.println(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        RightSumTree.Root = new Node(11);
        RightSumTree.Root.left = new Node(2);
        RightSumTree.Root.right = new Node(29);
        RightSumTree.Root.left.left = new Node(1);
        RightSumTree.Root.left.right = new Node(7);
        RightSumTree.Root.right.left = new Node(15);
        RightSumTree.Root.right.right = new Node(40);
        RightSumTree.Root.right.right.left = new Node(35);

        System.out.println("Inorder Traversal of given tree");
        printInorder(Root);

        transformTree(Root);
        System.out.println("\n\nInorder Traversal of transformed tree");
        printInorder(Root);
    }

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }
}