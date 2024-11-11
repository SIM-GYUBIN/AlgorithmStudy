import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 *
 * 백트래킹하며 이동가능한 최대 칸 수 기록
 * 기록한 곳들은 방문x, 초기방문도 x
 *
 */
public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] cntArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        cntArr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int RESULT = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    int rtn = dfs(r, c);
                    RESULT = Math.max(RESULT, rtn);
                }
            }
        }
        System.out.println(RESULT);
    }

    private static int dfs(int r, int c) {

        visited[r][c] = true;
        if (isEnd(r,c)) {
            cntArr[r][c] = 1;
            return 1;
        }

        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int maxReturn = 0;
        for (int[] dir : dirArr) {
            int nR = r + dir[0];
            int nC = c + dir[1];

            if (!canGo(nR, nC) || map[r][c] >= map[nR][nC]) {
                continue;
            }

            int num = 0;
            if (!visited[nR][nC]) {
                num = dfs(nR, nC) + 1;
            } else if (visited[nR][nC]){
                num = cntArr[nR][nC] + 1;
            }

            maxReturn = Math.max(maxReturn, num);
        }

        cntArr[r][c] = maxReturn;

        return maxReturn;
    }

    private static boolean isEnd(int r, int c) {
        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        boolean isEnd = true;
        for (int[] dir : dirArr) {
            int nR = r + dir[0];
            int nC = c + dir[1];
            if (canGo(nR, nC) && map[r][c] < map[nR][nC]) {
                isEnd = false;
                break;
            }
        }
        return isEnd;
    }

    private static boolean canGo(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}