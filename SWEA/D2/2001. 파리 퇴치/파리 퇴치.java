import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;
    static int M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int caseNum = 0; caseNum < T; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //logic

            int[] sp = {0,0};
            int[] ep = {sp[0] + M - 1, sp[1] + M - 1};
            int maxCnt = 0;

            while(ep[0]<N) {
                int killFly = 0;
                for (int i = sp[0]; i <= ep[0]; i++) {
                    for (int j = sp[1]; j <= ep[1]; j++) {
                        killFly += arr[i][j];
                    }
                }

                maxCnt = Math.max(killFly, maxCnt);

                //다음 위치
                if (ep[1] == N-1) { //오른쪽으로 넘어간다면
                    sp[0] += 1;
                    sp[1] = 0;
                    ep[0] = sp[0] + M - 1;
                    ep[1] = sp[1] + M - 1;
                } else {
                    sp[1] += 1;
                    ep[1] += 1;
                }
            }
            System.out.println("#"+(caseNum+1)+" "+maxCnt);
        }
    }
}