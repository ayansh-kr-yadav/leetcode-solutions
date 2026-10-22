// 1 ms | 97.2 MB
class Solution {
public:
    int maxProfit(vector<int>& prices) {
       int minbuy=prices[0];
       int maxprofit=0;
       int profit=0;
       for(int i=1 ; i< prices.size() ; i++){
         profit=prices[i]-minbuy;
        if(profit> maxprofit){
            maxprofit=profit;
            
        }
        if (prices[i]< minbuy){
            minbuy=prices[i];
        }
       }
       return maxprofit;
    }
};