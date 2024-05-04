import java.util.Base64;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int caseNum = 1; caseNum < T+1; caseNum++) {
            String line = sc.nextLine();

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decode = decoder.decode(line);

            System.out.println("#"+caseNum+" "+new String(decode));
        }
    }
}