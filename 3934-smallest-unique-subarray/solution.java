// 277 ms | 205.5 MB
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE1 = 100_003L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 100_019L;
    private static final long MOD2 = 1_000_000_009L;

    private long[] pref1, pref2;
    private long[] pow1, pow2;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        pref1 = new long[n + 1];
        pref2 = new long[n + 1];
        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
            pref1[i + 1] = (pref1[i] * BASE1 + nums[i]) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + nums[i]) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int len) {
        // Maps packed hash -> Boolean.TRUE if seen once, Boolean.FALSE if duplicated
        Map<Long, Boolean> seen = new HashMap<>(n - len + 1);

        for (int i = 0; i <= n - len; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % MOD2 + MOD2) % MOD2;
            long key = (h1 << 32) | h2;

            Boolean prev = seen.putIfAbsent(key, Boolean.TRUE);
            if (prev == Boolean.TRUE) {
                seen.put(key, Boolean.FALSE);
            }
        }

        return seen.containsValue(Boolean.TRUE);
    }
}