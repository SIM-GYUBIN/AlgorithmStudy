import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            arr = new int[9][9];
            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = findS();

            sb.append('#').append(caseNum).append(' ').append(result).append('\n');

        }
        System.out.println(sb);
    }
    static int findS() {
        //가로 찾기
        for (int i = 0; i < 9; i++) {
            Queue<Integer> myQ = new LinkedList<>();
            for (int j = 0; j < 9; j++) {
                if(myQ.contains(arr[i][j])) {
                    return 0;
                }
                myQ.add(arr[i][j]);
            }
        }

        //세로 찾기
        for (int i = 0; i < 9; i++) {
            Queue<Integer> myQ = new LinkedList<>();
            for (int j = 0; j < 9; j++) {
                if(myQ.contains(arr[j][i])) {
                    return 0;
                }
                myQ.add(arr[j][i]);
            }
        }

        //3x3 찾기
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                Queue<Integer> myQ = new LinkedList<>();
                for (int k = i; k < i+3; k++) {
                    for (int l = j; l < j+3; l++) {
                        if(myQ.contains(arr[k][l])) {
                            return 0;
                        }
                        myQ.add(arr[k][l]);
                    }
                }
            }
        }
        return 1;
    }
}