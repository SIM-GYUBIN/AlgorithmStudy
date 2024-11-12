import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 역방향 리스트로
 * 진입점(바로 지을 수 있는 애들) 들을 찾으며, 최장시간을 기록
 */
public class Main {

    static int N, V, RESULT;
    static List<List<Integer>> revlist;
    static int[] timeArr;
    static int[] dp; //V에서 해당 노드까지의 거리 중 최대
    static int[] memo; // 출발지에서 해당 노드까지의 거리 중 최대
    static boolean[] visit; // 출발지에서 해당 노드까지 거리 탐색 완료된 것들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int caseNum = 0; caseNum < T; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            timeArr = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                timeArr[i] = Integer.parseInt(st.nextToken());
            }

            revlist = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                revlist.add(new ArrayList<>());
            }

//            dp = new int[N + 1];
//            Arrays.fill(dp, -1);
            visit = new boolean[N + 1];
            memo = new int[N + 1];


            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                revlist.get(to).add(from);
            }
            V = Integer.parseInt(br.readLine());

            RESULT = 0;

            int dfsed = dfs(V);

            sb.append(dfsed).append("\n");
        }
        System.out.println(sb);
    }


    private static int dfs(int num) {

        if (revlist.get(num).isEmpty()) {
//            RESULT = Math.max(RESULT, sec + timeArr[num]); //손 보자
            memo[num] = timeArr[num];
            visit[num] = true;
            return timeArr[num];
        }

//        if (dp[num] >= sec) {
//            return; //갈 필요 없는뎅
//        } else {
//            dp[num] = sec;
//        }

        if (visit[num]) {
            return memo[num];
        }

        int tmp = 0;
        for (Integer next : revlist.get(num)) {
            int rtn = dfs(next);
            tmp = Math.max(tmp, rtn + timeArr[num]);
        }
//        방문처리
        visit[num] = true;
        memo[num] = tmp;

        return memo[num];
    }
}