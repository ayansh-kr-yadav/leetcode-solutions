// 106 ms | 95.1 MB
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        int i = 0, j = 0;
        long long maxsum = 0, sum = 0;
        unordered_map<int, int> freq;

        while (j < nums.size()) {
            sum += nums[j];
            freq[nums[j]]++;

            if (j - i + 1 == k) {
                if (freq.size() == k) {
                    maxsum = max(maxsum, sum);
                }

                sum -= nums[i];
                freq[nums[i]]--;

                if (freq[nums[i]] == 0) {
                    freq.erase(nums[i]);
                }

                i++;
            }
            j++;
        }

        return maxsum;
    }
};