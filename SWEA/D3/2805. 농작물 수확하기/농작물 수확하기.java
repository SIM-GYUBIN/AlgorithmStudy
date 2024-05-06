import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int T;
    static int N;
    static int[][] farm;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {

            N = Integer.parseInt(br.readLine());

            farm = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    farm[i][j] = line.charAt(j) - '0';
                }
            }

            /**
             * logic
             */

            int result = calResult();

            sb.append('#').append(caseNum).append(' ').append(result).append('\n');

        }
        System.out.println(sb);
    }

    private static int calResult() {
        if (N == 1) {
            return farm[0][0];
        }

        int cnt = 0;
        int flag = N/2;
        int row = 0;
        int range = 1;
        while(row!=N) {
            //N에서 range빼고 2로 나눈걸 앞에서부터 세고 range하고 그다음 나머지

            int sp = 0 + ((N-range)/2);
            int ep = (N-1) - ((N-range)/2);
            for (int i = sp; i <= ep; i++) {
                cnt += farm[row][i];
            }


            //다음 턴
            if (row < flag) {
                range += 2;
            } else {
                range -= 2;
            }
            row++;
        }

        return cnt;
    }
}