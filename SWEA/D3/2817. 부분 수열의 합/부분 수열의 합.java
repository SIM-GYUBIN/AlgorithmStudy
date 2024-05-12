import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int K;
	static int result;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		for (int caseNum = 1; caseNum <T+1; caseNum++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i]= Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				dfs(i, 0);
			}


			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}

	private static void dfs(int num, int sum) {
		if (sum+arr[num]==K) {
			result++;
			return;
		}
		
		for (int i = num+1; i < N; i++) {
			if (num<N-1){
				dfs(i, sum+arr[num]);
			}
		}
	}



}

