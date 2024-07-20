import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        boolean[] arr = new boolean[21];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int num = 0;
            if (!(order.equals("all") || order.equals("empty"))) {
                num = Integer.parseInt(st.nextToken());
            }

            //연산
            if (order.equals("add")) {
                arr[num] = true;
            } else if (order.equals("remove")) {
                arr[num] = false;
            } else if (order.equals("check")) {
                sb.append(arr[num] ? 1 : 0).append("\n");
            } else if (order.equals("toggle")) {
                arr[num] = !arr[num];
            } else if (order.equals("all")) {
                Arrays.fill(arr, true);
            } else if (order.equals("empty")) {
                Arrays.fill(arr, false);
            }
        }
        System.out.print(sb);
    }
}
