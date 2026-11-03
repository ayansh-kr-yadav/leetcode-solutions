// 0 ms | 23.6 MB
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
       int i=0;
       int j=0;
       while(i<nums.size() && j< nums.size()){
        if(nums[i]!=0){
            i++;
        }
        if(j> i && j!=0){
            swap(nums[j],nums[i]);
            j++;
        }
        else j++;
       } 
    }
};