class Solution(object):
    def minOperations(self, s):
        """
        :type s: str
        :rtype: int
        """
        n = len(s)
        
        # Case 0: Already sorted
        is_sorted = all(s[i] <= s[i + 1] for i in range(n - 1))
        if is_sorted:
            return 0
        
        # Case -1: Impossible if length is 2 and not sorted
        if n == 2:
            return -1
        
        min_char = min(s)
        max_char = max(s)
        
        # Case 1: First character is minimum OR last character is maximum
        if s[0] == min_char or s[-1] == max_char:
            return 1
        
        # Case 3: s[0] is the unique maximum AND s[-1] is the unique minimum
        mid = s[1:-1]
        mid_min = min(mid)
        mid_max = max(mid)
        
        if s[-1] < mid_min and s[0] > mid_max:
            return 3
        
        # Case 2: All other cases
        return 2
        