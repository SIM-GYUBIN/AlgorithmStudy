import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] isVisited;   //잠긴 곳과 방문한 곳 저장하는 배열
    static int[][] directionArr = {{-1, 0},{1,0},{0,-1},{0,1}}; //상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

//            for (int j = 0; j < M; j++) {
//                map[i][j] = Integer.parseInt(line.substring(j, j + 1));
//            }

            map[i] = Stream.of(line.split("")).mapToInt(Integer::parseInt).toArray();

//            for(int j=0; j<M; j++) {
//                map[i][j] = line.charAt(j) - '0';
//            }
        }

        BFS(0, 0);
        System.out.println(map[N - 1][M - 1]);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        isVisited[i][j] = true;
        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = now[0] + directionArr[k][0];
                int y = now[1] + directionArr[k][1];
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (map[x][y] != 0 && !isVisited[x][y]) {
                        //가자
                        isVisited[x][y] = true;
                        map[x][y] = map[now[0]][now[1]] + 1;    //배열에 depth기록
                        queue.add(new int[] {x,y});
                    }
                }
            }
        }
    }
}