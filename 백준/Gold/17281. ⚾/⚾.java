import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, maxScore;
    static int[][] arr;
    static int[] idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    //이닝

        arr = new int[N][10];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 1번 선수는 4번타자로 낙점
         * 나머지 선수롤 타순 구해서, 최대 몇점 얻을 수 있는지
         */

        //경우의 수
        // 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 = 40320
        // 40320 * 50 = 2016000

        idx = new int[10];
        idx[4] = 1; //1번 선수는 4번타자로 낙점
        boolean[] visit = new boolean[10];
        visit[1] = true;
        dfsMakeIdx(1, 9, visit);

        System.out.println(maxScore);

    }

    private static void dfsMakeIdx(int beon, int flr, boolean[] visit) {
        if (flr == 0) {
            if (idx[1]==2 && idx[2]==3 && idx[3]==4
                    && idx[4]==1 && idx[5]==6 && idx[6]==9
                    && idx[7]==7 && idx[8]==5 && idx[9]==8){
                int hey = 0;
            }

            int score = game(idx);
//            maxScore = Math.max(maxScore, score);
            if (maxScore < score) {
                maxScore = score;
            }
            return;
        }

        if (beon == 4) {
            dfsMakeIdx(beon + 1, flr - 1, visit);
        } else {
            for (int j = 2; j <= 9; j++) {  //j번선수가 i번 타자가 돼
                if (!visit[j]) {
                    visit[j] = true;
                    idx[beon] = j;
                    dfsMakeIdx(beon + 1, flr - 1, visit);
                    visit[j] = false;
                }
            }
        }
    }
    //arr[이닝][몇번선수인지]

    private static int game(int[] idx) {
        int sunseo = 1;
        int score = 0;
        for (int i = 0; i < N; i++) {  //각 이닝
            int out = 0;
            boolean[] ru = new boolean[4]; //1루, 2루, 3루
            while (out < 3) {
                int hit = arr[i][idx[sunseo]];   //각 타자의 결과
                if (hit == 0) {
                    out++;
                } else if (hit==4) {
                    score++;
                    for (int j = 1; j <= 3; j++) {
                        if (ru[j]) {
                            ru[j] = false;
                            score++;
                        }
                    }
                } else {    //모든 주자가 hit만큼 진루
                    for (int j = 3; j >= 1; j--) {
                        if (ru[j]) {    //주자가 있으면
                            ru[j] = false;  //있던 루 비우고
                            if (j + hit > 3) { //다음 루가 3 넘으면 스코어 올리고
                                score++;
                            } else { //아니면 다음 루 채운다
                                ru[j+hit] = true;
                            }
                        }
                    }
                    ru[hit] = true; //타자를 내보냄
                }

                //init
                sunseo++;
                if(sunseo==10) sunseo=1;
            }
        }
        return score;
    }

}