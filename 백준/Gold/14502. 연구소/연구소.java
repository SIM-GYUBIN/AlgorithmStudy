import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, maxSafeArea, zeroCnt;
    static int[][] ogArr;
    static int[][] arr;
    static List<Node> virusLoc;

    public static class Node {
        int r,c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ogArr = new int[N][M];
        arr = new int[N][M];
        virusLoc = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                ogArr[i][j] = num;
                if (num==2)
                    virusLoc.add(new Node(i, j));
                else if (num == 0) {
                    zeroCnt++;
                }
            }
        }


        maxSafeArea = 0;

        //가벽 세우기
        dfsWall(0,3);

        System.out.println(maxSafeArea);
    }

    private static void dfsWall(int num, int flr) {
        if (flr == 0) {
            bfsVirus();
            return;
        }

        for (int next = num; next < N * M; next++) {
            int r = next / M; //행
            int c = next % M; //열

            if (canGo(r, c) && ogArr[r][c]==0) {
                ogArr[r][c] = 3;
                dfsWall(next, flr-1);
                ogArr[r][c] = 0;
            }
        }
    }

    private static void bfsVirus() {
        int plusVirus = 3;  //가벽 위치 빼야하니 그냥 여기에
        for (int r = 0; r < N; r++) {
            arr[r] = ogArr[r].clone();
        }

        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> myQ = new ArrayDeque<>();
        for (Node node : virusLoc) {
            myQ.offer(node);
        }

        while (!myQ.isEmpty()) {
            Node now = myQ.poll();

            if (zeroCnt - maxSafeArea <= plusVirus) {
                return;
            }

            for (int[] dir : dirArr) {
                int nextR = now.r + dir[0];
                int nextC = now.c + dir[1];

                if (canGo(nextR, nextC) && arr[nextR][nextC] == 0) {
                    arr[nextR][nextC] = 2;
                    plusVirus++;
                    myQ.offer(new Node(nextR, nextC));
                }
            }
        }

        maxSafeArea = Math.max(maxSafeArea, zeroCnt - plusVirus);
    }

    private static boolean canGo(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
