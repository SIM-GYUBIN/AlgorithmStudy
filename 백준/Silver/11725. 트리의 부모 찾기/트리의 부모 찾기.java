import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        int[] resultArr = new int[N+1];
        boolean[] visit = new boolean[N+1];
        Queue<Integer> myQ = new LinkedList<>();
        myQ.offer(1);
        visit[1] = true;

        while (!myQ.isEmpty()) {
            Integer polled = myQ.poll();

            for (int num : list.get(polled)) {
                if (!visit[num]) {
                    visit[num] = true;
                    resultArr[num] = polled;
                    myQ.offer(num);
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(resultArr[i]);
        }


    }
}
