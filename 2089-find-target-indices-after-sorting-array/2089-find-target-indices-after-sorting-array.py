class Solution(object):
    def targetIndices(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        less_count = 0
        target_count = 0
        
        for num in nums:
            if num < target:
                less_count += 1
            elif num == target:
                target_count += 1
                
        return list(range(less_count, less_count + target_count))