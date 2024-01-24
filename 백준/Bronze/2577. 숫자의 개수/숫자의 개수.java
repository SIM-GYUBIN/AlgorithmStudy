import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        String sentence = Integer.toString(a*b*c);
        char[] sent = sentence.toCharArray();

        for (int i = 0; i < 10; i++) {
            int cnt = 0;
            for (int j = 0; j < sent.length; j++) {
                if (Character.getNumericValue(sent[j]) == i)
                    cnt++;
            }
            System.out.println(cnt);
        }


    }
}