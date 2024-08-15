import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K,W,H,result;
    static int[][] arr;

    static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}
            ,{-1,-2},{-2,-1},{-2,1},{-1,2}
            ,{1,-2},{2,-1},{2,1},{1,2}};

    static class Node {
        int x;
        int y;
        int kNum;
        int turn;

        public Node(int x, int y, int turn,int kNum) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.kNum = kNum;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());    //말처럼 점프 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }

        result = Integer.MAX_VALUE;
        bfs(0,0);
        System.out.println(result==Integer.MAX_VALUE ? -1 : result);
    }

    private static void bfs(int stX, int stY) {
        int[][] visitK = new int[H][W];
        Queue<Node> myQ = new LinkedList<>();
        myQ.offer(new Node(stX, stY,0 , K));

        for (int[] arr : visitK) {
            Arrays.fill(arr, -1);
        }

        visitK[0][0] = Integer.MAX_VALUE; //0,0 방문 막기

        while (!myQ.isEmpty()) {
            Node node = myQ.poll();
            int nowX = node.x;
            int nowY = node.y;
            int nowK = node.kNum;
            int nowTurn = node.turn;

            if (nowX == H - 1 && nowY == W - 1) {
                result = Math.min(result, nowTurn);
                break;
            }

            for (int i = 0; i < dirArr.length; i++) {
                int nextX = nowX + dirArr[i][0];
                int nextY = nowY + dirArr[i][1];

                if (i>3 && K<=0) break; //말 처럼 점프할 수 없는 경우

                if (canGO(nextX, nextY) && arr[nextX][nextY] == 0) {
                    //말 처럼 움직이는 경우
                    if (i > 3) {
                        if (visitK[nextX][nextY] >= nowK -1) {  //방문하려는 곳이 더 큰 K를 가지고 방문했거나, 같은 K를 가지고 방문했다면
                            continue;
                        }
                        visitK[nextX][nextY] = nowK - 1;
                        myQ.offer(new Node(nextX, nextY, nowTurn + 1,nowK - 1));
                    }
                    //원숭이처럼 움직이는 경우
                    else {
                        if (visitK[nextX][nextY] >= nowK) { //방문하려는 곳이 더 큰 K를 가지고 방문했거나, 같은 K를 가지고 방문했다면
                            continue;
                        }
                        visitK[nextX][nextY] = nowK;
                        myQ.offer(new Node(nextX, nextY,nowTurn + 1, nowK));
                    }
                }
            }
        }
    }

    private static boolean canGO(int x, int y) {
        return 0<=x && x<H && 0<=y && y<W;
    }
}