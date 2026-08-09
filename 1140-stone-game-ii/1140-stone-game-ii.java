class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSum[i] stores the sum of stones from index i to n - 1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // dp[i][M] stores the max stones a player can get starting at index i with parameter M
        int[][] memo = new int[n][n + 1];

        return helper(0, 1, suffixSum, memo, n);
    }

    private int helper(int i, int M, int[] suffixSum, int[][] memo, int n) {
        // Base case: Beyond the array length
        if (i >= n) {
            return 0;
        }

        // If current player can take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return memoized result if present
        if (memo[i][M] > 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            
            // Total remaining stones - optimal stones opponent can get
            int currentStones = suffixSum[i] - helper(i + X, nextM, suffixSum, memo, n);
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[i][M] = maxStones;
        return memo[i][M];
    }
}