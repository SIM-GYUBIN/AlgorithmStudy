import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int K;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;
            //가로
            for (int row = 0; row < N; row++) {
                int cnt = 0;
                for (int col = 0; col < N; col++) {
                    if (arr[row][col]==1) {
                        cnt++;
                    } else {
                        cnt = 0;
                    }

                    if (cnt == K) {
                        if(col==N-1 ||arr[row][col+1]==0) {
                            result++;
                        }
                    }
                }
            }
            //세로
            for (int col = 0; col < N; col++) {
                int cnt = 0;
                for (int row = 0; row < N; row++) {
                    if (arr[row][col]==1) {
                        cnt++;
                    } else {
                        cnt = 0;
                    }

                    if (cnt == K) {
                        if(row==N-1 ||arr[row+1][col]==0) {
                            result++;
                        }
                    }
                }
            }


            sb.append('#').append(caseNum).append(' ').append(result).append('\n');

        }
        System.out.println(sb);
    }
}