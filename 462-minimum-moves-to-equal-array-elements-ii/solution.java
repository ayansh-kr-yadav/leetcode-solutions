// 11 ms | 46.7 MB
import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find the median
        Arrays.sort(nums);
        
        // The median minimizes the sum of absolute differences
        int median = nums[nums.length / 2];
        int moves = 0;
        
        // Calculate the total moves to make all elements equal to the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}