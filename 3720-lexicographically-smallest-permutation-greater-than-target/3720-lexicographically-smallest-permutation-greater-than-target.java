class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (int i = 0; i < n; i++) {
            totalCount[s.charAt(i) - 'a']++;
        }

        // Find how long we can match target exactly
        int[] count = totalCount.clone();
        int maxMatch = 0;
        while (maxMatch < n && count[target.charAt(maxMatch) - 'a'] > 0) {
            count[target.charAt(maxMatch) - 'a']--;
            maxMatch++;
        }

        // Try to place a character greater than target[i] at index i,
        // starting from the longest possible prefix match down to 0.
        for (int i = maxMatch; i >= 0; i--) {
            // Reconstruct the frequency array for remaining characters after matching target[0..i-1]
            int[] remCount = totalCount.clone();
            for (int j = 0; j < i; j++) {
                remCount[target.charAt(j) - 'a']--;
            }

            if (i < n) {
                int targetChar = target.charAt(i) - 'a';
                // Look for the smallest available character strictly greater than target[i]
                for (int c = targetChar + 1; c < 26; c++) {
                    if (remCount[c] > 0) {
                        StringBuilder sb = new StringBuilder();
                        // 1. Matched prefix
                        sb.append(target, 0, i);
                        // 2. The strictly greater character at index i
                        sb.append((char) ('a' + c));
                        remCount[c]--;

                        // 3. Fill the rest in ascending order
                        for (int k = 0; k < 26; k++) {
                            while (remCount[k] > 0) {
                                sb.append((char) ('a' + k));
                                remCount[k]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
        }

        return "";
    }
}