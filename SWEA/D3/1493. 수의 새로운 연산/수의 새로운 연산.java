import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static long result;
	static int[][] arr;

	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		arr = new int[300][300];
		int num = 1;
		first : 
		for (int i = 2; i < 300; i++) {	//합
			for (int j = 1; j < i; j++) {	//x
				arr[j][i-j] = num;  
				num++;
			}
		}

		int T = Integer.parseInt(br.readLine());

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			int x1 = p;
			int flag=0;
			for (int i = 1; i < 200; i++) {
				if (x1 - i <=0) {
					flag = i;
					break;
				}
				x1 = x1 - i;
			}

			int y1 = flag +1 - x1;
			
			int x2 = q;
			int flag2=0;
			for (int i = 1; i < 200; i++) {
				if (x2 - i <=0) {
					flag2 = i;
					break;
				}
				x2 = x2 - i;
			}

			int y2 = flag2 +1 - x2;
			
			int result = arr[x1+x2][y1+y2];
			
			
			
			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}


}


