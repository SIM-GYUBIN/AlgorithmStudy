import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int T;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            N = Integer.parseInt(br.readLine());
            System.out.println("#"+caseNum);

            System.out.println(1);
            int[] line = {1};
            for (int i = 2; i < N+1; i++) {   //각 층
                int[] newLine = new int[i];
                for (int j = 0; j < i; j++) {   //각 수

                    int i1;
                    if (j==0) {
                        i1 = 0;
                    } else {
                        i1 = line[j-1];
                    }

                    int i2;
                    if (j==i-1) {
                        i2 = 0;
                    } else {
                        i2 = line[j];
                    }

                    int num = i1 + i2;
                    newLine[j] = num;

                    System.out.print(num + " ");
                }
                line = newLine;
                System.out.println();
            }

//            sb.append("#").append(caseNum).append(" ").append(cnt).append('\n');
        }
//        System.out.println(sb);
    }
}