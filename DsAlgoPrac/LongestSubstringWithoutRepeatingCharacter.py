class SolutionLengthOfLongestSubstring:
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s is None or len(s) == 0:
            return 0
        if len(s) == 1:
            return 1
        count_map = {}
        left = 0
        right = 1
        count_map[s[0]] = 1
        max_val = 1
        while right < len(s):
            curr_char = s[right]
            if count_map.get(curr_char, 0) == 1:
                while count_map.get(curr_char, 0) != 0:
                    count_map[s[left]] = 0
                    left += 1
            else:
                max_val = max(max_val, right - left + 1)
                count_map[curr_char] = 1
                right += 1
        return max_val


sol = SolutionLengthOfLongestSubstring()
print(sol.lengthOfLongestSubstring('abcabcbb'))
print(sol.lengthOfLongestSubstring('bbbbb'))
print(sol.lengthOfLongestSubstring(''))
print(sol.lengthOfLongestSubstring('k'))
print(sol.lengthOfLongestSubstring('au'))

