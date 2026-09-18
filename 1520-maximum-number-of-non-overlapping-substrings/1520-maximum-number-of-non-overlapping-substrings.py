class Solution(object):
    def maxNumOfSubstrings(self, s):
        n = len(s)

        # First and last occurrence of each character
        first = [n] * 26
        last = [-1] * 26

        for i, ch in enumerate(s):
            x = ord(ch) - ord('a')
            first[x] = min(first[x], i)
            last[x] = i

        intervals = []

        # Try every character as the starting character
        for c in range(26):
            if first[c] == n:
                continue

            left = first[c]
            right = last[c]

            i = left
            valid = True

            while i <= right:
                x = ord(s[i]) - ord('a')

                # This character appeared before our current interval
                if first[x] < left:
                    valid = False
                    break

                # We must include all occurrences of this character
                right = max(right, last[x])
                i += 1

            if valid:
                intervals.append((left, right))

        # Sort by ending position
        intervals.sort(key=lambda x: x[1])

        result = []
        prev_end = -1

        for left, right in intervals:
            if left > prev_end:
                result.append(s[left:right + 1])
                prev_end = right

        return result

