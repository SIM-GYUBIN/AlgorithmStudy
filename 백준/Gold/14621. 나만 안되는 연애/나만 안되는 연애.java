import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static class School implements Comparable<School>{
        int num;
        int cost;

        public School(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(School o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<School>> dataList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            dataList.add(new ArrayList<>());
        }
        String[] gender = new String[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            gender[i] = st.nextToken();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dataList.get(a).add(new School(b, c));
            dataList.get(b).add(new School(a, c));
        }

        //prim
        PriorityQueue<School> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];
        visit[0] = true;
        pq.offer(new School(1, 0));

        int sumDist = 0;

        while (!pq.isEmpty()) {
            School now = pq.poll();
            int nowNum = now.num;
            int nowCost = now.cost;

            if (visit[nowNum]) continue;
            visit[nowNum] = true;

            sumDist += nowCost;

            for (School next : dataList.get(nowNum)) {
                int nextNum = next.num;
                int nextCost = next.cost;

                if (visit[nextNum] || Objects.equals(gender[nowNum], gender[nextNum])) continue;
                pq.offer(new School(nextNum, nextCost));
            }
        }

        //결과
        boolean isConnect = true;
        for (boolean b : visit) {
            if (!b) isConnect = false;
        }

        System.out.println(isConnect ? sumDist : -1);
    }
}