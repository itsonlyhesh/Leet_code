class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suffix[i] = max suffix length of word2 that can be matched in word1[i...n-1]
        int[] suffix = new int[n + 1];
        int j = m - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1];
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suffix[i]++;
                j--;
            }
        }

        int[] result = new int[m];
        boolean usedMismatch = false;
        int idx1 = 0;
        int idx2 = 0;

        while (idx1 < n && idx2 < m) {
            // Case 1: Exact character match
            if (word1.charAt(idx1) == word2.charAt(idx2)) {
                result[idx2] = idx1;
                idx2++;
            } 
            // Case 2: Mismatch, try using the allowed 1-character change as early as possible
            else if (!usedMismatch && suffix[idx1 + 1] >= m - idx2 - 1) {
                result[idx2] = idx1;
                usedMismatch = true;
                idx2++;
            }
            
            idx1++;
        }

        return idx2 == m ? result : new int[0];
    }
}
