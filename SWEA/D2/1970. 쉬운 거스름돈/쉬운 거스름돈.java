import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] moneys = {50000, 10000, 5000, 1000, 500, 100, 50 , 10};
			
			sb.append("#").append(caseNum).append("\n");
			
			N = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < 8; i++) {
				sb.append(N/moneys[i]).append(" ");
				N = N%moneys[i];
			}
			sb.append("\n");

//			sb.append("#").append(caseNum).append("\n").append(sb).append("\n");

		}
		System.out.print(sb);
	}


}

