// 2 ms | 42.3 MB
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Strategy 1: Buy only separate items for both needs
        long costA = (long) need1 * cost1 + (long) need2 * cost2;
        
        // Strategy 2: Buy only combined items to cover the maximum need
        long costB = (long) costBoth * Math.max(need1, need2);
        
        // Strategy 3: Buy combined items for the overlapping amount, and individual items for leftovers
        int mn = Math.min(need1, need2);
        long costC = (long) costBoth * mn + 
                     (long) (need1 - mn) * cost1 + 
                     (long) (need2 - mn) * cost2;
        
        // Return the minimum of all valid strategies
        return Math.min(costA, Math.min(costB, costC));
    }
}