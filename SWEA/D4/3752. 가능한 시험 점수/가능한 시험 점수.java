import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int[] score;
    static Set<Integer> set;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T + 1; caseNum++) {
            N = Integer.parseInt(br.readLine());
            score = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int maxScore = 0;
            for (int i = 0; i < N; i++) {
                int inputNum = Integer.parseInt(st.nextToken());
                score[i] = inputNum;
                maxScore += inputNum;
            }

            /**
             * logic
             */

            boolean[] record = new boolean[maxScore+1];
            record[0] = true;

            /**
             * 만들 수 있는 가장 큰 수에서, 배점들을 빼면서 내려감
             * 제일 마지막 재귀에 0점에 도달할 경우 -> 만들 수 있는 점수
             */
            int cnt = 1;
            for (int problemNo = 0; problemNo < N; problemNo++) {
                for (int goalScore = maxScore; goalScore >= score[problemNo]; goalScore--) {
                    if (record[goalScore - score[problemNo]] && (!record[goalScore])) { //이 점수 만들 수 있나?
                        record[goalScore] = true;
                        cnt++;
                    }
                }
            }

            sb.append('#').append(caseNum).append(' ').append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}