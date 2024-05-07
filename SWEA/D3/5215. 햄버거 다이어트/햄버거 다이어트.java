import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int N;
    static int maxCal;
    static Integer[][] menu;
    static int maxScore;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            maxCal = Integer.parseInt(st.nextToken());

            menu = new Integer[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    menu[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Arrays.sort(menu, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    if(Objects.equals(o1[1], o2[1])) {
                        return o2[0] - o1[0];
                    }
                    return o1[1] - o2[1];
                }
            });

//            System.out.println(Arrays.deepToString(menu));
            maxScore = 0;
            for (int i = 0; i < N; i++) {
                dfs(menu[i][0], menu[i][1], i);
            }

            sb.append('#').append(caseNum).append(' ').append(maxScore).append('\n');

        }
        System.out.println(sb);
    }

    private static void dfs(int score, int cal, int idx) {
        if (cal > maxCal) {
            maxScore = Math.max(maxScore,score-menu[idx][0]);
            return;
        }
        for (int i = idx+1; i < N; i++) {   //다음 재료
            dfs(score + menu[i][0], cal + menu[i][1], i);
        }
        maxScore = Math.max(maxScore,score);
    }
}