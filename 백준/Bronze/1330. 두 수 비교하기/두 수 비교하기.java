import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int 에이 = Integer.parseInt(st.nextToken());
        int 비 = Integer.parseInt(st.nextToken());

        int 비교결과 = Integer.compare(에이, 비);

        if (비교결과 == 1)
            System.out.println(">");
        else if (비교결과 == -1)
            System.out.println("<");
        else if (비교결과 == 0)
            System.out.println("==");
    }
}