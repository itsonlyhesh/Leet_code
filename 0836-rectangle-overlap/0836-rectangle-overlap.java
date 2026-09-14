class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if 1D projections overlap on both axes
        boolean xOverlap = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        boolean yOverlap = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
        
        return xOverlap && yOverlap;
    }
}