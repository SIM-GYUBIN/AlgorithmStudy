import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
   static long A;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      A = Long.parseLong(st.nextToken());
      long B = Long.parseLong(st.nextToken());
      long C = Long.parseLong(st.nextToken());


      long gob = gob(A, B, C);

      System.out.println(gob);

   }

   /**
    * 분할정복
    * 모듈러 연산은 결합법칙이 가능
    * 틀렷던 이유들
    * 1. Math.round(Math.pow(gob(a,b/2, c),2)) <= 오차 발생
    * 2. (gob(a, b / 2, c)%c * gob(a, b / 2, c) % c) % c <= 시간 잡아먹지 당연히;;
    */
   private static long gob(long a, long b, long c) {

      if (b == 1) {
         return A%c;
      }

      long caled = gob(a, b / 2, c) % c;
      long l = (caled*caled) % c;

      if (b%2==1) {
         l = ((l%c) * (A%c)) % c; 
      }
      
      return l;
   }
}