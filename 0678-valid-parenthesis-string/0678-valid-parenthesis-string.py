class Solution(object):
    def checkValidString(self, s):
        """
        :type s: str
        :rtype: bool
        """
        cmin = 0  # Minimum possible unmatched '('
        cmax = 0  # Maximum possible unmatched '('
        
        for char in s:
            if char == '(':
                cmin += 1
                cmax += 1
            elif char == ')':
                cmin -= 1
                cmax -= 1
            else:  # char == '*'
                cmin -= 1  # If '*' acts as ')'
                cmax += 1  # If '*' acts as '('
                # If '*' acts as '', cmin and cmax stay unchanged
            
            # If the maximum possible open count is negative, too many ')'
            if cmax < 0:
                return False
            
            # Minimum open count cannot fall below 0
            if cmin < 0:
                cmin = 0
                
        return cmin == 0