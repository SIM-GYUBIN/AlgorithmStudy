import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 그냥 단순하게 배열 채우고
 * 행을 탐색하며 다 채우기로 했음
 */
public class Main {
    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[][] world = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < W; c++) {
            int height = Integer.parseInt(st.nextToken());
            for (int r = H-1; r > (H-1)-height; r--) {
                world[r][c] = 1;
            }
        }

        for (int r = 0; r < H; r++) {
            int[] row = world[r];
            int sp = 0;

            for (int c = 0; c < W; c++) {
                if (row[c] == 1) {
                    sp = c;
                    break;
                }
            }

            int ep = 1;
            while (ep < W) {
                if ((row[sp]==1) && (row[ep]==1)) {
                    fillWater(row, sp, ep);
                    sp = ep;
                }

                ep++;
            }
        }

        int result = countWater(world);

        System.out.println(result);
    }

    private static int countWater(int[][] world) {

        int count = 0;
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (world[r][c] == 2) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void fillWater(int[] row, int sp, int ep) {
        for (int i = sp+1; i < ep; i++) {
            row[i] = 2;
        }
    }
}