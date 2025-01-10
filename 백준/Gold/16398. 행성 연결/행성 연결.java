import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N; // 행성 개수
    static List<List<Flow>> flowList;

    static class Flow implements Comparable<Flow> {
        int to, cost;

        public Flow(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Flow o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        flowList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            flowList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                flowList.get(i).add(new Flow(j, num));
            }
        }

        System.out.println(dijkstra());
    }

    private static long dijkstra() {
        boolean[] visit = new boolean[N];

        PriorityQueue<Flow> pq = new PriorityQueue<>();
        pq.offer(new Flow(0, 0));

        long costSum = 0;
        int visitNum = 0;

        while (!pq.isEmpty()) {
            Flow now = pq.poll();

            if (visit[now.to]) continue;
            visit[now.to] = true;
            costSum += now.cost;

            visitNum++;
            if (visitNum==N) break;


            for (Flow flow : flowList.get(now.to)) {
                if ((now.to != flow.to) && !visit[flow.to]) {
                    pq.offer(new Flow(flow.to, flow.cost));
                }
            }
        }

        return costSum;
    }
}
