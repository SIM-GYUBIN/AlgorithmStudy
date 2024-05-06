import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N+1; i++) {

            String string = Integer.toString(i);
            StringBuilder sb2 = new StringBuilder();
            boolean is369 = false;
            for (int j = 0; j < string.length(); j++) {
                if (string.charAt(j) == '3' || string.charAt(j) == '6' || string.charAt(j) == '9') {
                    sb2.append('-');
                    is369 = true;
                }
            }

            if(!is369) {
                sb.append(i).append(' ');
            } else {
                sb.append(sb2).append(' ');
            }
        }
        System.out.println(sb);
    }
}