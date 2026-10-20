// 43 ms | 70.3 MB
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && check(nums, k)) {
                answer += k;
            }
        }
        return answer;
    }

    private boolean check(int[] nums, int k) {
        int previousMax = Integer.MIN_VALUE;

        for (int start = 0; start < nums.length; start += k) {
            int end = start + k - 1;
            int min = nums[start], max = nums[start];
            int drops = 0;

            for (int i = start; i <= end; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);

                if (i < end && nums[i] > nums[i + 1]) {
                    drops++;
                }
            }

            // Include the circular connection.
            if (nums[end] > nums[start]) {
                drops++;
            }

            if (drops > 1 || previousMax > min) {
                return false;
            }
            previousMax = max;
        }
        return true;
    }
}
