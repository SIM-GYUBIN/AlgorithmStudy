import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        /**
         *  배열을 오름차순으로 정렬한 뒤
         *  누적합을 저장한 뒤
         *  그 누적합을 다 더함
         */

        Arrays.sort(arr);

        int[] dp = new int[N];

        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i-1] + arr[i];
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += dp[i];
        }

        System.out.println(sum);
    }
}
