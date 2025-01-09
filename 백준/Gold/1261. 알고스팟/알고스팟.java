import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] map;
    static int[][] dist;

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        map = new int[N][M];
        dist = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int c = 0; c < M; c++) {
                int num = line.charAt(c) - '0';
                map[r][c] = num;
            }
        }


        dijkstra();
        System.out.println(dist[N-1][M-1]);
    }

    private static void dijkstra() {
        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            // 도착지 방문 시 break?
            if (now.r==N-1 && now.c==M-1) break;

            for (int[] dir : dirArr) {
                int nextR = now.r + dir[0];
                int nextC = now.c + dir[1];

                // 방문했으면 비교
                // 방문안했으면 그냥 고
                if (canGo(nextR, nextC)
                        && (!visit[nextR][nextC] || dist[nextR][nextC] > now.cost + map[nextR][nextC])) {
                    dist[nextR][nextC] = now.cost + map[nextR][nextC];
                    visit[nextR][nextC] = true;
                    pq.offer(new Node(nextR, nextC, dist[nextR][nextC]));
                }
            }
        }
    }

    private static boolean canGo(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
