// 135 ms | 225 MB
import java.util.*;

class Solution {
    int LOG;
    int[][] up;
    int[] depth, tin, size, prefix, bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        size = new int[n];
        prefix = new int[n];
        bit = new int[n + 2];

        char[] chars = s.toCharArray();
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, timer = 0;
        stack[top++] = 0;

        // Iterative DFS avoids stack overflow on a long chain.
        while (top > 0) {
            int u = stack[--top];
            tin[u] = ++timer;
            order[timer - 1] = u;
            size[u] = 1;

            int mask = 1 << (chars[u] - 'a');
            prefix[u] = mask ^ (u == 0 ? 0 : prefix[up[0][u]]);

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            for (int v : graph[u]) {
                if (v == up[0][u]) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[up[0][u]] += size[u];
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);
                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                // Update root-path masks of every descendant.
                add(tin[u], delta);
                add(tin[u] + size[u], delta);
                chars[u] = c;
            } else {
                int v = Integer.parseInt(parts[2]);
                int ancestor = lca(u, v);

                int mask = prefix[u] ^ get(tin[u])
                         ^ prefix[v] ^ get(tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void add(int i, int value) {
        for (; i < bit.length; i += i & -i) {
            bit[i] ^= value;
        }
    }

    private int get(int i) {
        int result = 0;
        for (; i > 0; i -= i & -i) {
            result ^= bit[i];
        }
        return result;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) u = up[j][u];
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return up[0][u];
    }
}