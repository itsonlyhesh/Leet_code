class Solution:
    def countServers(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        
        row_counts = [0] * m
        col_counts = [0] * n
        
        # Pass 1: Count total servers in each row and column
        for r in range(m):
            for c in range(n):
                if grid[r][c] == 1:
                    row_counts[r] += 1
                    col_counts[c] += 1
                    
        # Pass 2: Count servers that communicate (row count > 1 or col count > 1)
        communicating_servers = 0
        for r in range(m):
            for c in range(n):
                if grid[r][c] == 1:
                    if row_counts[r] > 1 or col_counts[c] > 1:
                        communicating_servers += 1
                        
        return communicating_servers