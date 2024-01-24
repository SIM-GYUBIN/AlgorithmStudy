import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int LINE = Integer.parseInt(st.nextToken());
        int qTime = Integer.parseInt(st.nextToken());

        LINE += 1;

        int[][] arr = new int[LINE][LINE];


//        배열 입력받기
        for (int i = 1; i < LINE; i++) {
            st = new StringTokenizer(br.readLine());

            int lineSum = 0;

            for (int j = 1; j < LINE; j++) {
                int n = Integer.parseInt(st.nextToken());
                lineSum = lineSum + n;
                arr[i][j] = lineSum;
            }
        }

//        문제좌표 입력받기
        for (int i = 0; i < qTime; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            //        해결하기
            int first =  arr[x1][y2] - arr[x1][y1-1];
            int second =  arr[x2][y2] - arr[x2][y1-1];

            int sum = 0;
            for (int x = x1; x < x2 + 1; x++) {
                sum +=  arr[x][y2] - arr[x][y1-1];
            }
            sb.append(sum + "\n");
//            System.out.println(sum);
        }
        System.out.println(sb);
    }
}
