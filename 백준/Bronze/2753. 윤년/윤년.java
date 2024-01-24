import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputYear = Integer.parseInt(st.nextToken());
        int yoonYear;

        //4의 배수이면서 100의 배수가 아닌 경우
        if ((inputYear % 4 == 0) && (inputYear % 100 != 0))
            yoonYear = 1;
        //400의 배수인 경우
        else if (inputYear % 400 == 0)
            yoonYear = 1;
        //윤년이 아닌 경우
        else
            yoonYear = 0;
        

        System.out.println(yoonYear);
    }
}
