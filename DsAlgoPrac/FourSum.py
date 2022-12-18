class Solution(object):
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        all_pair_sum = {}
        quad = []
        pair_count = {}
        reverse_index = {}
        for i in range(1, len(nums) - 1):
            for j in range(i + 1, len(nums)):
                current_sum = nums[i] + nums[j]
                difference = target - current_sum
                if difference in all_pair_sum:
                    target_list = all_pair_sum.get(difference)
                    for x in range(len(target_list)):
                        new_quad = [target_list[x][0], target_list[x][1], nums[i], nums[j]]
                        new_quad.sort()
                        if tuple(new_quad) not in reverse_index:
                            reverse_index[tuple(new_quad)] = True
                            quad.append(new_quad)

            for k in range(i):
                current_sum = nums[i] + nums[k]
                pair = [nums[i], nums[k]]
                pair_tup = pair
                pair_tup.sort()
                pair_tup = tuple(pair_tup)
                if pair_count.get(pair_tup, 0) == 2:
                    continue
                count = pair_count.get(pair_tup, 0)
                pair_count[pair_tup] = count + 1
                if current_sum not in all_pair_sum:
                    pair_group = [pair]
                    all_pair_sum[current_sum] = pair_group
                else:
                    all_pair_sum[current_sum].append(pair)

        return quad


sol = Solution()
print(sol.fourSum([2, 2, 2, 2, 2], 8))
print(sol.fourSum([1, 0, -1, 0, -2, 2], 0))
print(sol.fourSum([-5, 5, 4, -3, 0, 0, 4, -2], 4))
print(sol.fourSum(
    [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 2, 2, 2, 2], 8))
