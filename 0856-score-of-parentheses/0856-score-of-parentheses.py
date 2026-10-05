class Solution(object):
    def scoreOfParentheses(self, s):
        score = 0
        depth = 0
        
        for i, char in enumerate(s):
            if char == '(':
                depth += 1
            else:
                depth -= 1
                if s[i - 1] == '(':
                    score += 1 << depth
                    
        return score