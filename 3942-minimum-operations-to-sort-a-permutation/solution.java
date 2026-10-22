// 4 ms | 94.6 MB
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Find the index of 0
        int zero = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        // Check if it's cyclically increasing
        boolean isInc = true;
        for (int i = 1; i < n; i++) {
            if (nums[(zero + i) % n] != i) {
                isInc = false;
                break;
            }
        }
        
        // Check if it's cyclically decreasing
        boolean isDec = true;
        for (int i = 1; i < n; i++) {
            if (nums[(zero - i + n) % n] != i) {
                isDec = false;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // If it is cyclically increasing, calculate the minimum of both strategies
        if (isInc) {
            ans = Math.min(ans, zero);              // Just rotate left
            ans = Math.min(ans, n - zero + 2);      // Reverse, rotate left, reverse (simulates right rotate)
        }
        
        // If it is cyclically decreasing, calculate the minimum of both strategies
        if (isDec) {
            ans = Math.min(ans, zero + 2);          // Rotate left until 0 is at the end, then reverse
            ans = Math.min(ans, n - zero);          // Reverse first, then rotate left
        }
        
        // Return -1 if it was neither cyclically increasing nor decreasing
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}