import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int result;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> list;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for (int i = 0; i < N+1; i++) {
				list.add(new ArrayList<>());
			}
			
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int sp = Integer.parseInt(st.nextToken());
				int ep = Integer.parseInt(st.nextToken());
				list.get(sp).add(ep);
				list.get(ep).add(sp);
				
			}

			result = 0;

			for (int INDEX = 1; INDEX < N+1; INDEX++) {
				visit = new boolean[N+1];
				visit[INDEX] = true;
				dfs(INDEX, 1);
			}
			
			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}

	private static void dfs(int num, int cnt) {	
//		result = Math.max(result, cnt);
		if (cnt > result) {
			result = cnt;
		}
		
		for (int i = 0; i < list.get(num).size(); i++) {
			Integer idx = list.get(num).get(i);
			if(visit[idx] != true) {
				visit[idx]= true; 
				dfs(idx, cnt+1);
				visit[idx]= false; 
			}
		}
	
	}





}

