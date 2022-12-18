"""
# Definition for a Node.
class Node:
    def __init__(self, x, next=None, random=None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: Node
        :rtype: Node
        """
        curr = head
        map = {}
        while curr is not None:
            map[curr] = Node(curr.val)
            curr = curr.next

        curr = head
        while curr is not None:
            map[curr].next = map.get(curr.next)
            map[curr].random = map.get(curr.random)
            curr = curr.next
        return map.get(head)