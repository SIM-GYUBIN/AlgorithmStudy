import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;


    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] smallArr = new int[N];
            int[] bigArr = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                smallArr[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                bigArr[i] = Integer.parseInt(st.nextToken());
            }

            if (N > M) {
                int[] tmp = smallArr;
                smallArr = bigArr;
                bigArr = tmp;
            }

            /**
             * logic
             */

            int result = 0;
            int sp = 0;
            int ep = smallArr.length-1;

            while(ep <= bigArr.length-1) {

                int j = 0;
                int sum = 0;
                for (int i = sp; i <= ep; i++) {
                    sum += smallArr[j] * bigArr[i];
                    j++;
                }

                result = Math.max(result, sum);

                //다음 턴
                sp++;
                ep++;
            }
            sb.append("#").append(caseNum).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
}