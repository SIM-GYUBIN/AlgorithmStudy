import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int size;
    static int cnt;
    static char[][] arr;
    static boolean[][] visit;
    static int[][] dirArr = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static public class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     *
     * 0을 클릭해서 퍼진 경우만 센 뒤, 클릭했을 때 0이 아닌 '.'을 탐색해야함
     * -> 0이 아닌 '.' 을 먼저 탐색하면, 0으로 인해 발견됐을 수 도 있는애를 카운트에 포함시킬 수도 있음
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum <T+1; caseNum++) {
            size = Integer.parseInt(br.readLine());
            arr = new char[size][size];
            visit = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr[i] = st.nextToken().toCharArray();
            }


            // '.' 중에서 0인 칸을 탐색 & 방문처리
            cnt = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!visit[i][j] && arr[i][j]=='.') {
                        bfs(i,j);
                    }
                }
            }

            // 0이 아닌 '.' 을 카운트
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!visit[i][j] && arr[i][j]=='.') {
                        cnt++;
                    }
                }
            }

            sb.append("#").append(caseNum).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }


    private static void bfs(int x, int y) {
        Queue<Node> myQ = new LinkedList<>();
        myQ.offer(new Node (x, y));

        boolean goCnt = true;
        while (!myQ.isEmpty()) {
            Node pos = myQ.poll();
            int nowX = pos.x;
            int nowY = pos.y;

            boolean isZero = judgeZero(nowX, nowY);

            if (isZero) {   //0이라면
                if(goCnt) { //첫 클릭으로 인해 파생된 탐색은 카운트하지 않음
                    cnt++;
                    goCnt = false;
                }
                visit[nowX][nowY] = true;
                for (int[] dir : dirArr) {
                    int nextX = nowX + dir[0];
                    int nextY = nowY + dir[1];
                    if (isInArray(nextX, nextY) && !visit[nextX][nextY]) {
                        myQ.offer(new Node(nextX, nextY));
                        visit[nextX][nextY] = true;
                    }
                }
            }
        }
    }

    private static boolean judgeZero(int nowX, int nowY) {
        for (int[] dir : dirArr) {
            int nextX = nowX + dir[0];
            int nextY = nowY + dir[1];
            if (isInArray(nextX, nextY)) {
                if (arr[nextX][nextY] == '*') { //근처에 지뢰가 하나라도 있으면
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInArray(int x, int y) {
        return 0 <= x && x < size && 0 <= y && y < size;
    }
}