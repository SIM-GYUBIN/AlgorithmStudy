import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] grid = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
            }
        }


        int first = logic(N, grid, false);
        int second = logic(N, grid, true);

        System.out.println(first + " " + second);
    }

    private static int logic(int N, char[][] grid, boolean isBlind) {
        int cntArea = 0;
        boolean[][] visit = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visit[r][c]) {
                    dfs(visit, r, c, grid, isBlind);
                    cntArea++;
                }
            }
        }

        return cntArea;
    }

    private static void dfs(boolean[][] visit, int r, int c, char[][] grid, boolean isBlind) {
        int[][] dirArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        visit[r][c] = true;
        for (int[] dir : dirArr) {
            int nR = r + dir[0];
            int nC = c + dir[1];

            if (canGo(nR, nC, grid.length)
                    && !visit[nR][nC]
                    && judgeSameColor(grid[r][c], grid[nR][nC], isBlind)) {
                dfs(visit, nR, nC, grid, isBlind);
            }
        }
    }

    private static boolean judgeSameColor(char before, char after, boolean isBlind) {
        if (Objects.equals(before, after)) {
            return true;
        } else if (isBlind) {
            return (Objects.equals(before, 'R') || Objects.equals(before, 'G'))
                    && (Objects.equals(after, 'R') || Objects.equals(after, 'G'));
        }

        return false;
    }

    private static boolean canGo(int r, int c, int max) {
        return 0 <= r && r < max && 0 <= c && c < max;
    }
}
