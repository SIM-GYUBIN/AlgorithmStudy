import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // https://www.acmicpc.net/problem/11659

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 데이터의 개수
        int m = Integer.parseInt(st.nextToken());  // 물어볼 개수

        long[] sum = new long[n + 1]; // 인덱스 1부터 쓰기 위해서

        st = new StringTokenizer(br.readLine());    // 새로운 인스턴스 만들어서 두 번째 줄 입력 받음


        for (int i = 1; i < n+1; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }

        for(int a = 0; a < m; a++) {
            st = new StringTokenizer(br.readLine());

            int i  = Integer.parseInt(st.nextToken());
            int j  = Integer.parseInt(st.nextToken());

            System.out.println(sum[j] - sum[i - 1]);

        } // for
    }// main
}