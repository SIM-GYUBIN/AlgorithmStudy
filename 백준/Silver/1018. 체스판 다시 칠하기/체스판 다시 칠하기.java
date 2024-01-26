import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = (input.charAt(j) == 'B') ? 1 : 0;
            }
        }

        int minResult = Integer.MAX_VALUE;

        boolean isContinue = true;
        int posX = 0;
        int posY = 0;

        //진행조건 두개가 일치할 경우
        /**
         * t, t => f
         * f, t => t
         * t, f => t
         * t, t => t
         */
        while (isContinue) {
            //첫 칸 black or white
            for (int marker = 0; marker < 2; marker++) {
                int cnt = 0;    //수정할 칸 카운트
                int check = marker; //check : 첫 칸 초기값


                //칸 전부 돌기
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (arr[posY + i][posX + j] != check) cnt++;


                        //x축 마지막 칸일땐 그대로 가
                        if (j != 7) check = check ^ 1;
                    }
                }

                //값 비교
                if (cnt < minResult) {
                    minResult = cnt;
                }
            }

            //끝 판단
            if ((posX + 8 == M) && (posY + 8 == N)) isContinue = false;

            //다음 인덱스
            if (posX + 8 == M) {
                posX = 0;
                posY++;
            } else
                posX++;

        }


        System.out.println(minResult);
    }
}