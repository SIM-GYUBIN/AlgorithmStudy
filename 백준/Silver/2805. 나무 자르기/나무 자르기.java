import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 임의의 길이로 자른다!
 * M보다 적게 얻었다 -> 톱날 높이 (H) 낮춘다.
 * M보다 많이 얻었다. -> '' 높인다.
 * 이분탐색으로
 * ! 적어도 M => M만큼 떨어지는게 없으면 더 가져갈 수도 있긴 함.
 * <p>
 * 함수()
 * 가운데 높이 준다.
 * 자르고 얻은거 계산한다.
 * M이면 끝.
 * 아니면 위 이분탐색으로 다음 톱날 높이 조정한다.
 * <p>
 * 초과 얻기 확인법
 * 한칸 위것도 계산해서 M보다 작아지면, 정답처리
 * or
 * 톱날 높이 높이는 경우 이전 위치도 포함. s == e이면 끝
 */

public class Main {

    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < trees.length; i++) {
            int num = Integer.parseInt(st.nextToken());
            trees[i] = num;
            max = Math.max(num, max);
        }

        // 시작
        System.out.println(logic(max / 2, 0, max));
    }

    private static int logic(int height, int sp, int ep) {
//        System.out.println("logic ! sp= " + sp + " / ep= " + ep);

        long mnt = cutTrees(height);
        if (mnt == M || sp == ep) return height;

        int nextHeight = 0;
        int nextSp = 0;
        int nextEp = 0;

        if (mnt < M) {
            nextHeight = (sp + height) / 2;
            nextSp = sp;
            nextEp = height - 1;
        }
        if (mnt > M) {
            nextHeight = (height + 1 + ep) / 2;
            nextSp = height;
            nextEp = ep;
        }

//        System.out.println("height= " + height + " / mnt= " + mnt);
        return logic(nextHeight, nextSp, nextEp);
    }

    private static long cutTrees(int height) {
        long sum = 0;

        for (int i = 0; i < trees.length; i++) {
            int tmp = trees[i] - height;
            if (tmp > 0) sum += tmp;
        }

        return sum;
    }
}
