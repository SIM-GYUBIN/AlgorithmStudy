import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int B;
	static int result;
	static int[] arr;

	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		
		
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				bfs(i);
			}
			

			sb.append("#").append(caseNum).append(" ").append(result-B).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int idx) {
		
		Queue<Integer[]> myQ = new LinkedList<>();
		myQ.add(new Integer[]{idx, arr[idx]});	//0: idx , 1: sum
		
		while(!myQ.isEmpty()) {
			Integer[] outNum = myQ.poll();
			
			if (outNum[1] >= B) {
				if (outNum[1] < result) {
					result = outNum[1];
				}
				continue;
			}
			
			
			for (int j = outNum[0]+1; j < N; j++) {
				myQ.add(new Integer[]{j, outNum[1] + arr[j]});
			}
			
		}
		
	}

}


