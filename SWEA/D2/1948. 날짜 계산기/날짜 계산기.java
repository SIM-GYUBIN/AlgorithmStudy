import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] arr;

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		

		int[] mDay = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int fM = Integer.parseInt(st.nextToken());
			int fD = Integer.parseInt(st.nextToken());
			int sM = Integer.parseInt(st.nextToken());
			int sD = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			if (fM==sM) {
				sum = sD-fD+1;
			} else {
				sum += mDay[fM] - fD +1;
				for (int i = fM+1; i < sM; i++) {
					sum += mDay[i];
				}
				sum+=sD;
			}
			

		

			sb.append("#").append(caseNum).append(" ").append(sum).append("\n");
		}
		System.out.print(sb);
	}
}


