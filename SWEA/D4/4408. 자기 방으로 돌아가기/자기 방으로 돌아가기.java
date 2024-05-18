import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;

	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			N = Integer.parseInt(br.readLine());
		
			int[] arr = new int[401];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int sp = Integer.parseInt(st.nextToken());
				int ep = Integer.parseInt(st.nextToken());
				
				if (ep<sp) {
					int temp = sp;
					sp = ep;
					ep = temp;
				}
				
				if (sp%2==0) {
					sp -= 1;
				}
				if (ep%2==1) {
					ep += 1;
				}
				
				for (int j = sp; j < ep+1; j++) {
					arr[j] = arr[j]+1;
				}
			}
			int result = 0;
			for (int i = 0; i < 400; i++) {
				if (result < arr[i])
					result = arr[i];
			}
		

			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

}


