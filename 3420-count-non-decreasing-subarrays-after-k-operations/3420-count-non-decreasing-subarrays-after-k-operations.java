import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        int r = n - 1;
        // Stores indices of running prefix maxima from l to r (strictly decreasing values)
        Deque<Integer> dq = new ArrayDeque<>();

        for (int l = n - 1; l >= 0; l--) {
            // 1. Expand window to the left by including nums[l]
            while (!dq.isEmpty() && nums[dq.peekFirst()] <= nums[l]) {
                int idx = dq.pollFirst();
                int nextIdx = dq.isEmpty() ? r + 1 : dq.peekFirst();
                cost += (long) (nums[l] - nums[idx]) * (nextIdx - idx);
            }
            dq.offerFirst(l);

            // 2. Shrink window from the right while operations exceed k
            while (cost > k) {
                // The running maximum reaching index r is at the BACK of the deque
                int maxIdxAtR = dq.peekLast();
                cost -= (long) nums[maxIdxAtR] - nums[r];
                if (maxIdxAtR == r) {
                    dq.pollLast();
                }
                r--;
            }

            // 3. All subarrays starting at l and ending in [l..r] are valid
            ans += (r - l + 1);
        }

        return ans;
    }
}