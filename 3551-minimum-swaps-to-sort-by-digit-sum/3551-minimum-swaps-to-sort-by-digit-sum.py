class Solution(object):
    def minSwaps(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        def get_digit_sum(x):
            s = 0
            while x > 0:
                s += x % 10
                x //= 10
            return s

        n = len(nums)
        
        # Pair each element with its digit sum and original index
        elements = []
        for i in xrange(n):
            val = nums[i]
            elements.append((get_digit_sum(val), val, i))
        
        # Sort by: 1. digit sum (ascending), 2. value (ascending)
        elements.sort()
        
        # visited array to keep track of cycle decomposition
        visited = [False] * n
        swaps = 0
        
        for i in xrange(n):
            # If already visited or already in the correct position
            if visited[i] or elements[i][2] == i:
                continue
            
            # Find the size of the current cycle
            cycle_size = 0
            curr = i
            while not visited[curr]:
                visited[curr] = True
                curr = elements[curr][2]
                cycle_size += 1
            
            if cycle_size > 1:
                swaps += cycle_size - 1
                
        return swaps