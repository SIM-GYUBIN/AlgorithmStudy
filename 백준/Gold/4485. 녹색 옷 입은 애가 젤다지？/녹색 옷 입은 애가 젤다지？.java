import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
   static int N;
   static int[][] arr;

   static int[][] dist;
   static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

   public static class Node implements Comparable<Node> {
      int x;
      int y;
      int cost;

      public Node(int x, int y,int cost) {
         this.x = x;
         this.y = y;
         this.cost = cost;
      }

      @Override
      public int compareTo(Node o) {
         return this.cost - o.cost;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int caseNum = 1;
      while (true) {
         N = Integer.parseInt(br.readLine());
         if (N==0) {
            break;
         }
         arr = new int[N][N];
         dist = new int[N][N];
         for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
         }

         for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               arr[i][j] = Integer.parseInt(st.nextToken());
            }
         }

         System.out.println("Problem "+(caseNum++)+": "+dijkstra());
      }
   }

   private static int dijkstra() {
      PriorityQueue<Node> myQ = new PriorityQueue<>();
      boolean[][] visit = new boolean[N][N];

      myQ.offer(new Node(0,0,arr[0][0]));
      dist[0][0] = arr[0][0];

      while (!myQ.isEmpty()) {
         Node nowNode = myQ.poll();
         int nowX = nowNode.x;
         int nowY = nowNode.y;
         visit[nowX][nowY] = true;

         for (int i = 0; i < 4; i++) {
            int nextX = nowX + dir[i][0];
            int nextY = nowY + dir[i][1];
            if (canGo(nextX, nextY) && !visit[nextX][nextY] && dist[nextX][nextY] > dist[nowX][nowY] + arr[nextX][nextY]) {
               dist[nextX][nextY] = dist[nowX][nowY] + arr[nextX][nextY];
               myQ.offer(new Node(nextX, nextY, dist[nextX][nextY]));
            }
         }
      }
      return dist[N-1][N-1];
   }

   private static boolean canGo(int x, int y) {
      if (0<=x && x<N && 0<=y && y<N) {
         return true;
      } else {
         return false;
      }
   }


}