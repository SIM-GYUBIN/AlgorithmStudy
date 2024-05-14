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
	

		int T = 10;
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			String noUse = br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr = new int[8];
			for (int i = 0; i < 8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(caseNum).append(" ");
			
			int idx = 0;
			int i = 1;
			boolean endSign = false;
			while (!endSign) {
				
				arr[idx] -= i;  
				
				if(arr[idx]<=0) {
					arr[idx] = 0;
					endSign = true;
				}

				
				if (idx==7) {
					idx=0;
				} else {
					idx++;
				}
				
				if (i==5) {
					i=1;
				} else {
					i++;
				}
			}
			
			for (int j = 0; j < 8; j++) {
				sb.append(arr[idx]).append(" ");
				if (idx==7) {
					idx=0;
				} else {
					idx++;
				}
			}
			sb.append("\n");
		

//			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}


