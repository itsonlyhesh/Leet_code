class Solution(object):
    def lexGreaterPermutation(self, s, target):
        """
        :type s: str
        :type target: str
        :rtype: str
        """
        n = len(s)
        total_count = Counter(s)
        
        # 1. Match as long a prefix of target as possible
        prefix_len = 0
        rem = Counter(total_count)
        while prefix_len < n and rem[target[prefix_len]] > 0:
            rem[target[prefix_len]] -= 1
            prefix_len += 1
            
        # 2. Try pivoting at position i from min(prefix_len, n - 1) down to 0
        # If prefix_len == n, we must backtrack because the string must be STRICTLY greater.
        start_i = min(prefix_len, n - 1)
        
        # Count remaining characters after taking prefix of length start_i
        curr_counts = Counter(total_count)
        for j in range(start_i):
            curr_counts[target[j]] -= 1
            
        for i in range(start_i, -1, -1):
            target_char = target[i]
            chosen_char = None
            
            # Find the smallest character strictly greater than target[i]
            for c_code in range(ord(target_char) + 1, ord('z') + 1):
                c = chr(c_code)
                if curr_counts[c] > 0:
                    chosen_char = c
                    break
            
            if chosen_char is not None:
                curr_counts[chosen_char] -= 1
                
                # Build the result
                res = list(target[:i])
                res.append(chosen_char)
                
                # Append all remaining characters in ascending order
                for c_code in range(ord('a'), ord('z') + 1):
                    c = chr(c_code)
                    if curr_counts[c] > 0:
                        res.append(c * curr_counts[c])
                
                return "".join(res)
            
            # Backtrack: add target[i - 1] back into the pool for the next iteration
            if i > 0:
                curr_counts[target[i - 1]] += 1
                
        return ""