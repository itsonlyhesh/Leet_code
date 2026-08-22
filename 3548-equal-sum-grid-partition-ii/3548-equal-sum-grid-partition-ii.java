class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        if (checkHorizontal(grid)) return true;
        return checkHorizontal(transpose(grid));
    }

    private boolean checkHorizontal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m < 2) return false;

        long totalSum = 0;
        Map<Long, Integer> botFreq = new HashMap<>();
        Map<Long, Integer> topFreq = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
                botFreq.put((long) grid[i][j], botFreq.getOrDefault((long) grid[i][j], 0) + 1);
            }
        }

        long topSum = 0;
        for (int i = 0; i < m - 1; i++) {
            int h1 = i + 1;
            int h2 = m - h1;

            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                topSum += val;
                topFreq.put(val, topFreq.getOrDefault(val, 0) + 1);
                
                int count = botFreq.get(val);
                if (count == 1) botFreq.remove(val);
                else botFreq.put(val, count - 1);
            }

            long botSum = totalSum - topSum;

            // Case 1: Equal sums without discount
            if (topSum == botSum) return true;

            // Case 2: Top section is heavier -> discount from top
            if (topSum > botSum) {
                long diff = topSum - botSum;
                if (isValidDiscount(grid, 0, i, h1, n, diff, topFreq)) return true;
            }

            // Case 3: Bottom section is heavier -> discount from bottom
            if (botSum > topSum) {
                long diff = botSum - topSum;
                if (isValidDiscount(grid, i + 1, m - 1, h2, n, diff, botFreq)) return true;
            }
        }

        return false;
    }

    private boolean isValidDiscount(int[][] grid, int rStart, int rEnd, int h, int w, long diff, Map<Long, Integer> freq) {
        if (h >= 2 && w >= 2) {
            return freq.getOrDefault(diff, 0) > 0;
        } else if (h == 1 && w > 1) {
            return grid[rStart][0] == diff || grid[rStart][w - 1] == diff;
        } else if (w == 1 && h > 1) {
            return grid[rStart][0] == diff || grid[rEnd][0] == diff;
        }
        return false;
    }

    private int[][] transpose(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] trans = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                trans[j][i] = grid[i][j];
            }
        }
        return trans;
    }
}