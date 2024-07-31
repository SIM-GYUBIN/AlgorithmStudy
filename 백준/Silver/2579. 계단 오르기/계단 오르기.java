import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N];
        int[] memo = new int[N];


        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (N==1) {
            memo[0] = stairs[0];
        } else {
            memo[0] = stairs[0];
            memo[1] = stairs[0] + stairs[1];

            //틀려서 추가한 코드 : 시작부터 두칸 뛰어오르기
            if (N > 2) {
                memo[2] = stairs[2] + Math.max(stairs[0], stairs[1]);
            }

            /**
             * 선택지
             * 1) 2칸 전 것을 가져와 더하기
             * 2) 직전 칸 것을 가져와 더하기 (직전칸이 연속이면 불가, 더했으면 직전칸 표기)
             */
            for (int i = 3; i < N; i++) {
                int can2before = memo[i - 2];
                int can1before = memo[i-3] + stairs[i-1];

                memo[i] = Math.max(can2before, can1before) + stairs[i];
            }
        }

        System.out.println(memo[N-1]);
    }
}