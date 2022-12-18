class Solution(object):
    import collections
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = []
        open_paren = {'(', '[', '{'}
        for ch in s:
            if ch in open_paren:
                stack.append(ch)
            elif ch == ')':
                if len(stack) == 0:
                    return False
                else:
                    pair_pr = stack.pop()
                    if pair_pr != '(':
                        return False
            elif ch == ']':
                if len(stack) == 0:
                    return False
                else:
                    pair_pr = stack.pop()
                    if pair_pr != '[':
                        return False
            elif ch == '}':
                if len(stack) == 0:
                    return False
                else:
                    pair_pr = stack.pop()
                    if pair_pr != '{':
                        return False
        return len(stack) == 0


sol = Solution()
s = "()"
print(1, sol.isValid(s) == True)
s = "()[]{}"
print(2, sol.isValid(s) == True)
s = "(]"
print(3, sol.isValid(s) == False)
