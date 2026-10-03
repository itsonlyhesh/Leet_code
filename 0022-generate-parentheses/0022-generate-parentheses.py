class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        res = []
        
        def backtrack(current, open_count, close_count):
            if len(current) == 2 * n:
                res.append("".join(current))
                return
            
            if open_count < n:
                current.append("(")
                backtrack(current, open_count + 1, close_count)
                current.pop()
                
            if close_count < open_count:
                current.append(")")
                backtrack(current, open_count, close_count + 1)
                current.pop()
                
        backtrack([], 0, 0)
        return res