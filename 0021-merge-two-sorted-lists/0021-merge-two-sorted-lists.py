# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):

    def mergeTwoLists(self, head_one, head_two):
        """
        :type list1: Optional[ListNode]
        :type list2: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        if head_one is None:
            return head_two
        if head_two is None:
            return head_one
        
        p1_prev = None
        p1 = head_one
        p2 = head_two
        while p1 is not None and p2 is not None:
            if p1.val < p2.val:
                p1_prev = p1
                p1 = p1.next
            else:
                if p1_prev is not None:
                    p1_prev.next = p2
                p1_prev = p2
                p2 = p2.next
                p1_prev.next = p1

        if p1 is None:
            p1_prev.next = p2

        return head_one if head_one.val < head_two.val else head_two
