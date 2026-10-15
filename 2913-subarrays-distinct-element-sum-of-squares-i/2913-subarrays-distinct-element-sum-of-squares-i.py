class Solution(object):
    def sumCounts(self, nums):
        total = 0
        n = len(nums)
        
        for i in range(n):
            seen = set()
            for j in range(i, n):
                seen.add(nums[j])
                total += len(seen) ** 2
                
        return total