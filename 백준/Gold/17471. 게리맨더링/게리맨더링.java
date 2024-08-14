import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int N, result;
    static int[] population;
    static boolean[] gu;
    static List<List<Integer>> list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }


        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int to = Integer.parseInt(st.nextToken());
                list.get(i).add(to);
            }
        }

        //dfs(선거구 나누기) <-- 시간 초과 원인 될듯
        result = INF;

        for (int su = 1; su <= N/2; su++) {
            for (int start = 1; start <= N; start++) {
                gu = new boolean[N + 1];
                gu[start] = true;
                getC(start,su, su);
            }
        }

        System.out.println(result==INF ? -1 : result);

    }

    private static void getC(int start, int su, int ogSu) {
        if (su == 1) {
            result = Math.min(result, seperate(ogSu));
            return;
        }

        for (int i = start+1; i <= N; i++) {
            gu[i] = true;
            getC(i, su - 1, ogSu);
            gu[i] = false;
        }
    }

    private static int seperate(int size) {
        boolean[] gu1 = Arrays.copyOf(gu, gu.length);
        boolean[] gu2 = new boolean[gu.length];

        for (int i = 1; i < gu.length; i++) {
            gu2[i] = !gu1[i];
        }

        int sum1 = calculate(gu1);
        if (sum1==-1) {
            return INF;
        }
        int sum2 = calculate(gu2);
        if (sum2==-1) {
            return INF;
        }

        return Math.abs(sum1 - sum2);
    }

    private static int calculate(boolean[] arr) {
        //연결 판단
        boolean[] visit = new boolean[arr.length];
        int start = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]) {
                start = i;
                break;
            }
        }
        Queue<Integer> myQ = new LinkedList<>();
        myQ.offer(start);
        visit[start] = true;
        while (!myQ.isEmpty()) {
            Integer now = myQ.poll();
            for (int next : list.get(now)) {
                if (visit[next]) continue;
                if (!arr[next]) continue;
                visit[next] = true;
                myQ.offer(next);
            }
        }

        //이어져잇음
        if (Arrays.equals(arr, visit)) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]) {
                    sum += population[i];
                }
            }

            return sum;
        } else {
            return -1;
        }
    }
}
