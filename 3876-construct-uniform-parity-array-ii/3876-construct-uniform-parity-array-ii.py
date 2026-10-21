class Solution(object):
    def uniformArray(self, nums1):
        """
        :type nums1: List[int]
        :rtype: bool
        """
        min_val = min(nums1)
        
        # If the minimum element is odd, we can always make all elements odd
        if min_val % 2 != 0:
            return True
        
        # If the minimum element is even, all elements must be even
        return all(x % 2 == 0 for x in nums1)