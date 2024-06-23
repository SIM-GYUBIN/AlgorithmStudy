import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static public class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] arr;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //세로
        M = Integer.parseInt(st.nextToken());   //가로

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 외부공기 bfs로 -1로 만들고
         * 외부공기 두면에 접촉하지 않는 치즈만 골라서 tempArr에 적고
         * arr을 tempArr에 다시 옮김
         */

        int[][] endArr = new int[N][M];
        for (int[] a: endArr) {
            Arrays.fill(a, 0);
        }

        int turn = 0;
        while (!Arrays.deepEquals(arr, endArr)) {
            //외부공기 -1 만들기
            Queue<Node> myQ = new LinkedList<>();
            boolean[][] visit = new boolean[N][M];
            myQ.offer(new Node(0, 0));
            visit[0][0] = true;

            while (!myQ.isEmpty()) {
                Node node = myQ.poll();
                int nowX = node.x;
                int nowY = node.y;
                arr[nowX][nowY] = -1;

                for (int[] dirArr : dir) {
                    int nextX = nowX + dirArr[0];
                    int nextY = nowY + dirArr[1];
                    if(canGo(nextX, nextY) && !visit[nextX][nextY]) {
                        visit[nextX][nextY] = true;
                        myQ.add(new Node(nextX, nextY));
                    }
                }
            }

            //내부 치즈 계산
            int[][] tempArr = new int[N][M];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (arr[x][y]==1 && !isMelt(x, y)) {
                        tempArr[x][y] = 1;
                    }
                }
            }
            arr = tempArr;
            turn++;
        }


        System.out.println(turn);




    }

    private static boolean isMelt(int x, int y) {
        int cnt = 0;
        for (int[] dirArr : dir) {
            int nextX = x + dirArr[0];
            int nextY = y + dirArr[1];

            if (0<= nextX && nextX < N && 0<= nextY && nextY < M && arr[nextX][nextY]==-1) {
                cnt++;
            }
        }

        return cnt >= 2;
    }

    private static boolean canGo(int nextX, int nextY) {
        if (0<= nextX && nextX < N && 0<= nextY && nextY < M && arr[nextX][nextY]==0) {
            return true;
        } else {
            return false;
        }
    }

}
