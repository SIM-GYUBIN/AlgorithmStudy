import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
   static ArrayList<ArrayList<Road>> list;

   public static class Road implements Comparable<Road>{
      int to;
      int cost;

      public Road(int to, int cost) {
         this.to = to;
         this.cost = cost;
      }

      @Override
      public int compareTo(Road o) {
         return this.cost - o.cost;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int X = Integer.parseInt(st.nextToken());

      list = new ArrayList<>();

      for (int i = 0; i <= N; i++) {
         list.add(new ArrayList<>());
      }

      for (int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine());

         int start = Integer.parseInt(st.nextToken());
         int end = Integer.parseInt(st.nextToken());
         int cost = Integer.parseInt(st.nextToken());

         list.get(start).add(new Road(end, cost));
      }

      int result = 0;

      for (int i = 1; i <= N; i++) {
         int walkTime = findFarStudent(i, X);
         int backTime = findFarStudent(X, i);
         if (result < walkTime+backTime) {
            result = walkTime+backTime;
         }
      }
      System.out.println(result);

   }

   private static int findFarStudent(int startVillage, int partyPlace) {

      PriorityQueue<Road> myQ = new PriorityQueue<>();
      int[] result = new int[list.size()];
      boolean[] visit = new boolean[list.size()];
      Arrays.fill(result, Integer.MAX_VALUE);
      result[startVillage] = 0;

      myQ.add(new Road(startVillage, 0));

      while(!myQ.isEmpty()) {

         Road nowVillage = myQ.poll();

         if (visit[nowVillage.to]) {
            continue;
         }
         visit[nowVillage.to] = true;

         for (Road nextVillage : list.get(nowVillage.to)) {

            if (result[nextVillage.to] > result[nowVillage.to] + nextVillage.cost) {
               result[nextVillage.to] = result[nowVillage.to] + nextVillage.cost;
               myQ.add(new Road(nextVillage.to, result[nextVillage.to]));
            }

         }
      }

      return result[partyPlace];
   }

}