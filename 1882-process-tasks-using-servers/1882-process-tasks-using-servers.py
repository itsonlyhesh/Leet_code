class Solution(object):
    def assignTasks(self, servers, tasks):
        """
        :type servers: List[int]
        :type tasks: List[int]
        :rtype: List[int]
        """
        # available: (weight, index)
        available = [(servers[i], i) for i in xrange(len(servers))]
        heapq.heapify(available)
        
        # busy: (free_time, weight, index)
        busy = []
        
        ans = []
        cur_time = 0
        
        for j in xrange(len(tasks)):
            task_duration = tasks[j]
            cur_time = max(cur_time, j)
            
            # If all servers are busy, advance time to the earliest finish time
            if not available:
                cur_time = busy[0][0]
                
            # Free all servers that have finished by cur_time
            while busy and busy[0][0] <= cur_time:
                free_time, weight, idx = heapq.heappop(busy)
                heapq.heappush(available, (weight, idx))
                
            # Assign the best available server
            weight, idx = heapq.heappop(available)
            ans.append(idx)
            heapq.heappush(busy, (cur_time + task_duration, weight, idx))
            
        return ans