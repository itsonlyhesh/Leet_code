class Solution(object):
    def removeInvalidParentheses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        # Step 1: Count minimum '(' and ')' to remove
        rem_l = 0
        rem_r = 0
        for char in s:
            if char == '(':
                rem_l += 1
            elif char == ')':
                if rem_l > 0:
                    rem_l -= 1
                else:
                    rem_r += 1

        result = []

        def backtrack(index, rem_l, rem_r, balance, path):
            if index == len(s):
                if rem_l == 0 and rem_r == 0 and balance == 0:
                    result.append("".join(path))
                return

            char = s[index]

            # Option 1: Try removing the current character (if '(' or ')')
            if char == '(' and rem_l > 0:
                # Skip duplicate deletions in a sequence like "((("
                if index == 0 or s[index] != s[index - 1] or len(path) == 0 or path[-1] != char:
                    backtrack(index + 1, rem_l - 1, rem_r, balance, path)
                elif index > 0 and s[index] == s[index - 1]:
                    # To avoid duplicate branches, always prune when skipping repeated adjacent chars
                    backtrack(index + 1, rem_l - 1, rem_r, balance, path)

            elif char == ')' and rem_r > 0:
                backtrack(index + 1, rem_l, rem_r - 1, balance, path)

            # Option 2: Keep the current character
            path.append(char)
            if char == '(':
                backtrack(index + 1, rem_l, rem_r, balance + 1, path)
            elif char == ')':
                # Can only keep ')' if there is a matching open '('
                if balance > 0:
                    backtrack(index + 1, rem_l, rem_r, balance - 1, path)
            else:
                # Letters are always kept
                backtrack(index + 1, rem_l, rem_r, balance, path)
            path.pop()

        # Cleaner approach for deduplication using a set during backtracking:
        valid_set = set()

        def dfs(i, l_rem, r_rem, bal, cur):
            if i == len(s):
                if l_rem == 0 and r_rem == 0 and bal == 0:
                    valid_set.add(cur)
                return

            c = s[i]
            # Prune impossible paths
            if bal < 0:
                return

            # Delete '('
            if c == '(' and l_rem > 0:
                dfs(i + 1, l_rem - 1, r_rem, bal, cur)
            # Delete ')'
            if c == ')' and r_rem > 0:
                dfs(i + 1, l_rem, r_rem - 1, bal, cur)

            # Keep character
            if c == '(':
                dfs(i + 1, l_rem, r_rem, bal + 1, cur + c)
            elif c == ')':
                dfs(i + 1, l_rem, r_rem, bal - 1, cur + c)
            else:
                dfs(i + 1, l_rem, r_rem, bal, cur + c)

        dfs(0, rem_l, rem_r, 0, "")
        return list(valid_set)