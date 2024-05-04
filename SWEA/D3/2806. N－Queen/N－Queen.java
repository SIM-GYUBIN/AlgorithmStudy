import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int[] board;
    static int N;
    static int result;
    
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            result = 0;
            N = Integer.parseInt(br.readLine());

            board = new int[N];

            dfs(0);
            sb.append("#").append(caseNum).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x) {
        if (x == N) {
            result++;
            return;
        }

        loop_row:
        for (int y = 0; y < N; y++) {
            board[x] = y;   //2차원배열같은 1차원 배열 (이전의 퀸과 놓고 싶은 자리의 퀸의 비교를 용이하게 하기 위해)
            for (int beforeX = 0; beforeX < x; beforeX++) { //이전 행들의 퀸 위치와 비교
                int beforeY = board[beforeX];
                if (beforeY==y) {   //같은 행
                    board[x] = 0;
                    if(y==N-1)
                        return;
                    continue loop_row;
                }
                if (Math.abs(x-beforeX) == Math.abs(y - beforeY)) { //열의 차와 행의 차가 같은 경우 대각선 (절대값 필요)
                    board[x] = 0;
                    if(y==N-1)
                        return;
                    continue loop_row;
                }
            }
            //통과된 경우
            dfs(x+1);
        }
    }
}