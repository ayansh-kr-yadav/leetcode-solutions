// 319 ms | 180.4 MB

import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long delta) {
            while (i <= n) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find the smallest index whose prefix sum >= k
        int kth(long k) {
            int idx = 0;
            int bit = Integer.highestOneBit(n);

            while (bit > 0) {
                int next = idx + bit;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= tree[next];
                }

                bit >>= 1;
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int val : sorted) {
            if (m == 0 || sorted[m - 1] != val) {
                sorted[m++] = val;
            }
        }

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
        }

        Fenwick countBIT = new Fenwick(m);
        Fenwick sumBIT = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        // Calculate cost of every window of size x
        for (int i = 0; i < n; i++) {

            int r = rank[i];

            countBIT.update(r, 1);
            sumBIT.update(r, nums[i]);

            windowSum += nums[i];

            // Remove outgoing element
            if (i >= x) {
                int oldRank = rank[i - x];

                countBIT.update(oldRank, -1);
                sumBIT.update(oldRank, -nums[i - x]);

                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {

                // Median position (1-based)
                int medianRank = countBIT.kth((x + 1L) / 2);

                int median = sorted[medianRank - 1];

                long leftCount = countBIT.query(medianRank);
                long leftSum = sumBIT.query(medianRank);

                long rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                long leftCost =
                    (long) median * leftCount - leftSum;

                long rightCost =
                    rightSum - (long) median * rightCount;

                cost[i - x + 1] = leftCost + rightCost;
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Skip element
                dp[i][j] = dp[i - 1][j];

                // Select window ending at i - 1
                if (i >= x) {

                    long previous = dp[i - x][j - 1];

                    if (previous != INF) {
                        dp[i][j] = Math.min(
                            dp[i][j],
                            previous + cost[i - x]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }
}
