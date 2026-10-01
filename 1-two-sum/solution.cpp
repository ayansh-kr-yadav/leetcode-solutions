// 0 ms | 14.7 MB
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // crete hashmap 
        // traverse the array and calculate the differece =target-nums[i];
        // check that difference found in hashmap or not 
        // if found return { hashmp [ differene and i]}
        // if not found then store it into hashmap

        unordered_map<int , int  > hash;

        for(int i=0; i<nums.size() ; i++){
           
           int diff= target-nums[i];

            if(hash.find(diff) != hash.end()){
                return {hash[diff], i};
            }
            hash[nums[i]]=i;
        }
        return {};
    }
};