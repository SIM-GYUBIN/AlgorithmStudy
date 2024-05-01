import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int[][] arr;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int NUM;
    static int dirNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            NUM = Integer.parseInt(br.readLine());
            sb.append("#").append(i+1).append("\n");

            arr = new int[NUM][NUM];
            dirNum = 0;
            dfs(0,0,1);

            for (int[] printArr: arr) {
                for (int j = 0; j < NUM; j++) {
                    sb.append(printArr[j]).append(" ");
                }
                sb.append("\n");
            }

        }

        System.out.println(sb);
    }

    private static void dfs(int x, int y, int num) {
        if (num == NUM * NUM+1) {
            return;
        }

        arr[x][y] = num;


        int nextX = x + direction[dirNum][0];
        int nextY = y + direction[dirNum][1];

        if (nextX==NUM || nextY==NUM || nextX<0 || nextY<0 || arr[nextX][nextY] != 0) {
            dirNum = dirNum==3 ? 0 : dirNum+1;
            nextX = x + direction[dirNum][0];
            nextY = y + direction[dirNum][1];
        }

        dfs(nextX, nextY, num+1);
    }
}