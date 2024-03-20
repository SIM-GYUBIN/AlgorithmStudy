import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static boolean[][] isSinked;
    static int maxCount;
    static int[][] directionArr = {{-1, 0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        maxCount = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //높이 증가 (한 턴) (height = 비)  ** 0부터!! 비 안올 수 도 있음!!!
        for (int height = 0; height < 101; height++) {    //입력값받을 때 제일 높은 높이 구별하는 것보다 높이 100까지니까 이게 나을듯
            int cntTurn = 0;
            isSinked = new boolean[N][N];
            isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {   //잠긴 지역 맵 세팅
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= height)
                        isSinked[i][j] = true;
                }
            }

            for (int i = 0; i < N; i++) {   //순회
                for (int j = 0; j < N; j++) {
                    if (!isSinked[i][j] && !isVisited[i][j]) {
                        cntTurn++;
                        dfs(i,j);
                    }
                }
            }

            //턴 종료시 마다
            if (cntTurn > maxCount)
                maxCount = cntTurn;
        }

        System.out.println(maxCount);
    }

    private static void dfs(int x, int y) {
        if (isSinked[x][y] || isVisited[x][y]) {    //방문한 적 있거나 잠긴 곳이면 pass
            return;
        }
        isVisited[x][y] = true;

        //선택한 노드 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionArr[i][0];
            int nextY = y + directionArr[i][1];

            if (nextX < 0)
                nextX = 0;
            else if (nextX > N-1)
                nextX = N-1;

            if (nextY < 0)
                nextY = 0;
            else if (nextY > N-1)
                nextY = N-1;


            dfs(nextX, nextY);
        }

    }
}
//isSinked 하나만 있어도 될 것 같은데 해보기