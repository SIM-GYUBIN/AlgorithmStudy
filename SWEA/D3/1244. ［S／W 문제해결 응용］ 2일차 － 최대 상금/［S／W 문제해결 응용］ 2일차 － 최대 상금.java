import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int TESTCASE;
    static Integer[][] nums;
    static int[] chance;
    static Integer maxNums;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TESTCASE = Integer.parseInt(br.readLine());

        nums = new Integer[TESTCASE][];
        chance = new int[TESTCASE];
        for (int i = 0; i < TESTCASE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tmpStr = st.nextToken();
            nums[i] = new Integer[tmpStr.length()];
            for (int j = 0; j < tmpStr.length(); j++) {
                nums[i][j] = tmpStr.charAt(j) - '0'; // 각 문자를 int로 변환
            }
            chance[i] = Integer.parseInt(st.nextToken());
        }

        for (int caseNum = 0; caseNum < TESTCASE; caseNum++) {    //각 테스트 케이스
            maxNums = 0;

            if (nums[caseNum].length < chance[caseNum]) {
                int x = 0;
                if (chance[caseNum] % 2 != nums[caseNum].length%2) {
                    x--;
                }
                chance[caseNum] = nums[caseNum].length + x;
            }

            dfs(nums[caseNum], chance[caseNum]);
            System.out.println("#"+(caseNum+1)+" "+maxNums);
        }
    }

    private static void dfs(Integer[] arr, int cnt) {
        if (cnt == 0) {
            Integer result = arrayToInt(arr);
            maxNums = Math.max(maxNums, result);
            return;
        }

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = arr.length-1; j > i; j--) {
//            for (int j = i + 1; j < arr.length; j++) {

                swap(arr, i, j);

                dfs(arr, cnt-1);

                // 재귀 호출이 끝나면 원상복구
                swap(arr, i, j);
            }
        }
    }

    private static void swap(Integer[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static Integer arrayToInt(Integer[] arr) {
        StringBuilder sb = new StringBuilder();
        for (Integer num : arr) {
            sb.append(num);
        }
        return Integer.parseInt(sb.toString());
    }
}