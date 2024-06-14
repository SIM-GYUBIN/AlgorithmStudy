import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

   static int[] arr;
   static int[] mem;

   public static void main(String[] args) throws Exception {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());

      arr = new int[N];
      mem = new int[N];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
         int num = Integer.parseInt(st.nextToken());
         arr[i] = num;
      }

      Arrays.fill(mem, 1);

      //dp(bottom-up)
      for (int now = 0; now < N; now++) {
         for (int i = 0; i < now; i++) {
            if (arr[now] > arr[i] && mem[now] < mem[i]+1 ) {
               mem[now] = mem[i] + 1;
            }
         }
      }

      Arrays.sort(mem);

      System.out.println(mem[N-1]);

   }
}

