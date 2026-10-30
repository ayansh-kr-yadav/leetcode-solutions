// 0 ms | 8.6 MB
class Solution {
public:
    int addDigits(int num) {


            if(num==0){
                return 0;
            }
            return ( 1+ (num-1)%9)
            ;


        //   int sum=0;
        // int rem=0;
        // int ans=0;
        // if(num < 10 ){
        //     return num;
        // }
        // else { 
        //     while(num>0){  
        //      sum+=num%10;
        //     num/=10;
        // }
        //     if(sum>10){
        //     while(sum >0){
        //         ans+=sum%10;
        //         sum=sum/10;
        //     }
        // }

        // }
        // return ans;
    }
};