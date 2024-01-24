import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(br.readLine());

        for (int i = 0; i < a; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            char[] charArray = st.nextToken().toCharArray();

            for (char ch : charArray) {
                sb.append(Character.toString(ch).repeat(num));
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}