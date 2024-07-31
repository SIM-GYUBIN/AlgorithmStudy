import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] box;
    static boolean[][] visit;
    static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}};

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());   //상자 가로 칸
        N = Integer.parseInt(st.nextToken());   //상자 세로 칸

        box = new int[N][M];
        visit = new boolean[N][M];

        Queue<Node> myQ = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                box[i][j] = num;
                if (num == 1) {
                    myQ.offer(new Node(i, j));
                }
            }
        }

        /**
         * 1: 익은 토마토, 0: 안익은 토마토, -1: 없는 칸
         *
         */

        while (!myQ.isEmpty()) {
            Node nowNode = myQ.poll();
            int nowR = nowNode.r;
            int nowC = nowNode.c;

            for (int[] dir : dirArr) {
                int nextR = nowR + dir[0];
                int nextC = nowC + dir[1];

                if (canGo(nextR, nextC) && !visit[nextR][nextC]
                        && box[nextR][nextC] != -1 && box[nextR][nextC] != 1) {
                    box[nextR][nextC] = box[nowR][nowC] + 1;
                    visit[nextR][nextC] = true;
                    myQ.offer(new Node(nextR, nextC));
                }
            }
        }

        //결과 검사
        int maxNum = 0;
        boolean failFlag = false;

        inspect:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {   //실패
                    failFlag = true;
                    break inspect;
                } else {
                    maxNum = Math.max(maxNum, box[i][j]);
                }
            }
        }
        System.out.println(failFlag ? -1 : maxNum - 1); //1빼는 이유 : box에 1부터 시작해서 넣었으니까 1 더 커

    }

    private static boolean canGo(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}