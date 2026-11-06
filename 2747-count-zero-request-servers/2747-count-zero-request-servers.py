class Solution(object):
    def countServers(self, n, logs, x, queries):
        """
        :type n: int
        :type logs: List[List[int]]
        :type x: int
        :type queries: List[int]
        :rtype: List[int]
        """
        logs.sort(key=lambda log: log[1])
        ids = sorted(range(len(queries)), key=lambda i: queries[i])
        ans = [0] * len(queries)
        cnt = [0] * (n + 1)
        left = right = active = 0

        for i in ids:
            q = queries[i]
            while right < len(logs) and logs[right][1] <= q:
                server_id = logs[right][0]
                if cnt[server_id] == 0:
                    active += 1
                cnt[server_id] += 1
                right += 1

            while left < right and logs[left][1] < q - x:
                server_id = logs[left][0]
                cnt[server_id] -= 1
                if cnt[server_id] == 0:
                    active -= 1
                left += 1

            ans[i] = n - active

        return ans