class Solution:
    def minimumCost(self, cost1: int, cost2: int, costBoth: int, need1: int, need2: int) -> int:
        # Overlapping units required for both types
        common = min(need1, need2)
        total_cost = common * min(costBoth, cost1 + cost2)
        
        # Remaining units required for type 1 (if any)
        rem1 = need1 - common
        total_cost += rem1 * min(cost1, costBoth)
        
        # Remaining units required for type 2 (if any)
        rem2 = need2 - common
        total_cost += rem2 * min(cost2, costBoth)
        
        return total_cost