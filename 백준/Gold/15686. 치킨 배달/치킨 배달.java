import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M, RESULT;
    static int[][] arr;
    static int[][] memArr;
    static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}};

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

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }

        RESULT = Integer.MAX_VALUE;
        dfs(0,0,M);

        System.out.println(RESULT);

    }

    /**
     * bfs해서 초과 났었음
     */

    private static void dfs(int startX, int startY, int flr) {
        if (flr == 0) {
            int cityChickenRoad = calChickenRoad();
            RESULT = Math.min(RESULT, cityChickenRoad);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==startX && j>=startY || i>startX) {
                    if (arr[i][j] == 2) {
                        arr[i][j] = 5;  //5는 운영하는 치킨집
                        dfs(i, j, flr - 1);
                        arr[i][j] = 2;
                    }
                }
            }
        }
    }

    private static int calChickenRoad() {
        int sum = 0;
        memArr = new int[N][N];
        for (int[] a : memArr) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j]==5) { //치킨집 찾으면
//                    sum += bfs(i,j);
                    calDistance(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j]==1) {
                    sum+= memArr[i][j];
                }
            }
        }
        return sum;
    }

    private static void calDistance(int chickX, int chickY) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (arr[x][y] == 1) {
                    int distance = Math.abs(chickX - x) + Math.abs(chickY - y);
                    memArr[x][y] = Math.min(memArr[x][y], distance);
                }
            }
        }
    }


//    private static int bfs(int startX, int startY) {
//        Queue<Node> myQ = new LinkedList<>();
//        myQ.offer(new Node(startX, startY));
//
//        boolean[][] visit = new boolean[N][N];
//
//        while (!myQ.isEmpty()) {
//            Node node = myQ.poll();
//            visit[node.x][node.y] = true;
//
//            if (arr[node.x][node.y] == 5) { //가까운 치킨집 발견
//                int distance = Math.abs(startX - node.x) + Math.abs(startY - node.y);
//                return distance;
//            }
//
//            for (int[] dir : dirArr) {
//                int nextX = node.x + dir[0];
//                int nextY = node.y + dir[1];
//
//                if (canGo(nextX,nextY) && !visit[nextX][nextY]) {
//                    myQ.offer(new Node(nextX, nextY));
//                }
//            }
//        }
//        return -1;  //문제상 치킨집은 무조건 1개 이상 존재함. 걸릴일 없음.
//    }

    private static boolean canGo(int x, int y) {
        if (0<=x && x<N && 0<=y && y<N) {
            return true;
        } else {
            return false;
        }
    }


}
