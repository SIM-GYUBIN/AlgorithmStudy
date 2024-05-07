import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int[][] arr;
    static int[][] dir = {{0,-1},{0,1},{-1,0}};    //좌, 우
    static int result;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = 10;

        for (int caseNum = 1; caseNum < T+1; caseNum++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[100][100];
            int startX = 0, startY = 0;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if (num == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            dfs(startX, startY, "none");


            sb.append('#').append(caseNum).append(' ').append(result).append('\n');

        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, String status) {
        if(x==0) {
            result = y;
            return;
        }

        /**
         * 좌, 우로 갈 수 있는지 확인 후 있으면 글로 가고(그대로 넘겨야함! 재귀x), 없으면 위로(-1,0)
         */

        int nextX = x;
        int nextY = y;
        if (status.equals("none")) {
            for (int i = 0; i < 3; i++) {
                nextX = x + dir[i][0];
                nextY = y + dir[i][1];
                if(nextY==-1 || nextY==100) {
                    continue;
                }
                if (arr[nextX][nextY] == 1) {
                    if (i == 0) {   //왼쪽 당첨
                        status = "goLeft";
                    } else if (i == 1) {    //오른쪽 당첨
                        status = "goRight";
                    } else if (i == 2) {    //위 당첨
                        status = "none";
                    }
                    break;
                }
            }
        } else if (status.equals("goLeft")) {
            nextX += dir[0][0];
            nextY += dir[0][1];
            if (nextY==-1 || arr[nextX][nextY] != 1) {   //이제 왼쪽이 끝나면!
                nextX = x + dir[2][0]; //nextX와 Y를 다시 위쪽으로 보내
                nextY = y + dir[2][1];
                status = "none";
            }
        } else if (status.equals("goRight")) {
            nextX += dir[1][0];
            nextY += dir[1][1];
            if (nextY==100 || arr[nextX][nextY] != 1) {   //이제 오른쪽이 끝나면!
                nextX = x + dir[2][0];  //nextX와 Y를 다시 위쪽으로 보내
                nextY = y + dir[2][1];
                status = "none";
            }
        }
        dfs(nextX, nextY, status);
    }
}