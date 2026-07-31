class Solution {
    public int minimumPushes(String word) {
    int n = word.length();
        int pushes = 0;
        
        // 1 push for the first 8 characters
        pushes += Math.min(n, 8) * 1;
        
        // 2 pushes for characters 9 through 16
        if (n > 8) {
            pushes += Math.min(n - 8, 8) * 2;
        }
        
        // 3 pushes for characters 17 through 24
        if (n > 16) {
            pushes += Math.min(n - 16, 8) * 3;
        }
        
        // 4 pushes for characters 25 and 26
        if (n > 24) {
            pushes += (n - 24) * 4;
        }
        
        return pushes;
    }
}