import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int 지금까지나온최댓값 = 0;
        int 몇번째수 = 0;

        for (int 번 = 1; 번 < 9+1; 번++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int 수 = Integer.parseInt(st.nextToken());
            if (수 > 지금까지나온최댓값) {
                지금까지나온최댓값 = 수;
                몇번째수 = 번;
            }
        }
        System.out.println(지금까지나온최댓값);
        System.out.println(몇번째수);
    }
}