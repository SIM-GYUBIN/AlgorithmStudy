import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최적화2
 * - 경로 압축
 * - 높이 높은 트리에 union (rank활용)
 */

class Main {
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int turn = 1;
        boolean isCycle = false;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                // 사이클 발견
                isCycle = true;
                break;
            } else {
                //union
                parent[pa] = pb;
            }
            turn++;
        }

        if (isCycle)
            System.out.println(turn);
        else
            System.out.println(0);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}