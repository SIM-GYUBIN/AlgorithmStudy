import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int CASE = 1; CASE < 11; CASE++) {

            int N = Integer.parseInt(br.readLine());
            int[] heights = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }


            int sum = 0;
            for (int now = 2; now < N-2; now++) { //빌딩 앞에서 부터 순차적으로
                /**
                 * 경우의 수
                 * 1. 양 두 칸 내에 더 크거나 같은 빌딩 -> 아예 탈락
                 * 2. 1번의 경우를 제외하고
                 *  a. 양 두 칸내에 제일 큰 빌딩만큼을 뺌
                 */

                int nearTop = 0;
                for (int nearBuilding = now-2; nearBuilding <= now+2; nearBuilding++) {
                    if (nearBuilding == now) {
                        continue;
                    } else if (heights[nearBuilding] >= heights[now]) { //1
                        nearTop = heights[now]; //더해지는 값 0으로 만들기 위해서
                        break;
                    } else {
                        if (heights[nearBuilding] > nearTop) {
                            nearTop = heights[nearBuilding];
                        }
                    }
                }
                sum += heights[now] - nearTop;
            }
            sb.append("#"+CASE+" "+sum+"\n");
        }
        System.out.println(sb.toString().trim());
    }
}