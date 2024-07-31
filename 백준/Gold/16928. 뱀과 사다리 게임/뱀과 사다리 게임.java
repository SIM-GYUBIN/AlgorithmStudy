import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 사다리 수
        M = Integer.parseInt(st.nextToken());   // 뱀 수

        int[] pan = new int[101];
        int[] memo = new int[101];
        Arrays.fill(memo, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {   // 사다리 입력
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            pan[start] = to;
        }

        for (int i = 0; i < M; i++) {   // 뱀 입력
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            pan[start] = to;
        }


        //탐색
        Queue<Integer> myQ = new LinkedList<>();
        myQ.offer(1);
        memo[1] = 0;

        while (!myQ.isEmpty()) {
            int now = myQ.poll();
            int nowTurn = memo[now];

            if (now==100) {
                break;
            }

            for (int i = 1; i < 7; i++) {   //주사위 돌리기
                if (now+i <= 100) {
                    int next = pan[now + i] == 0 ? now + i : pan[now + i];    //사다리나 뱀 있으면 이동 아니면 그냥 눈금만큼
                    if (nowTurn + 1 < memo[next]) { //판 안넘기고, 다음 위치 가는 턴이 적으면
                        myQ.offer(next);
                        memo[next] = nowTurn + 1;
                    }
                }
            }
        }

        System.out.println(memo[100]);


    }
}