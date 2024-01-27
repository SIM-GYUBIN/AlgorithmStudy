import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            boolean pal = true;
            int num = Integer.parseInt(br.readLine());
            if (num == 0)
                break;

            char[] words = Integer.toString(num).toCharArray();

            char[] temp = new char[words.length];

            for (int i = 0; i < words.length/2; i++) {
                temp[i] = words[i];
            }

            for (int i = words.length-1; i >= words.length - (words.length/2); i--) {
                if (temp[words.length-1-i] != words[i]) {
                    pal = false;
                    break;
                }
            }
            if (pal == true)
                sb.append("yes" + "\n");
            else
                sb.append("no" + "\n");

        }
        System.out.println(sb);

    }
}
