import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int road = 0;

        //행
        for (int i = 0; i < N; i++) {
            int[] street = arr[i];
            if (findRoad(street))
                road++;
        }

        int[] street = new int[N];
//        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                street[j] = arr[j][i];
            }

            if(findRoad(street))
                road++;
        }

        System.out.println(road);


    }

    private static boolean findRoad(int[] street) {
        boolean[] hill = new boolean[N];
        for (int i = 0; i < street.length; i++) {

            if (i + 1 == N) {   //다음 자리가 오바됨
                break;
            }

            int nowH = street[i];
            int nextH = street[i + 1];
            if (nowH == nextH){
                continue;
            } else if (nowH + 1 == nextH) { //올라가는 경사 만남
                int upIdx = i+1;
                for (int j = 1; j <= L; j++) {
                    if (upIdx-j < 0 ||street[upIdx - j] != nowH || hill[upIdx-j]) {
                        return false;
                    }
                }
                //평탄화 잘됨

            } else if (nowH - 1 == nextH) { //내려가는 경사 만남
                for (int j = 1; j <= L; j++) {
                    if (i+j >= N || street[i + j] != nextH || hill[i+j]) {   //평탄화 안되어있거나 이미 설치된 경사 만남
                        return false;
                    }
                    hill[i + j] = true;
                }
                //평탄화 잘됨
            } else {
                return false;
            }
        }

        return true;
    }
}
