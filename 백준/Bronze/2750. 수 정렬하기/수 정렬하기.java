import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int turn = Integer.parseInt(br.readLine());
        int[] arr = new int[turn];

        for (int i = 0; i < turn; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        //버블 정렬
//        for (int i = 0; i < turn-1; i++) {  //회차
//            for (int j = 0; j < turn-1-i; j++) {    //뒤에 정렬된 부분 빼고 나머지 앞부분 정렬
//                int temp;
//                if (arr[j] > arr[j+1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//        }
//
        for (int i : arr) {
            System.out.println(i);
        }

//        System.out.println(Arrays.toString(arr));
    }
}
