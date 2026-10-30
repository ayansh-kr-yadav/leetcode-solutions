// 66 ms | 274.8 MB
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
        dist[0][1] = 0;

        // State: {cost, node, streak}
        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] state = pq.poll();
            long cost = state[0];
            int u = (int) state[1], streak = (int) state[2];

            if (cost != dist[u][streak]) continue;
            if (u == n - 1) return (int) cost;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int next = labels.charAt(u) == labels.charAt(v)
                         ? streak + 1 : 1;

                if (next > k) continue;

                long newCost = cost + edge[1];
                if (newCost < dist[v][next]) {
                    dist[v][next] = newCost;
                    pq.offer(new long[]{newCost, v, next});
                }
            }
        }
        return -1;
    }
}