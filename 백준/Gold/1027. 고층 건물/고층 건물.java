import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 기울기 구하고 선 그어서 닿는지 대보면서 하다가 부동소수점 문제에 대였음
         * 그냥 기울기로만 비교했으면 BigDecimal도 안써도 되는 문제였는듯
         */
        
        int result = 0;
        for (int aIdx = 1; aIdx <= N; aIdx++) {
            int cnt = 0;
            for (int bIdx = 1; bIdx <= N; bIdx++) {
                if(aIdx!=bIdx) {

                    BigDecimal deltaX = new BigDecimal(aIdx - bIdx);
                    BigDecimal deltaY = new BigDecimal(arr[aIdx] - arr[bIdx]);
                    BigDecimal tilt = deltaY.divide(deltaX, 10, BigDecimal.ROUND_HALF_UP);
                    boolean flag = true;
                    if (aIdx > bIdx) {
                        for (int i = bIdx+1; i < aIdx; i++) {
                            BigDecimal preDeltaX = new BigDecimal(aIdx - i);
                            BigDecimal preDeltaY = new BigDecimal(arr[aIdx] - arr[i]);
                            BigDecimal preTilt = preDeltaY.divide(preDeltaX, 10, BigDecimal.ROUND_HALF_UP);

                            if (tilt.compareTo(preTilt) >= 0) {
                                flag = false;
                                break;
                            }
                        }
                    } else if (aIdx < bIdx){
                        for (int i = bIdx-1; i > aIdx; i--) {
                            BigDecimal preDeltaX = new BigDecimal(aIdx - i);
                            BigDecimal preDeltaY = new BigDecimal(arr[aIdx] - arr[i]);
                            BigDecimal preTilt = preDeltaY.divide(preDeltaX, 10, BigDecimal.ROUND_HALF_UP);

                            if (tilt.compareTo(preTilt) <= 0) {
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
                        cnt++;
                    }
                }
            }
            if (result < cnt) {
                result = cnt;
            }
        }

//        System.out.println(who);
        System.out.println(result);

    }
}