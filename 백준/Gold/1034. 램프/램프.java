import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, RESULT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열


        int[][] inputs = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                inputs[i][j] = line.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());    // 최대


        /**
         *  1. 각 행을 보며 판단
         *      1.1 K가 꺼진 램프보다 많은지
         *      1.2 아니라면 꺼진 램프의 홀짝이 같은지 (다르면 불가)
         *      1.3 같다면 똑같이 생긴 행이 총 몇개인지 (똑같아야만 가능)
         *  2. 판단한 행은 visit 처리해서 시간 개선
         */



        // 완전 탐색 돌리기
        RESULT = 0;
        boolean[] visit = new boolean[N];
        for (int r = 0; r < N; r++) {

            int cntZero = 0;
            for (int clm : inputs[r]) {
                if (clm==0) cntZero++;
            }

            if (!visit[r] && cntZero <= K) {
                visit[r] = true;
                if (isSameEvenOdd(cntZero, K)) {
                    List<Integer> sameRowList = getSameRow(inputs[r], inputs);
                    for (Integer i : sameRowList) {
                        visit[i] = true;
                    }

                    RESULT = Math.max(RESULT, sameRowList.size());
                }
            }
        }

        System.out.println(RESULT);
    }

    private static List<Integer> getSameRow(int[] target, int[][] inputs) {
        List<Integer> sameRowNumberList = new LinkedList<>();
        for (int i = 0; i < inputs.length; i++) {
            if (Arrays.equals(target, inputs[i])) sameRowNumberList.add(i);
        }

        return sameRowNumberList;
    }

    private static boolean isSameEvenOdd(int cntZero, int K) {
        return cntZero % 2 == K % 2;
    }
}

