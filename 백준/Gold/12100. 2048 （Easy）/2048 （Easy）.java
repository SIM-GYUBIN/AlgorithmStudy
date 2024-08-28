import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, RESULT;
    static int[][] arr;
    static String[] orders = {"up", "down", "left", "right"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
//        ORDER = st.nextToken();

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        RESULT = 0;
        dfs(arr, 5);

        System.out.println(RESULT);
    }

    private static void dfs(int[][] arr, int flr) {
        if (flr == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    RESULT = Math.max(RESULT, arr[i][j]);
                }
            }
            return;
        }

        for (String order : orders) {
            int[][] tmpArr = new int[N][N];
            for (int i = 0; i < N; i++) {
                tmpArr[i] = Arrays.copyOf(arr[i], N);
            }


            switch (order) {
                case "up":
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    logic(tmpArr);
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    break;

                case "down":
                    logic(tmpArr);
                    break;

                case "left":
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    logic(tmpArr);
                    turnRight(tmpArr);
                    break;
                case "right":
                    turnRight(tmpArr);
                    logic(tmpArr);
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    turnRight(tmpArr);
                    break;
            }

            dfs(tmpArr, flr - 1);
        }
    }

    private static void turnRight(int[][] arr) {
        int[][] tempArr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempArr[j][N - 1 - i] = arr[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.copyOf(tempArr[i], N);
        }
    }

    private static void logic(int[][] arr) {
        for (int c = 0; c < N; c++) {
            int sp = N - 2;
            int ep = N - 1;

            while (sp >= 0) {   //아래에서 위로
                if (arr[sp][c] == 0) {  //탐색 대상이 0
//                    sp--;
                } else {    //숫자 발견
                    if (arr[ep][c] == 0) {  //밑에가 0이면 (시작 시 맨밑이 0일때)
                        arr[ep][c] = arr[sp][c];
                        arr[sp][c] = 0;
                    } else if (arr[sp][c] == arr[ep][c]) {  //합쳐질 경우
                        arr[ep][c] = arr[sp][c] + arr[ep][c];
                        arr[sp][c] = 0;
                        ep--;
                    } else {    //안합쳐지고 위에 쌓이는 경우
                        ep--;
                        int temp = arr[sp][c];  //이렇게 해야지 바로 붙어있는 경우나, 떨어져있는 경우 처리 가능
                        arr[sp][c] = 0;
                        arr[ep][c] = temp;
                    }
                }
                sp--;
            }
        }
    }


}