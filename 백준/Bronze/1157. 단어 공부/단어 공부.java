import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String 문장 = br.readLine();
        char[] 나눈문장 = 문장.toCharArray();

        int 제일많은횟수 = 0;
        for (int 회차 = 0; 회차 <나눈문장.length; 회차++) {
            if(Character.isLowerCase(나눈문장[회차])) {
                나눈문장[회차] = Character.toUpperCase(나눈문장[회차]);
            }
        }

        int[] 알파벳 = new int[26];

        for (int 회차 = 0; 회차 <나눈문장.length; 회차++) {
            int 숫자가된문자 = 나눈문장[회차] - 'A';
            알파벳[숫자가된문자]++;
        }

        int 제일많이든곳의번호 = 0;
        int 제일많이든곳의개수 = 0;
        boolean 중복인가 = false;
        for (int 회차 = 0; 회차 < 26; 회차++) {
            if(제일많이든곳의개수 < 알파벳[회차]) {
                제일많이든곳의개수 = 알파벳[회차];
                제일많이든곳의번호 = 회차;
                중복인가 = false;
            } else if (제일많이든곳의개수 == 알파벳[회차]) {
                중복인가 = true;
            }
        }

        System.out.println(중복인가 ? '?' : (char)(제일많이든곳의번호+'A'));
    }
}