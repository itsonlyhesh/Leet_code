class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (c - '0');
            }
        }

        // Alice wins if Bob cannot force the sums to balance
        // Equivalent to: sumDiff * 2 + qDiff * 9 != 0
        return sumDiff * 2 + qDiff * 9 != 0;
    }
}