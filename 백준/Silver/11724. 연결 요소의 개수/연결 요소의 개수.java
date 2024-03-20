import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] arr;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //node 수
        int M = Integer.parseInt(st.nextToken());   //edge 수

        visited = new boolean[N+1]; //방문 배열
        arr = new ArrayList[N+1];   // 인접 리스트

        //arraylist 초기화
        for (int i = 1; i < N+1; i++) { //여기 1부터 초기화!
            arr[i] = new ArrayList<Integer>();
        }

        //그래프 데이터 넣기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            //양 노드에 서로 넣어줘야해
            arr[start].add(next);
            arr[next].add(start);
        }

        int count = 0;
        for (int i = 1; i < N+1; i++) {         //여기 i=0으로 해서 틀렸음 **괜히 i=0일때 count 올라가게 ㄴㄴ
            if (!visited[i]) {  //방문하지 않은 노드에서 탐색 시작
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    private static void DFS(int now) {
        if (visited[now]) {  //현재노드(now)가 방문한적 있다면
            return;
        }
        visited[now] = true;
        for(int i : arr[now]) { //현재 노드에서 연결된 node들 탐색하면서
            if (!visited[i]) {  //리스트에서 방문하지않은 node있으면 걔 dfs
                DFS(i);
            }
        }
    }
}
