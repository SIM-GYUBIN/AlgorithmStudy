import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;
    static int cnt;
    static int[] arr;
    static int[] chkIdx;
    static boolean[] chkChange;
    static boolean[] fillTrue;
    static StringBuilder sb2;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            sb2 = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            arr = new int[N+2];
            chkChange = new boolean[N + 2];
            chkIdx = new int[N+2];
            fillTrue = new boolean[N+2];
            Arrays.fill(fillTrue, true);
            chkChange[0] = true;
            chkChange[N + 1] = true;
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                chkIdx[num] = i;
            }
            arr[N+1] = -1;
            chkIdx[N+1] = N+1;

//            System.out.println();
//            System.out.println(Arrays.toString(arr));
//            System.out.println(Arrays.toString(chkIdx));
//            System.out.println(Arrays.toString(chkChange));
//            System.out.println(Arrays.toString(fillTrue));

            boolean noCnt = true;
            for (int i = 1; i < N+1; i++) {
                if (i != arr[i]) {
                    noCnt = false;
                    continue;
                }
                chkChange[i] = true;
            }



            if (!noCnt) {    //이미 정렬된 경우면 로직이 망가짐
                findBox();
            }
            sb.append(cnt).append("\n");
            sb.append(sb2).append("\n");


        }
        System.out.println(sb);
    }

    private static void findBox() {

        int tmp;
        int locX = N+1;
        int findK = chkIdx[N];
        arr[locX] = arr[chkIdx[N]]; //맨 끝자리에 제일 큰수 집어넣음
        arr[chkIdx[N]] = -1;    //x를 옮김

        tmp = chkIdx[locX]; //idx저장 배열 반영
        chkIdx[locX] = chkIdx[N];
        chkIdx[N] = tmp;

        sb2.append(findK).append(" ");
        cnt++;


        /**
         * 1. 맨 뒤 x와 제일 큰 수 바꾸고 시작
         * 2. 그 다음부터 x위치에 자릿 수 맞는 놈 넣기
         * 3. 자릿수 맞는 놈 들어가면 chkChange배열에 true로
         * 4. 다 안채워졌는데 x가 끝자리에 가면 false놈들 중 가장 큰놈하고 바꾸고 재 시작 chkIdx에도 반영하고
         * 5. 전부 true로 채워지면 끝
         *
         * chkIdx[locX] => x의 위치 반환
         */


        for (int i = 0; i < 1500; i++) {
            if (Arrays.equals(chkChange, fillTrue)) {
                break;
            } else if (chkIdx[locX] == N + 1) { //4번 경우

                int false제일큰수 = 0;
                for (int j = N; j > 0; j--) {
                    if (!chkChange[j]) {
                        false제일큰수 = j;
                        break;
                    }
                }

                //반복문 내용
                int x위치 = chkIdx[locX];
                int x위치에있어야할수가있는위치 = chkIdx[false제일큰수];
                arr[x위치] = arr[x위치에있어야할수가있는위치];   //x의 위치에 x위치에 있어야 하는 놈 넣음
                arr[x위치에있어야할수가있는위치] = -1;

                chkChange[x위치] = true; //x가 있었던 위치

//                int tmp;
                tmp = chkIdx[locX]; //idx저장 배열 반영
                chkIdx[false제일큰수] = chkIdx[x위치];
                chkIdx[x위치] = x위치에있어야할수가있는위치;

                sb2.append(x위치에있어야할수가있는위치).append(" ");
                cnt++;

            } else {
                //반복문 내용
                int x위치 = chkIdx[locX];
                int x위치에있어야할수가있는위치 = chkIdx[x위치];
                arr[x위치] = arr[x위치에있어야할수가있는위치];   //x의 위치에 x위치에 있어야 하는 놈 넣음
                arr[x위치에있어야할수가있는위치] = -1;

                chkChange[x위치] = true; //x가 있었던 위치

//                int tmp;
                tmp = chkIdx[locX]; //idx저장 배열 반영
                chkIdx[locX] = chkIdx[x위치];
                chkIdx[x위치] = tmp;

                sb2.append(x위치에있어야할수가있는위치).append(" ");
                cnt++;
            }
        }
    }
}