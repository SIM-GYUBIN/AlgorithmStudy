import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int caseNum = 1; caseNum < 11; caseNum++) {

            int dump = Integer.parseInt(br.readLine());

            int[] arr = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            /**
             * logic
             */


            for (int i = dump; i > 0; i--) {
                Arrays.sort(arr);
                if (arr[99]-arr[0] == 0 || arr[99]-arr[0] == 1) {
                    break;
                }
                arr[99]--;
                arr[0]++;
            }
            Arrays.sort(arr);
            System.out.println("#"+caseNum+" "+(arr[99]-arr[0]));
        }
    }
}