import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Road implements Comparable<Road>{
        int to;
        int cost;

        public Road(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sbResult = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Road>> village = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            village.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            village.get(a).add(new Road(b, c));
            village.get(b).add(new Road(a, c));
        }

        //prim
        PriorityQueue<Road> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N+1];
        int costSum = 0;
        int bigRoad = 0;

        pq.offer(new Road(1, 0));
        while (!pq.isEmpty()) {
            Road now = pq.poll();

            if (visit[now.to]) continue;
            visit[now.to] = true;
            costSum += now.cost;
            bigRoad = Math.max(bigRoad, now.cost);

            for (Road road : village.get(now.to)) {
                pq.offer(road);
            }
        }
        System.out.println(costSum-bigRoad);
    }
}