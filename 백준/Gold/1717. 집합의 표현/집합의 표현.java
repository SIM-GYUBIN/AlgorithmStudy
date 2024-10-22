import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 최적화1
 * - 경로 압축
 */

class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 0) {
                // union
                union(a, b);

            } else if (order == 1) {
                boolean isIn = false;
                if (find(a) == find(b)) isIn = true;

                sb.append(isIn ? "YES" : "NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * 
     * 최적화1
     * - 경로 압축
     */

    public static int find(int x) {
        if (parent[x] == x)
            return x; // 현재 노드의 부모가 존재하지 않는경우(현재 노드가 루트노드) 자기 자신 반환
        return parent[x] = find(parent[x]); // 현재 노드의 부모 노드가 존재한다면 재귀를 통해 루트노드를 찾아 반환
    }

    // 부모 노드(루트 노드) 가 같다면 두 노드의 부모 노드를 같게 만든다.
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return; // x와 y의 부모 노드가 같다면 두 노드는 연결되어 있다.

        // 부모 노드와 같게 만드는 union 작업
        if (x!=y)
            parent[x] = y;
    }
}