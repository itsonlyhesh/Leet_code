class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t for 2, 3, 5, 7
        long temp = t;
        int target2 = 0, target3 = 0, target5 = 0, target7 = 0;
        
        while (temp % 2 == 0) { target2++; temp /= 2; }
        while (temp % 3 == 0) { target3++; temp /= 3; }
        while (temp % 5 == 0) { target5++; temp /= 5; }
        while (temp % 7 == 0) { target7++; temp /= 7; }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (temp > 1) return "-1";

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = num.charAt(i) - '0';
        }

        // Check if num itself is zero-free and valid
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                firstZero = i;
                break;
            }
        }

        // Prefix prime factor counts for num
        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pref2[i + 1] = pref2[i] + countFactor(digits[i], 2);
            pref3[i + 1] = pref3[i] + countFactor(digits[i], 3);
            pref5[i + 1] = pref5[i] + countFactor(digits[i], 5);
            pref7[i + 1] = pref7[i] + countFactor(digits[i], 7);
        }

        // If num is already zero-free and digit product is divisible by t
        if (firstZero == n &&
            pref2[n] >= target2 && pref3[n] >= target3 &&
            pref5[n] >= target5 && pref7[n] >= target7) {
            return num;
        }

        // Step 2: Try to match a prefix of length i (from min(n-1, firstZero) down to 0)
        int maxI = Math.min(n - 1, firstZero);
        for (int i = maxI; i >= 0; i--) {
            int currentDigit = digits[i];
            
            // Try increasing digit at position i
            for (int d = currentDigit + 1; d <= 9; d++) {
                int rem2 = Math.max(0, target2 - pref2[i] - countFactor(d, 2));
                int rem3 = Math.max(0, target3 - pref3[i] - countFactor(d, 3));
                int rem5 = Math.max(0, target5 - pref5[i] - countFactor(d, 5));
                int rem7 = Math.max(0, target7 - pref7[i] - countFactor(d, 7));

                int remLen = n - 1 - i;
                if (minLen(rem2, rem3, rem5, rem7) <= remLen) {
                    // Valid configuration found! Construct the result
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < i; k++) {
                        sb.append(digits[k]);
                    }
                    sb.append(d);
                    sb.append(fillSuffix(rem2, rem3, rem5, rem7, remLen));
                    return sb.toString();
                }
            }
        }

        // Step 3: If no valid string of length n exists, construct for length L > n
        int minReqLen = minLen(target2, target3, target5, target7);
        int targetLen = Math.max(n + 1, minReqLen);
        
        return fillSuffix(target2, target3, target5, target7, targetLen);
    }

    // Helper to calculate minimum digits needed to satisfy required prime factor powers
    private int minLen(int r2, int r3, int r5, int r7) {
        int base = r5 + r7;
        int min23 = Integer.MAX_VALUE;

        // Try using c6 6s (where c6 = 0, 1, 2)
        for (int c6 = 0; c6 <= 2; c6++) {
            int rem2 = Math.max(0, r2 - c6);
            int rem3 = Math.max(0, r3 - c6);

            int count9 = rem3 / 2;
            int rem3Mod = rem3 % 2;

            int count8 = rem2 / 3;
            int rem2Mod = rem2 % 3;

            int extra = 0;
            if (rem3Mod == 0 && rem2Mod == 0) extra = 0;
            else if (rem3Mod == 1 && rem2Mod == 2) extra = 2;
            else extra = 1;

            min23 = Math.min(min23, c6 + count9 + count8 + extra);
        }

        return base + min23;
    }

    // Greedily fill a suffix of given length to be lexicographically smallest
    private String fillSuffix(int r2, int r3, int r5, int r7, int len) {
        StringBuilder sb = new StringBuilder();
        
        for (int pos = 0; pos < len; pos++) {
            int lenAfter = len - 1 - pos;
            
            for (int d = 1; d <= 9; d++) {
                int n2 = Math.max(0, r2 - countFactor(d, 2));
                int n3 = Math.max(0, r3 - countFactor(d, 3));
                int n5 = Math.max(0, r5 - countFactor(d, 5));
                int n7 = Math.max(0, r7 - countFactor(d, 7));

                if (minLen(n2, n3, n5, n7) <= lenAfter) {
                    sb.append(d);
                    r2 = n2; r3 = n3; r5 = n5; r7 = n7;
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Helper to count occurrences of prime factor p in digit d
    private int countFactor(int d, int p) {
        if (d == 0) return 0;
        int count = 0;
        while (d > 0 && d % p == 0) {
            count++;
            d /= p;
        }
        return count;
    }
}