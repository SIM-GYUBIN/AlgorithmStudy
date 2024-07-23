import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, V;
    static List<List<Integer>> list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }


        //dfs
        visited = new boolean[N+1];
        dfs(V);
        System.out.println();
        visited = new boolean[N+1];
        bfs(V);


    }

    private static void bfs(int start) {
        Queue<Integer> myQ = new LinkedList<>();
        myQ.offer(start);
        visited[start] = true;

        while (!myQ.isEmpty()) {
            Integer polledNum = myQ.poll();
            List<Integer> nodes = list.get(polledNum);
            Collections.sort(nodes);


            System.out.print(polledNum + " ");

            for (Integer n : nodes) {
                if (!visited[n]) {
                    visited[n] = true;
                    myQ.offer(n);
                }
            }
        }


    }

    private static void dfs(int idx) {
        List<Integer> nodes = list.get(idx);
        Collections.sort(nodes);
        visited[idx] = true;
        System.out.print(idx +" ");
        for(Integer n : nodes) {
            if (!visited[n]) {
                dfs(n);
            }
        }
    }
}
