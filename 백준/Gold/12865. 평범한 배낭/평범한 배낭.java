import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //물품의 수
        int K = Integer.parseInt(st.nextToken());   //버틸 수 있는 무게

        int[] W = new int[N+1];
        int[] V = new int[N+1];
        int[][] arr = new int[N+1][K+1];

        /**
         * 2차원 dp 냅색
         * https://www.youtube.com/watch?v=S-7YAuT9nDk <= 이거 보고 풀었음
         */

        //K를 넘기지 않고 가장 최고의 가치
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());  //각 물건 무게
            int v = Integer.parseInt(st.nextToken());  //각 물건 가치
            W[i] = w;
            V[i] = v;
        }

        for (int i = 1; i <= N; i++) { //물품 하나씩
            for (int j = 1; j <= K; j++) { //무게 하나씩
                int before = arr[i-1][j];   //위칸의 수 (j무게에 대한 i-1까지의 물품까지에 대한 최대)
                int diff = j-W[i] < 0 ? 0 : arr[i-1][j-W[i]] + V[i];   //i번째의 물건 넣고도 남은 공간에 넣을 수 있는 최대(i-1번째에 W[i]뺀 무게) 넣기

                arr[i][j] = Math.max(before, diff);
            }
        }

        System.out.println(arr[N][K]);

    }
}
