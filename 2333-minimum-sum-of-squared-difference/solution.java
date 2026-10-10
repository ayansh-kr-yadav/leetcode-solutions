// 9 ms | 117.5 MB
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        
        // Find the maximum possible difference to size the bucket array
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        if (maxDiff == 0) {
            return 0L;
        }

        // count[d] stores the number of pairs with absolute difference d
        int[] count = new int[maxDiff + 1];
        for (int i = 0; i < n; i++) {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }

        long k = (long) k1 + k2;

        // Flatten the largest differences downwards
        for (int d = maxDiff; d > 0; d--) {
            if (count[d] == 0) {
                continue;
            }

            if (k >= count[d]) {
                // We have enough operations to decrement all elements of size d to d - 1
                k -= count[d];
                count[d - 1] += count[d];
                count[d] = 0;
            } else {
                // Distribute the remaining k operations among elements of size d
                count[d - 1] += (int) k;
                count[d] -= (int) k;
                k = 0;
                break;
            }
        }

        // Calculate the sum of squared differences
        long result = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * d * d;
            }
        }

        return result;
    }
}