class Solution {
    public List<Integer> findMissingElements(int[] nums) {
    Arrays.sort(nums);
        List<Integer> missing = new ArrayList<>();
        
        for (int i = 1; i < nums.length; i++) {
            for (int val = nums[i - 1] + 1; val < nums[i]; val++) {
                missing.add(val);
            }
        }
        
        return missing;
    }
}