import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            sb.append("#").append(caseNum).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int[] A = new int[N];

            M = Integer.parseInt(st.nextToken());
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            int result = 0;
            if (N > M) {
                result = solve(A, B);
            } else if (N <= M) {
                result = solve(B, A);
            }

            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve(int[] longArr, int[] shortArr) {

        int maxSum = 0;
        for (int longStartIdx = 0; longStartIdx < longArr.length - shortArr.length + 1; longStartIdx++) {
            int sum = 0;
            int longIdx = longStartIdx;
            for (int shortIdx = 0; shortIdx < shortArr.length; shortIdx++) {
                sum += longArr[longIdx] * shortArr[shortIdx];
                longIdx++;
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

}
