import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[1_000_001];
//        1_000_001
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * NlogN lis (길이만 구하는 방법)
         * 1. 큰거는 lis의 맨 뒤에 삽입
         * 2. 작은 것은 이분탐색하여 바꿔 넣기
         * (-> lis를 만드려면 idx저장하는 배열 만들어서 구하기, 여기서는 x)
         */

        lis[0] = arr[0];
        int ep = 0; //lis배열의 마지막 원소를 가리킴
        for (int i = 1; i < N; i++) {   // arr을 순회
            if (arr[i] > lis[ep]) {
                lis[++ep] = arr[i];
            } else if (arr[i] < lis[ep]) {
                int idx = binarysearch(0, ep, arr[i], lis);
                lis[idx] = arr[i];
            }
        }

        System.out.println(ep+1);
    }

    private static int binarysearch(int s, int e, int target, int[] lis) {
        while (s < e) {
            int mid = (s+e)/2;
            if (lis[mid] == target) {
                return mid;
            } else if (lis[mid] < target) {
                s = mid+1;
            } else if (lis[mid] > target) {
                e = mid;
                /**
                 * mid에서 -1을 하지 않더라도 mid는 '/'연산에 의해 내림임
                 * -1하면 s:0, e:1일 때 mid가 -1이 되어버려 exception발생
                 */
            }
        }
        return e;   // 찾는 원소가 없을 경우 : '/'연산은 내림하니까 e를 보내야 큰 부분을 바꿀 수 있음
    }
}
