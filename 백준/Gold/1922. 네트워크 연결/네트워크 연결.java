import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Network implements Comparable<Network>{
        int to;
        int cost;

        public Network(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Network o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sbResult = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Network>> networks = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            networks.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a==b) continue;

            networks.get(a).add(new Network(b, c));
            networks.get(b).add(new Network(a, c));
        }

        //prim
        PriorityQueue<Network> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N+1];
        int costSum = 0;

        pq.offer(new Network(1, 0));
        while (!pq.isEmpty()) {
            Network now = pq.poll();

            if (visit[now.to]) continue;
            visit[now.to] = true;
            costSum += now.cost;

            for (Network network : networks.get(now.to)) {
                pq.offer(network);
            }
        }
        System.out.println(costSum);
    }
}