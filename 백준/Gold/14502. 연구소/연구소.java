import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int finalResult;
    static int N, M;
    static int[][] arr;
    static int[][] tempArr;
    static int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static public class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tempArr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = arr[i].clone();
        }

        finalResult = 0;
        dfs(3);


        System.out.println(finalResult);


    }

    private static void dfs( int flr) {
        if (flr == 0) {
            int result = goVirus();
            finalResult = Math.max(finalResult, result);
            return;
        }

        /**
         * 실수한 부분 : 가벽 설치하고 그다음 가벽은 그 뒤에 설치하기 위해 dfs에 매개변수로 이전 가벽설치 위치 넘겼으나
         * 이중반복문의 시작점을 이전 x,y로 하면 다음 가벽의 위치가 아랫줄일 때 y가 0부터 도는게 아니라 이전 y부터 시작함
         * 애초에 사실 필요 없었을 것 (반복문이 뒤로 계속 돌아가므로 앞의 위치에 설치될 일 없음)
         */
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (arr[x][y]==0) {
                    arr[x][y] = 3;  //가벽 설치
                    dfs(flr-1);
                    arr[x][y] = 0;
                }
            }
        }

    }

    private static int goVirus() {
        //tempArr에 arr복사
        tempArr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = arr[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]==2) { //바이러스 찾음
                    bfs(i, j);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tempArr[i][j]==0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int startX, int startY) {
        boolean[][] visit = new boolean[N][M];
        Queue<Node> myQ = new LinkedList<>();
        myQ.offer(new Node(startX, startY));

        while (!myQ.isEmpty()) {
            Node node = myQ.poll();
            visit[node.x][node.y] = true;

            for (int[] dir : dirArr) {
                int nextX = node.x + dir[0];
                int nextY = node.y + dir[1];
                if (canGo(nextX, nextY) && !visit[nextX][nextY] && tempArr[nextX][nextY] == 0) {
                    tempArr[nextX][nextY] = 2;
                    myQ.offer(new Node(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int nextX, int nextY) {
        if (0<=nextX && nextX<N && 0<=nextY && nextY<M) {
            return true;
        } else {
            return false;
        }
    }
}
