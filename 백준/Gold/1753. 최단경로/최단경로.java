import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
   static int V;
   static int E;
   static List<ArrayList<Node>> list;
   static int[] dist;

   public static class Node implements Comparable<Node> {
      int num;
      int cost;

      public Node(int num, int cost) {
         this.num = num;
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

      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(br.readLine());

      list = new ArrayList<>();
      for (int i = 0; i <= V; i++) {
         list.add(new ArrayList<>());
      }
      dist = new int[V+1];
      Arrays.fill(dist, Integer.MAX_VALUE);

      for (int i = 0; i < E; i++) {
         st = new StringTokenizer(br.readLine());
         int sp = Integer.parseInt(st.nextToken());
         int ep = Integer.parseInt(st.nextToken());
         int cost = Integer.parseInt(st.nextToken());
         list.get(sp).add(new Node(ep, cost));
      }
      dijkstra(K);

      for (int i = 1; i <= V; i++) {
         if (dist[i]==Integer.MAX_VALUE) {
            System.out.println("INF");
         } else {
            System.out.println(dist[i]);
         }
      }
   }

   private static void dijkstra(int k) {
      PriorityQueue<Node> myQ = new PriorityQueue<>();
      boolean[] visit = new boolean[V+1];
      dist[k] = 0;
      myQ.add(new Node(k, 0));

      while (!myQ.isEmpty()) {
         Node nowNode = myQ.poll();
         visit[nowNode.num] = true;

         for (Node nextNode: list.get(nowNode.num)) {
            if(!visit[nextNode.num] && dist[nextNode.num] > dist[nowNode.num] + nextNode.cost) {
               dist[nextNode.num] = dist[nowNode.num] + nextNode.cost;
               myQ.offer(new Node(nextNode.num, dist[nextNode.num]));
            }
         }
      }
   }

}