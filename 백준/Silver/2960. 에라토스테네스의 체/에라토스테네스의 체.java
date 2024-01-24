import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int result = 0;

        int[] arr = new int[N+1];
        int arrSize = N+1;
        //2부터 N까지 모든 정수를 적는다.
        for (int i = 2; i < arrSize; i++) {
            arr[i] = i;
        }

        LoopN :
        for (int i = 2; i < arrSize; i++) {
            if (arr[i] == 0)
                continue;
            else {
                //P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
                for (int j = i; j < arrSize; j = j+i) {
                    //이미 지워진 P의 배수는 skip
                    if (arr[j] == 0)
                        continue;
                    cnt++;
                    if(cnt == K) {
                        result = j;
                        break LoopN;
                    }
                    arr[j] = 0;
                }
            }
        }

        System.out.println(result);
    }
}