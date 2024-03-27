import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] ROAD;   // 0 : 빈곳, 숫자: 카운트, -1: 동생
    static boolean[] isGood;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ROAD = new int[N];
        isGood = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            ROAD[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 로직
         * 투 포인터로 서로 다른 수의 합을 이진 탐색으로 찾기
         * firstPoint와 secondPoint는 모든 배열을 돈다.
         * firstPoint + secondPoint의 합이 배열 내에 존재하면 goodCnt += 1
         *
         * @@@ 같은 수가 여러 개 있을 때! @@@
         * 1 + 2 = 3인데 3이 여러 개 있으면 3들을 전부 카운트 해야함!
         *
         * ### 어떤 수는 다른 수 두개로 이루어짐!!! => 내가 나로 이루어진 식에 들어가면 안됨 (반례1)
         * 3
         * 0 0 1
         * 정답: 0
         *
         */

        Arrays.sort(ROAD);

        int goodCnt = 0;

        for (int firstPoint = 0; firstPoint < N; firstPoint++) {
            for (int secondPoint = firstPoint + 1; secondPoint < N; secondPoint++) { //이미 계산한 수 한번 더 하지 않기 위해서

                int target = ROAD[firstPoint] + ROAD[secondPoint];


                //이분 탐색
                int start = 0;
                int end = N - 1;
                while (start <= end) {
                    int midIdx = (start + end) / 2;
                    int midValue = ROAD[midIdx];

                    if (midValue > target) {
                        end = midIdx - 1;
                    } else if (midValue < target) {
                        start = midIdx + 1;
                    } else if (midValue == target) {    // target이 없을 수도 있다는 것 생각 !
                        //이분탐색으로 찾은 값 등록 안되어 있다면 등록
                        if(!isGood[midIdx]) {
                            if (midIdx!=firstPoint && midIdx!=secondPoint) {    //자기 자신으로 이루어지면 안됨 (반례1)
                                goodCnt += 1;
                                isGood[midIdx] = true;
                            }

                            //중복 된 값 모두 등록하기 위해서 찾음
                            int[] direction = {-1, 1};
                            for (int i : direction) {
                                int findIndex = midIdx + i;
                                while ((findIndex >= 0 && findIndex < N) //findIndex가 범위 벗어나면 안됨
                                        && ROAD[findIndex] == target    //찾는 수는 중복된 수를 찾아야 할 뿐
                                        && !isGood[findIndex]   //이미 등록된 수는 건너뛰고
                                ) {
                                    if (midIdx!=firstPoint && midIdx!=secondPoint) {    //자기 자신으로 이루어지면 안됨 (반례1)
                                        goodCnt += 1;
                                        isGood[findIndex] = true;
                                    }
                                    findIndex += i;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

        System.out.println(goodCnt);
    }
}