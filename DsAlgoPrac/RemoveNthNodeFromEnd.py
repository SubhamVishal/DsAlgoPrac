# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        if head is None:
            return head
        temp_head = head
        list_len = 0
        while temp_head is not None:
            list_len += 1
            temp_head = temp_head.next

        if n == 1:
            return None

        if n > list_len:
            return head

        node_to_remove = list_len - n
        temp_head = head
        for i in range(node_to_remove):
            temp_head = temp_head.next

        temp_head.next = temp_head.next.next

        return head


five = ListNode(5, None)
four = ListNode(4, five)
'''

three = ListNode(3, four)
two = ListNode(2, three)
one = ListNode(1, two)

n = 2

print(one)
'''
sol = Solution()
#sol.removeNthFromEnd(one, n)
sol.removeNthFromEnd(four, 1)

#print(one)
print(five)
