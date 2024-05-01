import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int caseNum = Integer.parseInt(br.readLine());
            arr = new int[101];

            StringTokenizer st = new StringTokenizer(br.readLine());


            for (int j = 0; j < 1000; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[num] += 1;
            }

            int modeNum = 0;
            for (int j = 0; j < 101; j++) {
                if (arr[j] >= arr[modeNum]) {
                    modeNum = j;
                }
            }
            System.out.println("#" + caseNum + " " + modeNum);
        }
    }
}