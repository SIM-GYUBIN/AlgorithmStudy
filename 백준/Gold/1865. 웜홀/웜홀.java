import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge> list;

    static class Edge {
        int v;
        int w;
        int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        /**
         * 벨먼 포드 연습
         * 음의 순환 여부 확인 문제
         *
         * 틀렸던 이유 : 모든 정점이 연결되 있는게 아닐 수도 있음;;;
         * 시작점을 그냥 1로하면 안됐음 (1번 정점이 연결되어 있지 않을 수도 있음)
         */
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum <= T; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                list.add(new Edge(s, e, t));
                list.add(new Edge(e, s, t));
            }

            List<Integer> startList = new ArrayList<>();

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken()) * -1;
                list.add(new Edge(s, e, t));
                startList.add(s);
            }

            /**
             * 시작점을 어디로 하든 외딴섬일 수 있음
             * 웜홀 주어지는 곳들로만 돌려봄 (웜홀 있어야지 음수사이클 생기니까)
             */
            boolean flag = false;
            for (Integer s : startList) {
                if (bellman(N, (2*M)+W, s)) {
                    flag = true;
                    break;
                }
            }

            sb.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bellman(int nodeNum, int edgeNum, int start) {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[nodeNum+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;    //음수 사이클 있는지만 확인하므로 시작점 상관 x

        for (int i = 0; i < nodeNum; i++) { // 정점 수 만큼 반복 (N-1번) + N번째 한번에
            for (int j = 0; j < edgeNum; j++) {
                Edge edge = list.get(j);

                if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;

                    if (i == nodeNum - 1) { // N번째 확인 (음의 가중치 순환 확인)
                        return true;
                    }
                }
            }
        }
        return false;
    }
}