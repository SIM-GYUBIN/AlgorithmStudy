import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M,N, K;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());

            int aR = M - ay -1;
            int aC = ax;
            int bR = M - by;
            int bC = bx-1;


            for (int r = bR; r <= aR; r++) {
                for (int c = aC; c <= bC; c++) {
                    map[r][c] = true;
                }
            }
        }

        //logic
        int areaCnt = 0;
        List<Integer> sizeLst = new ArrayList<>();
        boolean[][] visit = new boolean[M][N];
        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (!map[r][c] && !visit[r][c]) {
                    areaCnt++;
                    int size = 1;

                    Queue<int[]> myQ = new ArrayDeque<>();
                    myQ.offer(new int[]{r, c});
                    visit[r][c] = true;

                    while (!myQ.isEmpty()) {
                        int[] now = myQ.poll();
                        int nowR = now[0];
                        int nowC = now[1];

                        for (int[] dir : dirArr) {
                            int nR = nowR + dir[0];
                            int nC = nowC + dir[1];
                            if (canGo(nR, nC) && !map[nR][nC] && !visit[nR][nC]) {
                                visit[nR][nC] = true;
                                size++;
                                myQ.offer(new int[]{nR, nC});
                            }
                        }
                    }
                    sizeLst.add(size);
                }
            }
        }

        Collections.sort(sizeLst);
        System.out.println(areaCnt);
        for (Integer i : sizeLst) {
            System.out.print(i+" ");
        }


    }

    private static boolean canGo(int r, int c) {
        return 0 <= r && r < M && 0 <= c && c < N;
    }
}