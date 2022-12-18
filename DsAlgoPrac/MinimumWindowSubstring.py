class Solution(object):
    def minWindow(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        pattern_count_dict = {}
        for i in range(len(t)):
            cur_count = pattern_count_dict.get(t[i], 0)
            pattern_count_dict[t[i]] = cur_count + 1
        matched = 0
        start = 0
        min_length = len(s) + 1
        sub_str = 0
        for end_window in range(0, len(s)):
            right = s[end_window]
            if right in pattern_count_dict:
                pattern_count_dict[right] = pattern_count_dict[right] - 1
                if pattern_count_dict[right] == 0:
                    matched += 1
            while matched == len(pattern_count_dict):
                if min_length > ((end_window - start) + 1):
                    min_length = end_window - start + 1
                    sub_str = start

                deleted = s[start]
                start += 1
                if deleted in pattern_count_dict:
                    if pattern_count_dict[deleted] == 0:
                        matched -= 1
                    pattern_count_dict[deleted] = pattern_count_dict[deleted] + 1

        return '' if min_length > len(s) else s[sub_str: sub_str + min_length]


sol = Solution()
print(sol.minWindow('ADOBECODEADBANC', 'AABC'))
print(sol.minWindow('ADOBECODEADBANC', 'ABC') == 'BAANC')

'''
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t
'''
