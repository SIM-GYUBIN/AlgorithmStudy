import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static char[][] arr;

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = 10;
		
		
		int size = 100;
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			int noUse = Integer.parseInt(br.readLine());
			arr = new char[100][100];
			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String line  = st.nextToken();
				arr[i] = line.toCharArray();
			}
			
			int result = 0;
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					for (int end = 99; end >= 0; end--) {
						int left = j;
						int right = end;
						boolean isPal = true;
						while (left<right) {
							if(arr[i][left] != arr[i][right]) {
								isPal = false;
								break;
							}
							left++;
							right--;
						}
						if(isPal) {
							result = Math.max(result, end-j+1);
						}
					}

				}
			}
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					for (int end = 99; end >= 0; end--) {
						int up = j;
						int down = end;
						boolean isPal = true;
						while (up<down) {
							if(arr[up][i] != arr[down][i]) {
								isPal = false;
								break;
							}
							up++;
							down--;
						}
						if(isPal) {
							result = Math.max(result, end-j+1);
						}
					}

				}
			}
		

			sb.append("#").append(noUse).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}


