import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
//		int T = Integer.parseInt(br.readLine());
		int T =10;
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[8][8];
		
			for (int i = 0; i < 8; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String line = st.nextToken();
				arr[i]= line.toCharArray(); 
			}
			
			int cnt = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8-N; j++) {
					char[] tmpArr = new char[N];
					char[] tmpArr2 = new char[N];
					for (int k = 0; k < N; k++) {
						tmpArr[k] = arr[i][j+k];
						tmpArr2[k] = arr[j+k][i];
					}
					if(checking(tmpArr)) {
						cnt++;
					};
					if(checking(tmpArr2)) {
						cnt++;
					};
				}
			}
			
		
			sb.append("#").append(caseNum).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
		
	}

	private static boolean checking(char[] line) {
		int endIdx;
		if (N%2==1) {
			endIdx = N/2+1;
		} else {
			endIdx = N/2;
		}
		
		for (int i = 0; i < endIdx; i++) {
			if(line[i] != line[N-1-i]) {
				return false;
			}
		}
		
		return true;
	}
}
