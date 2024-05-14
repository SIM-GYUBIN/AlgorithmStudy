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
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			int N = Integer.parseInt(br.readLine());
			
			int sum = 0;
			int mag = 1;
			for (int i = 1; i < N+1; i++) {
				sum += mag*i;
				mag *= -1;
			}
			

		

			sb.append("#").append(caseNum).append(" ").append(sum).append("\n");
		}
		System.out.print(sb);
	}
}


