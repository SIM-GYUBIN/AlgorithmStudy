import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
   static int N;
   static int E;
   static ArrayList<ArrayList<Node>> list;
   static boolean nono;

   public static class Node implements Comparable<Node> {
      int next;
      int cost;

      public Node(int next, int cost) {
         this.next = next;
         this.cost = cost;
      }

      @Override
      public int compareTo(Node o) {
         return this.cost - o.cost;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());

      list = new ArrayList<>();

      for (int i = 0; i <= N; i++) {
         list.add(new ArrayList<>());
      }

      for (int i = 0; i < E; i++) {
         st = new StringTokenizer(br.readLine());

         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());

         list.get(a).add(new Node(b, c));
         list.get(b).add(new Node(a, c));
      }

      st = new StringTokenizer(br.readLine());

      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());

      /**
       * 문제 :
       * 1에서 N으로 가는 최단 경로
       * v1, v2 통과해야 함 !!!!
       * node와 edge 재방문 가능 (위의 조건 통과할 때 쓰일 것)
       *
       * logic
       * 1에서 v1(v2)까지, v1(v2)에서 v2(v1)까지, v1(v2)에서 N까지
       *
       * 틀렸던 반례
       * 4 3
       * 1 2 1
       * 1 3 10
       * 2 4 1
       * 2 3 => 22
       * v1,v2중 빠른걸 먼저가더라도 전체적으로는 느릴 수 있음 (다시 되돌아와야해서)
       */

      int sum = 0;

      int v1Tov2 = dijkstra(v1, v2);
      int n1 = dijkstra(1, v1) + v1Tov2 + dijkstra(v2, N);
      int n2 = dijkstra(1, v2) + v1Tov2 + dijkstra(v1, N);

      sum = Math.min(n1, n2);

      if (!nono) {
         System.out.println(sum);
      } else {
         System.out.println(-1);
      }
   }

   private static int dijkstra(int start, int end) {
      PriorityQueue<Node> myQ = new PriorityQueue<>();
      int[] res = new int[N+1];
      Arrays.fill(res, Integer.MAX_VALUE);
      boolean[] visit = new boolean[N+1];

      res[start] = 0;
      myQ.offer(new Node(start, 0));

      while (!myQ.isEmpty()) {
         Node nowNode = myQ.poll();

         if (visit[nowNode.next]) {
            continue;
         }
         visit[nowNode.next] = true;

         for (Node nextNode: list.get(nowNode.next)) {
            if (res[nextNode.next] > res[nowNode.next] + nextNode.cost) {
               res[nextNode.next] = res[nowNode.next] + nextNode.cost;
               myQ.offer(new Node(nextNode.next, res[nextNode.next]));
            }
         }
      }
      if (res[end]==Integer.MAX_VALUE) {
         nono = true;
      }
      return res[end];
   }
}