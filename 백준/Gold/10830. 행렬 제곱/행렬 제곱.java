import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
   static int[][] arr;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      long B = Long.parseLong(st.nextToken());

      arr = new int [N][N];

      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < N; j++) {
            arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;  //1000입력, B=1 일 때 방지
         }
      }


      int[][] result = calculate(arr, B);

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < result.length; i++) {
         for (int j = 0; j < result.length; j++) {
            sb.append(result[i][j]).append(" ");
         }
         if (i!= result.length-1) {
            sb.append("\n");
         }
      }
      System.out.print(sb);
   }

   private static int[][] calculate(int[][] array, long b) {
      if (b==1) {
         return array;
      }

      int[][] temp = calculate(array, b/2);

      temp = gobWith(temp, temp);


      if (b%2==1) {
         temp =  gobWith(temp, arr);
      }

      return temp;
   }

   private static int[][] gobWith(int[][] arr1, int[][] arr2) {

      int[][] result = new int[arr1.length][arr1.length];

      for (int i = 0; i < arr1.length; i++) {
         for (int j = 0; j < arr1.length; j++) {

            long sum = 0;

            for (int k = 0; k < arr1.length; k++) {
               sum += (long) arr1[i][k] *arr2[k][j];
            }

            result[i][j] = (int) (sum% 1000);
         }
      }

      return result;
   }
}