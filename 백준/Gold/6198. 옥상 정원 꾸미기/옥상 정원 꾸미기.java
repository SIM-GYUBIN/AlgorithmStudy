import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. index와 높이를 담은 2차원 배열을 만듦
 * 2. 초기값을 스택에 담음
 * 3. 다음 빌딩의 높이가 스택의  top보다 작다면 그대로 스택에 push
 * 4. 다음 빌딩의 높이가 스택의 top보다 크다면, pop하고 다음 빌딩의 index를 pop된 빌딩의 limit배열에 담음
 * 5. pop하고 새로 top이 된 빌딩과도 비교하며 4번 반복
 * 6. 끝까지 순회 후 스택에 남은 빌딩의 limit에는 endIndex값을 담음
 * 7. limit배열의 값과 빌딩의 index 차이를 계산하고 더함
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int INDEX = 0;
        int HEIGHT = 1;

        int N = Integer.parseInt(st.nextToken());

        int endIndex = N +1;
        int[][] heights = new int[endIndex][2]; //1. index와 높이를 담은 2차원 배열을 만듦
        int[] limits = new int[endIndex];

        for (int i = 1; i < endIndex; i++) {
            st = new StringTokenizer(br.readLine());
            heights[i][INDEX] = i;
            heights[i][HEIGHT] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(heights[1]); //2. 초기값을 스택에 담음

        for (int i = 2; i < endIndex; i++) {    //초기값 다음 빌딩 부터 시작
            //3. 다음 빌딩의 높이가 스택의 top보다 작다면 그대로 스택에 push
            if (stack.peek()[HEIGHT] > heights[i][HEIGHT]) {
                stack.push(heights[i]);
                continue;
            }

            //4. 다음 빌딩의 높이가 스택의 top보다 크거나 같다면, pop하고 다음 빌딩의 index를 pop된 빌딩의 limit배열에 담음
            while (!stack.isEmpty() && stack.peek()[HEIGHT] <= heights[i][HEIGHT]) {
                int[] popedTower = stack.pop();
                limits[popedTower[INDEX]] = heights[i][0];
            }
            stack.push(heights[i]);
        }

        //6. 끝까지 순회 후 스택에 남은 빌딩의 limit에는 endIndex값을 담음
        while (!stack.isEmpty()) {
            int[] popedTower = stack.pop();
            limits[popedTower[INDEX]] = endIndex;
        }

        //7. limit배열의 값과 빌딩의 index 차이를 계산하고 더함

        long result = 0;
        for (int i = 1; i < endIndex; i++) {
            result +=  limits[i] - i - 1;
        }

        System.out.println(result);
    }

}