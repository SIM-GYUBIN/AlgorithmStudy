import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, RESULT;
    static int[][] arr;
    static boolean[][] visit;
    static int[][] dirArr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 달팽이 회전
        visit[0][0] = true;
        tornado(0,0,0);

        System.out.println(RESULT);
    }

    private static void tornado(int nowR, int nowC, int dir) {
        if (nowR == N / 2 && nowC == N / 2) {
            return;
        }

        int nR = nowR + dirArr[dir][0];
        int nC = nowC + dirArr[dir][1];
        int ndir = dir;
        if (!canGo(nR, nC) || visit[nR][nC]) {
            ndir = ++ndir==4 ? 0 : ndir;
            nR = nowR + dirArr[ndir][0];
            nC = nowC + dirArr[ndir][1];
        }
        visit[nR][nC] = true;
        tornado(nR, nC, ndir);

//        System.out.println(nowR+" "+ nowC+" "+ ndir);
        if (arr[nowR][nowC] > 0) {
            blowSand(nowR, nowC, ndir);
        }
    }

    private static void blowSand(int r, int c, int dir) {
        int[][] sandMap = new int[5][5];
        sandMap[2][0] = 5;
        sandMap[2][1] = -1; // a
        sandMap[0][2] = sandMap[4][2] = 2;
        sandMap[1][1] = sandMap[3][1] = 10;
        sandMap[1][2] = sandMap[3][2] = 7;
        sandMap[1][3] = sandMap[3][3] = 1;

        if (dir==0) {   //  left
        } else if (dir==1) {    //  up
            sandMap = turnRight(sandMap);
        } else if (dir==2) {    //  right
            sandMap = turnRight(turnRight(sandMap));
        } else if (dir==3) {    //  down
            sandMap = turnRight(turnRight(turnRight(sandMap)));
        }

        blow(r,c,sandMap);
    }

    private static void blow(int nowR, int nowC, int[][] sandMap) {
        int sandMnt = arr[nowR][nowC];
        arr[nowR][nowC] = 0;
        int a = sandMnt;

        int aR =0, aC = 0;
        // 퍼센트
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                int nR = nowR + r - 2; //sandmap을 중앙점 기준 거리가 아닌, 그냥 배열을 넣었으므로 -2 필요
                int nC = nowC + c - 2;

                if (sandMap[r][c] > 0) {
                    int blowingSand = sandMnt * sandMap[r][c] / 100;
                    if (canGo(nR, nC)) {
                        arr[nR][nC] += blowingSand;
                    } else {
                        RESULT += blowingSand;
                    }
                    a -= blowingSand;
                } else if (sandMap[r][c] == -1){
                    aR = r;
                    aC = c;
                }
            }
        }

        // 알파
        int nR = nowR + aR - 2;
        int nC = nowC + aC - 2;
        if (canGo(nR, nC)) {
            arr[nR][nC] += a;
        } else {
            RESULT += a;
        }
    }

    private static int[][] turnRight(int[][] map) {
        int[][] rstMap = new int[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                rstMap[c][5-1-r] = map[r][c];
            }
        }

        return rstMap;
    }

    private static boolean canGo(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }
}