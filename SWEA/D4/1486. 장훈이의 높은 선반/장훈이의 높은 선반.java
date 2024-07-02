import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int B;
    static int result;
    static int[] arr;
    static public class topStack {
        int lastIdx;
        int height;

        public topStack(int lastIdx, int height) {
            this.lastIdx = lastIdx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());


        for (int caseNum = 1; caseNum <T+1; caseNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }


            result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                dfs(i, arr[i]);
            }


            sb.append("#").append(caseNum).append(" ").append(result-B).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, int sum) {
        if (sum >= B && sum < result) {
            result = sum;
            return;
        }

        for (int i = idx + 1; i < N; i++) {
            dfs(i, sum + arr[i]);
        }
    }
}