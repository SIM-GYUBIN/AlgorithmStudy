import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;
    static int[][] arr;
    static int[][] arr90;
    static int[][] arr180;
    static int[][] arr270;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            arr90 = new int[N][N];
            arr180 = new int[N][N];
            arr270 = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr90[j][N-1-i] = arr[i][j];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr180[j][N-1-i] = arr90[i][j];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr270[j][N-1-i] = arr180[i][j];
                }
            }

            sb.append("#").append(caseNum).append("\n");


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(arr90[i][j]);
                }
                sb.append(" ");
                for (int j = 0; j < N; j++) {
                    sb.append(arr180[i][j]);
                }
                sb.append(" ");
                for (int j = 0; j < N; j++) {
                    sb.append(arr270[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}