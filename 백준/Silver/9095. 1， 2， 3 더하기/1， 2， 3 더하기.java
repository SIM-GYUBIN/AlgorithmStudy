import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            result = 0;
            calculate(N);
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    private static void calculate(int N) {
        if (N == 0) {
            result++;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (N - i >= 0) {   // 요거 없으면 스택오버플로우
                calculate(N - i);
            }
        }
    }
}
