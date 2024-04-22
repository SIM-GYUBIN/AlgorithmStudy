import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int TEST;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TEST = Integer.parseInt(br.readLine());

        for (int i = 0; i < TEST; i++) {    //각 테스트 케이스

            int N = Integer.parseInt(br.readLine());

            Stack<Integer> products = new Stack<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int parsedInt = Integer.parseInt(st.nextToken());
                products.push(parsedInt);
            }


            long result = 0;    //이익
            long top = products.pop();
            long sum = 0;    //사재기 원료 가격 합 (판매 시 초기화)
            long cnt = 0;    //사재기 원료 개수 합 (판매 시 초기화)

            while (!products.isEmpty()) {
                if (products.peek() > top) {
                    result += top * cnt - sum;
                    top = products.pop();

                    sum = 0;
                    cnt = 0;
                } else {
                    sum += products.pop();
                    cnt++;
                }
            }
            result += top * cnt - sum;
            sb.append("#" + (i + 1) + " " + result + "\n");
        }
        System.out.println(sb);
    }
}