// 11 ms | 117.1 MB
import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
        // Use Integer.MAX_VALUE / 2 to prevent integer overflow during addition
        final int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, INF);
        
        // Base case: starting at step 0 costs 0
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            int currentCost = costs[i - 1];
            
            // Check the last 1, 2, or 3 steps we could have jumped from
            for (int j = Math.max(0, i - 3); j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + currentCost + (i - j) * (i - j));
            }
        }
        
        return dp[n];
    }
}