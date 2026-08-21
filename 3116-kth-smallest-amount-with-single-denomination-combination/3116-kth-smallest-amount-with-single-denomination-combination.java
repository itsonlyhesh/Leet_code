import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Prune redundant coins: if coin A divides coin B, remove coin B
        Arrays.sort(coins);
        List<Integer> filteredCoins = new ArrayList<>();
        for (int coin : coins) {
            boolean redundant = false;
            for (int base : filteredCoins) {
                if (coin % base == 0) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) {
                filteredCoins.add(coin);
            }
        }

        int n = filteredCoins.size();
        long[] c = new long[n];
        for (int i = 0; i < n; i++) {
            c[i] = filteredCoins.get(i);
        }

        // Binary Search bounds
        long left = 1;
        long right = c[0] * (long) k;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countMultiples(mid, c, n) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private long countMultiples(long m, long[] coins, int n) {
        long count = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > m) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                if (bits % 2 == 1) {
                    count += m / currentLcm;
                } else {
                    count -= m / currentLcm;
                }
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}