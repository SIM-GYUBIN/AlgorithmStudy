import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int[][] arr;
    static Set<String> box;
    static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {


            arr = new int[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /**
             * logic
             */

            box = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i,j,0, "");
                }
            }

            sb.append('#').append(caseNum).append(' ').append(box.size()).append('\n');

        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int cnt, String str) {
        if (cnt == 7) {
            box.add(str);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x +dir[i][0];
            int nextY = y +dir[i][1];

            if(0<=nextX && nextX<4 && 0<=nextY && nextY<4) { //이상 없으면
                dfs(nextX, nextY, cnt+1, str+arr[x][y]);
            }
        }
    }
}