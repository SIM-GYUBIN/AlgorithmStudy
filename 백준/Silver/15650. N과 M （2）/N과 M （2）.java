import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        find(0, M, N, sb);

    }

    private static void find(int startNum, int flr, int maxNum, StringBuilder sb) {


        if (flr == 0) {
            System.out.println(sb);
            return;
        } else {

            for (int i = startNum + 1; i <= maxNum; i++) {
                StringBuilder sbs = new StringBuilder();
                sbs.append(sb).append(i).append(" ");
                find(i, flr - 1, maxNum, sbs);
//                System.out.print("\n");
            }

        }
    }
}

