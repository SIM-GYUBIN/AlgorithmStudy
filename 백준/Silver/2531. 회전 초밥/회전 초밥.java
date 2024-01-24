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

        int DISH = Integer.parseInt(st.nextToken());
        int VARIABLE = Integer.parseInt(st.nextToken());
        int CONTINUITY = Integer.parseInt(st.nextToken());
        int COUPON = Integer.parseInt(st.nextToken());

        int[] sushi = new int[DISH];
        for (int cases = 0; cases < DISH; cases++) {
            st = new StringTokenizer(br.readLine());
            sushi[cases] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int sp = 0;
        int ep = sp + CONTINUITY;

        //sp가 전체를 도는 loop
        for (int i = 0; i < DISH; i++) {
            int[] meal = new int[VARIABLE+1];

            int dishVal = 0;
            int cnt = 0;
            int temp = sp;
            //세팅된 초밥들 순회
//            for (int j = sp; j < ep; j++) {
            while (cnt != CONTINUITY) {
                //초밥이 겹치지않는다면
                if (meal[sushi[temp]] != sushi[temp]) {
//                    meal배열에 넣기
                    meal[sushi[temp]] = sushi[temp];
                    dishVal++;
                }
                cnt++;
                temp++;
                if (temp == DISH)
                    temp = 0;
            }

            //쿠폰 레일에 있는지 확인
            if (meal[COUPON] != COUPON) {
                dishVal++;
            }

            //찾게된 경우가 클 경우 교체
            if (dishVal > result) {
                result = dishVal;
            }

            sp++;
            if (ep == (DISH - 1)) {
                ep = 0;
            } else {
                ep++;
            }
        }


//        sb.append(sum).append("\n");

        System.out.println(result);
    }
}
