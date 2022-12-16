class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        left = 0
        right = 1
        max_profit = 0
        while right < len(prices):
            if prices[left] < prices[right]:
                max_profit = max(max_profit, prices[right] - prices[left])
            else:
                left = right

            right += 1

        return max_profit