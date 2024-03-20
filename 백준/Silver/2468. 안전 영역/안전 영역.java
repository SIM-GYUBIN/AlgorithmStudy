import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;   //잠긴 곳과 방문한 곳 저장하는 배열
//    static boolean[][] isSinked;  // <- 굳이 필요 없음
    static int maxCount;
    static int[][] directionArr = {{-1, 0},{1,0},{0,-1},{0,1}}; //상 하 좌 우

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
        for (int height = 0; height < 101; height++) {    //입력 받을 때 제일 높은 높이 구별하는 것보다, 높이 100까지니까 이게 나을듯
            int cntTurn = 0;
            isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {   //잠긴 지역 맵 세팅
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= height)
                        isVisited[i][j] = true;
                }
            }

            for (int i = 0; i < N; i++) {   //순회
                for (int j = 0; j < N; j++) {
                    if (!isVisited[i][j]) {
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
        if (isVisited[x][y]) {    //방문한 적 있거나 잠긴 곳이면 pass
            return;
        }
        isVisited[x][y] = true;

        //선택한 노드 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionArr[i][0]; //배열 위 아래로
            int nextY = y + directionArr[i][1]; //배열 좌 우로

            nextX = validNumInRange(nextX);
            nextY = validNumInRange(nextY);

            dfs(nextX, nextY);  //다음 좌표로 dfs
        }

    }

    private static int validNumInRange(int num) {    //다음 노드가 배열을 벗어나지 않는지
        if (num < 0)
            num = 0;
        else if (num > N-1)
            num = N-1;

        return num;
    }
}
