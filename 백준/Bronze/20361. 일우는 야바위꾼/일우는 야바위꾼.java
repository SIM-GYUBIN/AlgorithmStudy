import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, X, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

//		int T = Integer.parseInt(br.readLine());
//
//		for (int caseNum = 1; caseNum <= T; caseNum++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[N + 1];
        arr[X] = true;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean tmp = arr[A];
            arr[A] = arr[B];
            arr[B] = tmp;
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (arr[i]) {
                result = i;
                break;
            }
        }

        System.out.println(result);
//		}
//		System.out.println(sb);
    }

}
