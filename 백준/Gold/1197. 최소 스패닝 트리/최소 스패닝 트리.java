import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<List<Edge>> list;

    static class Edge implements Comparable<Edge> {
        int num, cost;

        public Edge(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Edge(b, c));
            list.get(b).add(new Edge(a, c));
        }

        //prim
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[V + 1];
        int[] minEdge = new int[V + 1];
        pq.offer(new Edge(1, 0));
        int costSum = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visit[edge.num]) continue;

            costSum += edge.cost;
            visit[edge.num] = true;

            for (Edge e : list.get(edge.num)) {
                if (!visit[e.num]) {
                    pq.offer(new Edge(e.num, e.cost));
                }
            }
        }

        System.out.println(costSum);
    }
}
