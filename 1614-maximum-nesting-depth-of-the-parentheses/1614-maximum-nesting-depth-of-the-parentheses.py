class Solution(object):
    def maxDepth(self, s):
        """
        :type s: str
        :rtype: int
        """
        current_depth = 0
        max_depth = 0
        
        for char in s:
            if char == '(':
                current_depth += 1
                if current_depth > max_depth:
                    max_depth = current_depth
            elif char == ')':
                current_depth -= 1
                
        return max_depth