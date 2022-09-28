package com.test.dsa.util.linkedlist;

//head = 0 -> 1 -> 2 -> 3 -> 4 -> 5
//5 -> 4 -> 3 -> 2 -> 1 -> 0
public class ReverseLinkedList {
    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList reverseLinkedList(LinkedList head) {
        LinkedList previousNode = null;
        LinkedList currentNode = head;
        while (currentNode != null) {
            LinkedList nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }
}
