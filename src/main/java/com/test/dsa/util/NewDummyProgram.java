package com.test.dsa.util;

class Program {
    public static LinkedList invertedBisection(LinkedList head) {
        if(head == null || head.next == null){
            return head;
        }
        LinkedList fast = head;
        LinkedList slow = head;
        LinkedList prevToSlow = null;
        boolean isOdd = false;
        while(fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            prevToSlow = slow;
            slow = slow.next;
        }
        if(fast != null && fast.next == null) {
            isOdd = true;
        }

        LinkedList secondHead = slow.next;
        slow.next = null;
        if(isOdd) {
            prevToSlow.next = null;
        }
        reverseLinkedList(head);
        secondHead = reverseLinkedList(secondHead);
        if(isOdd) {
            head.next = slow;
            slow.next = secondHead;
            return prevToSlow;
        } else {
            head.next = secondHead;
            return slow;
        }
    }

    static LinkedList reverseLinkedList(LinkedList head) {
        LinkedList prev = null;
        LinkedList curr = head;
        while(curr != null) {
            LinkedList next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // This is an input class. Do not edit.
    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "LinkedList{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static LinkedList invertBisectionPart2(LinkedList head) {
        int length  = getLinkedListLength(head);
        if(length <= 3) {
            return head;
        }
        LinkedList firstHalfTail = getMiddleNode(head, length);
        LinkedList middleNode = null;
        LinkedList secondHalfHead = null;
        if(length % 2 == 0) {
            secondHalfHead = firstHalfTail.next;
        } else {
            middleNode = firstHalfTail.next;
            secondHalfHead = firstHalfTail.next.next;
        }

        firstHalfTail.next = null;
        reverseLinkedList(head);
        LinkedList reversedSecondHalfHead = reverseLinkedList(secondHalfHead);

        if(middleNode == null) {
            head.next = reversedSecondHalfHead;
        } else {
            head.next = middleNode;
            middleNode.next = reversedSecondHalfHead;
        }
        return firstHalfTail;
    }

    private static int getLinkedListLength(LinkedList head) {
        int length = 0;
        LinkedList currentNode = head;
        while(currentNode != null) {
            currentNode = currentNode.next;
            length++;
        }
        return length;
    }

    public static LinkedList getMiddleNode(LinkedList head, int length) {
        int halfLength = length / 2;
        int currentPosition = 1;
        LinkedList currentNode = head;
        while(currentPosition != halfLength) {
            currentNode = currentNode.next;
            currentPosition++;
        }
        return currentNode;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList(0);
        list.next = new LinkedList(1);
        list.next.next = new LinkedList(2);
        list.next.next.next = new LinkedList(3);
        list.next.next.next.next = new LinkedList(4);
        list.next.next.next.next.next = new LinkedList(5);
        list.next.next.next.next.next.next = new LinkedList(6);
        System.out.println(Program.invertBisectionPart2(list));
    }
}

