import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i < T+1; i++) {
            String string = br.readLine();
            int length = string.length();
            arr = new int[length];
            for (int j = 0; j < length; j++) {
                arr[j] = string.charAt(j) - '0';
            }

            int idx = 0;
            int cnt = 0;


            int[] ogArr = new int[length];
            int num=0;
            for (int j = 0; j < length; j++) {
                if (Arrays.equals(ogArr, arr)) {
                    break;
                }

                if (arr[j] != num) {
                    cnt++;
                    num = arr[j];
                }
                for (int k = j; k < length; k++) {
                    ogArr[k] = num;
                }
            }

            System.out.println("#"+i+" "+cnt);
        }
    }
}