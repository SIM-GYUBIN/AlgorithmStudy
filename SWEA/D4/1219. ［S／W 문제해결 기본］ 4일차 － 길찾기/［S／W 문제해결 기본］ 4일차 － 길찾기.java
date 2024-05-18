import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int result;
	static boolean isEnd;
	static LinkedList<LinkedList<Integer>> arr;
	static boolean[] visit;

	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = 10;
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int caseNO = Integer.parseInt(st.nextToken());
			int roadNum = Integer.parseInt(st.nextToken());
			
			arr = new LinkedList<>();
			
			for (int i = 0; i < 100; i++) {
				arr.add(new LinkedList<Integer>());
			}
			visit = new boolean[100];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < roadNum; i++) {
				int sp = Integer.parseInt(st.nextToken());
				int ep = Integer.parseInt(st.nextToken());
				
				arr.get(sp).add(ep);
			}
			isEnd = false;
			dfs(0);
			
			result = isEnd ? 1 : 0;

			sb.append("#").append(caseNO).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int idx) {
		if (idx == 99) {
			isEnd = true;
			return;
		}
		
		LinkedList<Integer> list = arr.get(idx);
		
		for (int i = 0; i < list.size(); i++) {
			Integer num = list.get(i);
			if (!visit[num]) {
				visit[num] = true;
				dfs(num);
			}
		}
		
	}

}


