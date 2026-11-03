// 0 ms | 31.3 MB
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int i=0;
        int moves=0;
        int highest=INT_MIN;
        for(int i=0 ; i< nums.size() ; i++){
            highest=max(highest, nums[i]);
        }
        while(i<nums.size()){
            moves+=highest-nums[i];
            i++;
        }
        return moves;
    }
};