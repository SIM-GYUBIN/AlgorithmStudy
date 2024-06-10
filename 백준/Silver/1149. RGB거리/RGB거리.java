import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());

      int[][] costArr = new int[N+1][3];
      int[][] resultArr = new int[N+1][3];

      for (int i = 1; i < costArr.length; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());

         costArr[i][0] = Integer.parseInt(st.nextToken()); //R
         costArr[i][1] = Integer.parseInt(st.nextToken()); //G
         costArr[i][2] = Integer.parseInt(st.nextToken()); //B
      }

      for (int[] arr: resultArr) {
         Arrays.fill(arr, Integer.MAX_VALUE);
      }
      for (int i = 0; i < 3; i++) {
         resultArr[1][i] = costArr[1][i];
      }


      //bottom-top dp
      for (int floor = 2; floor <= N; floor++) {
         for (int afterColor = 0; afterColor < 3; afterColor++) {
            int after = costArr[floor][afterColor];

            for (int beforeColor = 0; beforeColor < 3; beforeColor++) {
               if (beforeColor == afterColor){
                  continue;
               }
               int before = resultArr[floor-1][beforeColor];
               if (resultArr[floor][afterColor] > before+after) {
                  resultArr[floor][afterColor] = before+after;
               }
            }
         }
      }

      int temp = Math.min(resultArr[N][0], resultArr[N][1]);
      int result = Math.min(temp, resultArr[N][2]);
      System.out.println(result);
   }
}