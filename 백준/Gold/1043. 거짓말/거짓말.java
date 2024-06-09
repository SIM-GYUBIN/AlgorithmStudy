import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      boolean[] truthManChk = new boolean[N+1];
      int[][] party = new int[M][];


      st = new StringTokenizer(br.readLine());
      int truthManSu = Integer.parseInt(st.nextToken());
      int[] truthManArr = new int[truthManSu];

      for (int i = 0; i < truthManSu; i++) {
         int num = Integer.parseInt(st.nextToken());
         truthManChk[num] = true;
         truthManArr[i] = num;
      }

      LinkedList<LinkedList<Integer>> list = new LinkedList<>();

      for (int i = 0; i < N+1; i++) {
         list.add(new LinkedList<>());
      }

      for (int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine());
         int partyMemberSu = Integer.parseInt(st.nextToken());
         int startNum = Integer.parseInt(st.nextToken());

         party[i] = new int[partyMemberSu];
         party[i][0] = startNum;

         //인접리스트 만들기 & 파티 저장
         for (int j = 0; j < partyMemberSu-1; j++) {
            int num = Integer.parseInt(st.nextToken());
            list.get(startNum).add(num);
            list.get(num).add(startNum);

            party[i][j+1] = num;
         }
      }

      //카운트되면 안되는 사람 전부 찾기
      for (int truthMan: truthManArr) {
         Queue<Integer> myQ = new LinkedList<>();
         boolean[] visit = new boolean[N+1];
         myQ.offer(truthMan);

         //bfs
         while (!myQ.isEmpty()) {
            Integer num = myQ.poll();
            if (visit[num])
               continue;
            else
               visit[num] = true;
            truthManChk[num] = true;
            for (Integer getNum : list.get(num)) {
               myQ.offer(getNum);
            }
         }
      }

      int result = 0;
      for (int i = 0; i < party.length; i++) {
         boolean canLie = true;

         for (int j = 0; j < party[i].length; j++) {
            if(truthManChk[party[i][j]]) {
               canLie = false;
               break;
            }
         }

         if (canLie) {
            result++;
         }
      }
      System.out.println(result);
   }
}