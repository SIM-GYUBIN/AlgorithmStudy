import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static double E;
	static long result;
	static int[] arr;
	static boolean[] visit;
	static boolean[] fullTrue;

	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			
			N = Integer.parseInt(br.readLine());
			arr= new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb2.append(arr[i]).append(" ");
			}
			
			sb.append("#").append(caseNum).append(" ").append(sb2).append("\n");
		}
		System.out.print(sb);
	}


}


