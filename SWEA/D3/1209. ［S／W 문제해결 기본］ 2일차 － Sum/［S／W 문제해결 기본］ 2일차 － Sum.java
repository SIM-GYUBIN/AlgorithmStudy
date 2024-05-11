import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
//		int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			int no = Integer.parseInt(br.readLine());
			arr = new int[100][100];
		
			int result = 0;
			
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/**
			 * logic
			 */
			
			int sum3 = 0;
			int sum4 = 0;
			for (int i = 0; i < 100; i++) {
				int sum = 0;
				int sum2 = 0;

				for (int j = 0; j < 100; j++) {
					sum += arr[i][j];
					sum2 += arr[j][i];
				}
				sum3 += arr[i][i];
				sum4 += arr[i][99-i];
				
				sum = Math.max(sum, sum2);
				result = Math.max(result, sum);
			}
		
			sum3 = Math.max(sum3, sum4);
			result = Math.max(sum3, result);

			
		
			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
		
	}

}
