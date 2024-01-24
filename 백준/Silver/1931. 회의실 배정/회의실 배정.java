import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int MEETINGSNUM = Integer.parseInt(st.nextToken());
        int[][] meetings = new int[MEETINGSNUM][2];
        int result = 1;

        for (int cases = 0; cases < MEETINGSNUM; cases++) {
            st = new StringTokenizer(br.readLine());
            meetings[cases][0] = Integer.parseInt(st.nextToken());
            meetings[cases][1] = Integer.parseInt(st.nextToken());
        }

//        Arrays.sort(meetings, Comparator.comparingInt((int[] o) -> o[1])
//                .thenComparingInt((int[] o) -> o[0]));

        //종료시간 순으로 정렬, 종료시간 같을 시 시작 시간 순서대로 정렬
        Arrays.sort(meetings, (o1, o2) -> (o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]));

        int point = meetings[0][1];
        int STARTTIME = 0;
        int ENDTIME = 1;
        for (int cases = 1; cases < MEETINGSNUM; cases++) {
            //회의 시작시간이 겹치치않을 경우
            if (point <= meetings[cases][STARTTIME]) {
                result++;
                point = meetings[cases][ENDTIME];
            }
        }

        System.out.println(result);
    }

}