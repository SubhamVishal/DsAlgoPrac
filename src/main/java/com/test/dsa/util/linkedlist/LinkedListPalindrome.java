package com.test.dsa.util.linkedlist;

import com.test.dsa.util.linkedlist.ReverseLinkedList.*;

public class LinkedListPalindrome {
    public boolean linkedListPalindrome(LinkedList head) {
        LinkedList slowNode = head;
        LinkedList fastNode = head;

        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        LinkedList reversedSecondHalfNode = ReverseLinkedList.reverseLinkedList(slowNode);
        LinkedList firstHalfNode = head;

        while (reversedSecondHalfNode != null) {
            if (reversedSecondHalfNode.value != firstHalfNode.value) return false;
            reversedSecondHalfNode = reversedSecondHalfNode.next;
            firstHalfNode = firstHalfNode.next;
        }
        return true;
    }
}
