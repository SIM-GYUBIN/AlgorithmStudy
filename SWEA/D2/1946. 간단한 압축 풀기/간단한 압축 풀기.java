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

			String string = "";
			int idx = 1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String cha = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < num; j++) {
					string += cha;
					if(idx%10==0) {
						string += '\n';
					}
					idx++;
				}
			}
		

			sb.append("#").append(caseNum).append("\n").append(string).append("\n");
		}
		System.out.print(sb);
	}

}


