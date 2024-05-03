import java.util.Scanner;

public class Main {
   static int SIZE;
   static int[][] SEMO;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      SIZE = sc.nextInt();
      SEMO = new int[SIZE][];
      for (int i = 0; i < SIZE; i++) {
         for (int j = 0; j < i + 1; j++) {
            SEMO[i] = new int[j+1];
         }
      }

      for (int i = 0; i < SIZE; i++) {
         for (int j = 0; j < i + 1; j++) {
            SEMO[i][j] = sc.nextInt();
         }
      }

      /**
       * logic
       */

//      DP(SIZE-2,SIZE-2);

      for (int floor = SIZE - 2; floor >= 0; floor--) {
         for (int idx = SIZE-2; idx >= 0; idx--) {
            SEMO[floor][idx] = Math.max(SEMO[floor+1][idx]+SEMO[floor][idx], SEMO[floor+1][idx+1]+SEMO[floor][idx]);
         }
         SIZE--;
      }

      System.out.println(SEMO[0][0]);
   }
}