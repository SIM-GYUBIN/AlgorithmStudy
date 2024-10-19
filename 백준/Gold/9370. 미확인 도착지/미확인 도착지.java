import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static List<ArrayList<Node>> list;

    static long[] dist;
    static boolean[] dest;
    static HashSet<Integer> result;

    public static class Node implements Comparable<Node> {
        int num;
        long cost;
        boolean flag1;
        boolean flag2;

        public Node(int num, long cost, boolean flag1, boolean flag2) {
            this.num = num;
            this.cost = cost;
            this.flag1 = flag1;
            this.flag2 = flag2;
        }

        @Override
        public int compareTo(Node o) {

            if (this.cost != o.cost) {
                return (int) (this.cost - o.cost);
            } else {
                int cntThis = 0;
                int cntO = 0;

                if (this.flag1) cntThis++;
                if (this.flag2) cntThis++;
                if (o.flag1) cntO++;
                if (o.flag2) cntO++;

                if (cntThis > cntO) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sbResult = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int caseNum = 0; caseNum < T; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); //교차로
            int M = Integer.parseInt(st.nextToken()); //도로
            int K = Integer.parseInt(st.nextToken()); //목적지 후보 개수

            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken()); //예술가 출발지
            int G = Integer.parseInt(st.nextToken()); //여기서
            int H = Integer.parseInt(st.nextToken()); //여기 사이를 지나간적 있음

            list = new ArrayList<>();
            dist = new long[N + 1];

//            Arrays.fill(dist, Long.MAX_VALUE);
            Arrays.fill(dist, 100_000_000);

            for (int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.get(p1).add(new Node(p2, cost, false, false));
                list.get(p2).add(new Node(p1, cost, false, false));
            }

            dest = new boolean[N + 1];   //목적지 후보지 들어있는 배열
            for (int i = 0; i < K; i++) {
                int num = Integer.parseInt(br.readLine());
                dest[num] = true;
            }

            result = new HashSet<>();

            //초기화 끝

            dijkstra(S, G, H);

            List<Integer> tempSet = new ArrayList<>(result);
            Collections.sort(tempSet);

            StringBuilder sb = new StringBuilder();
            int size = tempSet.size();
            for (int i = 0; i < size; i++) {
                sb.append(tempSet.get(i)).append(" ");
            }
            String sbs = sb.toString().trim();
            sbResult.append(sbs);
            if (caseNum != T - 1) {
                sbResult.append("\n");
            }
        }
        System.out.print(sbResult);
    }

    private static void dijkstra(int startP, int flag1, int flag2) {
        //dijksktra
        PriorityQueue<Node> myQ = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];
        dist[startP] = 0;
        myQ.add(new Node(startP, 0, false, false));

        while (!myQ.isEmpty()) {
            Node nowNode = myQ.poll();
            int nowNum = nowNode.num;

            if (visit[nowNum]) {
                continue;
            }
            visit[nowNum] = true;

            // 중복x , 시작지점이 g,h 대비
            if (nowNum == flag1) {
                nowNode.flag1 = true;
            } else if (nowNum == flag2) {
                nowNode.flag2 = true;
            }

            if (dest[nowNode.num]) {  // 목적지라면
                if (nowNode.flag1 && nowNode.flag2) {
                    result.add(nowNode.num);
                }
            }

            for (Node nextNode : list.get(nowNum)) {

                // 목적지 == g,h 대비
//                boolean f1 = nowNode.flag1;
//                boolean f2 = nowNode.flag2;
//                if (nextNode.num == flag1) {
//                    f1 = true;
//                } else if (nextNode.num == flag2) {
//                    f2 = true;
//                }

                if (!visit[nextNode.num] && dist[nextNode.num] >= dist[nowNum] + nextNode.cost) {

                    dist[nextNode.num] = dist[nowNum] + nextNode.cost;
                    myQ.offer(new Node(nextNode.num, dist[nextNode.num], nowNode.flag1,  nowNode.flag2));
                }

                // 이미 방문한 곳을 같은 비용을 가지고 방문을 했고 심지어 나는 flag를 두개 밟았는데 무시되는 경우 예외
//                else if (dist[nextNode.num] == dist[nowNum] + nextNode.cost) {
//                    myQ.offer(new Node(nextNode.num, dist[nextNode.num], f1, f2));
//                }
            }
        }
    }
}