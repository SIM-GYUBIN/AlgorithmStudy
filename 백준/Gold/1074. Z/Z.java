import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, R, C, TURN;
    static int[][] dirArr = {{0, 0},{0, 1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //2^N크기의 2차원 배열
        R = Integer.parseInt(st.nextToken());   //r행
        C = Integer.parseInt(st.nextToken());   //c열을 몇번째로 방문하는지?

        int size = (int) Math.pow(2, N);

        TURN = 0;

        makeZ(0, 0, size/2);

        System.out.println(TURN-1); //첫칸 방문이 0부터인데 그냥 결과에서 -1하기로 함

    }


    /**
     *  실패했던 코드 : 메모리 초과
     *  arr = new int[size][size];
     *  2중배열 선언에서 outofmemoryerror발생 2의15승(32768) 로 선언하면 힙 초과되는듯,,
     *  ㅋㅋ 근데 재귀할 때 저 배열쓰질 않았는데 그냥 습관처럼 만들고 나서 재귀에 문제있나 찾고있었음
     *  진짜 어휴 ㅠㅠㅠㅠㅠㅜ
     *
     *  실패2 : 시간초과
     *  미리 박스안에 들어있는지 확인하고, 아니면 그냥 턴 확 올려버리게 수정
     *  
     *
     */

    private static boolean makeZ(int x, int y, int size) {
        if (size == 1) {
            for (int[] dir: dirArr) {
                TURN++;
                int nextX = x+dir[0];
                int nextY = y+dir[1];
                if (nextX == R && nextY == C) {
                    return true;
                }
            }
            return false;
        }

        for (int[] dir: dirArr) {
            int nextX = x + size * dir[0];
            int nextY = y + size * dir[1];

            if (!judgeInside(nextX, nextY, size)) {
                TURN += size * size;
                continue;
            }

            if(makeZ(nextX, nextY, size / 2)) {
                return true;
            }
        }

        return false;
    }

    private static boolean judgeInside(int x, int y, int size) {
        if (R >= x && R < x + size && C >= y && C < y + size) {   //x와y가 시작점인 네모박스 안에 들어간다면
            return true;
        } else {
            return false;
        }
    }
}
