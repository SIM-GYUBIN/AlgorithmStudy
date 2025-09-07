import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        // logic
        // 앞자리부터 비교하며, 다른문자가 나오면 ?로 설정
        // 파일이름의 길이는 모두 같다
        // 정석은 뭐지? 뭐로 해야 빠르지

        String raw = arr[0];
        StringBuilder result = new StringBuilder();

        // i 자릿수
        // j 배열순회
        for (int i = 0; i < raw.length(); i++) {
            for (int j = 0; j < arr.length; j++) {

                char c = arr[j].charAt(i);

                if (c != raw.charAt(i)) {
                    result.append("?");
                    break;
                }

                if (j == arr.length - 1) {
                    result.append(raw.charAt(i));
                }
            }
        }

        System.out.println(result);
    }
}