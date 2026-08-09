class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n];
        
        // Precompute factorials and populate numbers list
        int fact = 1;
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
            factorial[i] = fact;
            numbers.add(i);
        }
        numbers.add(n); // numbers = [1, 2, ..., n]
        
        // Convert k to 0-based index
        k--;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k %= factorial[i];
        }
        
        return sb.toString();
    }
}