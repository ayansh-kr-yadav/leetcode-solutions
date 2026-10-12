// 62 ms | 80.3 MB
import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        
        // bitCount[b] records the number of set bits at position b (up to 30 bits for 10^9)
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }

        long totalSum = 0;

        // Construct the k largest possible numbers greedily
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCount[b] > 0) {
                    currentNum |= (1L << b);
                    bitCount[b]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }

        return (int) totalSum;
    }
}