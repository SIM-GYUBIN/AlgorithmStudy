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
	

		int T = 10;
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			int arrLength = Integer.parseInt(br.readLine());
			
			arr = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}


			/**
			 * logic
			 */
			
			
			int cnt = 0;
			for (int i = 0; i < 100; i++) {
				boolean findRed = false;
				for (int j = 0; j < 100; j++) {
					if(arr[j][i]==1) {
						findRed = true;
					}
					
					if (findRed && arr[j][i]==2) {
						findRed = false;
						cnt++;
					}
				}
				
			}
			sb.append("#").append(caseNum).append(" ").append(cnt).append("\n");

		}
		System.out.print(sb);
	}

}

