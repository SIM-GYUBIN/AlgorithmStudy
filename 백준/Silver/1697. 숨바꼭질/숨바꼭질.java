import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int MAXINT = 100001;
    static int SUBIN;
    static int SISTER;
    static int[] ROAD = new int[MAXINT];   // 0 : 빈곳, 숫자: 카운트, -1: 동생

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        SUBIN = Integer.parseInt(st.nextToken());
        SISTER = Integer.parseInt(st.nextToken());

        ROAD[SISTER] = -1;
        ROAD[SUBIN] = 1;

        BFS(SUBIN);

        System.out.println(ROAD[SISTER] - 1);
    }

    private static void BFS(int idx) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);

        while (!queue.isEmpty()) {
            int beforeNum = queue.poll();
            int cnt = ROAD[beforeNum];
            int nextIdx = 0;

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    nextIdx = beforeNum - 1;
                } else if (i == 1) {
                    nextIdx = beforeNum + 1;

                } else if (i == 2) {
                    nextIdx = 2 * beforeNum;
                }

                if (nextIdx >= 0 && nextIdx < MAXINT) {
                    if (cnt+1 < ROAD[nextIdx] || ROAD[nextIdx] == 0 || ROAD[nextIdx] == -1) {
                        ROAD[nextIdx] = cnt + 1;
                        queue.offer(nextIdx);
                    }
                }
            }
        }
    }
}