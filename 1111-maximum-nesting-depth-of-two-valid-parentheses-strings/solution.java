// 1 ms | 45.5 MB
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            
            if (c == '(') {
                // Assign based on current depth parity, then increment depth
                ans[i] = depth % 2;
                depth++;
            } else {
                // Decrement depth first, then assign based on parity to match '('
                depth--;
                ans[i] = depth % 2;
            }
        }

        return ans;
    }
}