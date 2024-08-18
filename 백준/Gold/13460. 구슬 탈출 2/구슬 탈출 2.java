import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Try {
        private char[][] arr;
        private int turn;

        public Try(char[][] arr, int turn) {
            this.arr = arr;
            this.turn = turn;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j);
            }
        }


        /**
         * logic
         */

        Queue<Try> myQ = new ArrayDeque<>();
        int result = -1;
        myQ.offer(new Try(arr, 1));

        mainLoop:
        while (!myQ.isEmpty()) {
            //레드 블루 골인 여부  (동시에 들어간거 실패처리하기 위해 break 쓸 수 없어서 만듦)


            Try poll = myQ.poll();
            char[][] polledArr = poll.arr;
            int nowTurn = poll.turn;

            if (nowTurn > 10) {
                result = -1;
                break;
            }

            int[] redLoc = new int[2];
            int[] blueLoc = new int[2];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (polledArr[r][c] == 'R') {
                        redLoc[0] = r;
                        redLoc[1] = c;
                    } else if (polledArr[r][c] == 'B') {
                        blueLoc[0] = r;
                        blueLoc[1] = c;
                    }
                }
            }


            //움직인 뒤 q에 넣을 것
            delta:
            for (int[] dir : dirArr) {
                //배열 복사
                char[][] tmpArr = new char[N][M];
                for (int i = 0; i < N; i++) {
                    tmpArr[i] = polledArr[i].clone();
                }

                //상하좌우 굴리기

                boolean isRedMove = true;
                boolean isBlueMove = true;
                boolean redGoal = false;
                boolean blueGoal = false;

                int redCnt = 0;
                int blueCnt = 0;

                int rR = redLoc[0];
                int rC = redLoc[1];
                int bR = blueLoc[0];
                int bC = blueLoc[1];
                while (isRedMove || isBlueMove) {
                    int nrR = rR + dir[0];  //다음 레드 공 좌표
                    int nrC = rC + dir[1];
                    int nbR = bR + dir[0];  //다음 파란 공 좌표
                    int nbC = bC + dir[1];


                    /**
                     * 1. '.' 인지 확인하고 가
                     * 2. 잘 바꿔주기
                     */

                    if (!redGoal) {
                        if (tmpArr[nrR][nrC] == 'O') {  //성공
                            isRedMove = false;
                            redGoal = true;
                            tmpArr[rR][rC] = '.';   //없어져 줘야 뒤에 파란공이 옴
                        } else if (tmpArr[nrR][nrC] == '.') {
                            isRedMove = true;
                            redCnt++;
                            tmpArr[nrR][nrC] = 'R';
                            tmpArr[rR][rC] = '.';
                            rR = nrR;
                            rC = nrC;
                        } else {
                            isRedMove = false;
                        }
                    }

                    if (!blueGoal) {
                        if (tmpArr[nbR][nbC] == 'O') {  //실패
                            isBlueMove = false;
                            blueGoal = true;
                        } else if (tmpArr[nbR][nbC] == '.') {
                            isBlueMove = true;
                            blueCnt++;
                            tmpArr[nbR][nbC] = 'B';
                            tmpArr[bR][bC] = '.';
                            bR = nbR;
                            bC = nbC;
                        } else {
                            isBlueMove = false;
                        }
                    }

                    /**
                     * 성공, 실패 처리
                     * 1. 레드만 골일 때
                     * 2. 블루만 골일 때, 레드 블루 둘다 골일 때
                     * 3. 둘 다 골 아닐 때
                     */
                    if ((!redGoal && blueGoal) || (redGoal && blueGoal)) {   //실패 (아예 끝내자)
                        continue delta;
                    } else if ((redGoal && !blueGoal) && !isBlueMove) { //성공
                        result = nowTurn;
                        break mainLoop;
                    }
                }

                if (redCnt != 0 || blueCnt != 0) {
                    myQ.offer(new Try(tmpArr, nowTurn + 1));
                }
            }
        }
        System.out.println(result);
    }
}