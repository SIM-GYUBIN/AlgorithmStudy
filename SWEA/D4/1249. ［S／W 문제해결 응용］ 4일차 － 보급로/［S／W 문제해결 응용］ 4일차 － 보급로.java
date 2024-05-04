import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};

    public static void main(String args[]) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        int T;
//        T = sc.nextInt();
//
//        for (int test_case = 1; test_case <= T; test_case++) {
//
//            int size = sc.nextInt();
//            sc.nextLine();
//            int[][] arr = new int[size][size];
//            for (int i = 0; i < size; i++) {
//                String line = sc.nextLine();
//                for (int j = 0; j < size; j++) {
//                    arr[i][j] = line.charAt(j)-'0';
//                }
//            }
//          }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int caseNum = 0; caseNum < T; caseNum++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            int[][] record = new int[N][N];

            for (int[] a: record) {
                Arrays.fill(a, Integer.MAX_VALUE);
            }
            record[0][0] = 0;

            for (int i = 0; i < N; i++) {
                String string = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = string.charAt(j) - '0';
                }
            }
            /**
             * logic
             * arr => 문제의 지도
             * record => 그 위치까지의 최단거리값
             */
            BFS(arr, record);
            System.out.println("#"+(caseNum+1)+" "+record[N-1][N-1]);
        }
    }

    private static void BFS(int[][] arr, int[][] record) {
        boolean[][] visited = new boolean[arr.length][arr.length];
        Queue<int[]> myQ = new LinkedList<>();
        myQ.offer(new int[]{0, 0});

        while(!myQ.isEmpty()) {
            int[] now = myQ.poll();
            int x = now[0];
            int y = now[1];

            if (visited[x][y])
                continue;
            visited[now[0]][now[1]] = true;   //이미 방문했다는 표시

            for (int i = 0; i < 4; i++) {
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];

                if ((nextX<0 || nextX>=arr.length) || (nextY<0 || nextY>= arr.length)) { //skip대상
                    continue;
                }
                int calNextNum = arr[nextX][nextY] + record[x][y];   //현재 위치에서 다음 도로까지의 합
                int shortNum = record[nextX][nextY];    //다음 도로까지 최단값
                record[nextX][nextY] = Math.min(calNextNum, shortNum);

                if (calNextNum < shortNum) {
                    record[nextX][nextY] = calNextNum;
                    visited[nextX][nextY] = false;
                    myQ.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}