import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

//        String[] stArr = new String[N];
        ArrayList<String> stList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (!stList.contains(input))
                stList.add(input);
        }

        int arrListSize = stList.size();
        String[] stArr = stList.toArray(new String[stList.size()]);

        Arrays.sort(stArr, (o1, o2) -> {
            if (o1.length() < o2.length())
                return -1;
            else if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else
                return 1;
        });


        for (int i = 0; i < stArr.length; i++) {
            sb.append(stArr[i] + '\n');
        }

        System.out.println(sb);
    }
}