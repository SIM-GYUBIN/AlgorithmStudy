import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int result;
	static int maxCnt;
	static LinkedList<LinkedList<Integer>> arr;
	static int[] visit;
	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = 10;
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int su = Integer.parseInt(st.nextToken());
			int sp = Integer.parseInt(st.nextToken());
			
			arr = new LinkedList<>();
			for (int i = 0; i < 101; i++) {
				arr.add(new LinkedList<>());
			}
			visit = new int[101];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < su/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				arr.get(from).add(to);
			}
			
			result = 0;
			maxCnt = 0;
			
			bfs(sp);

			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int sp) {

		Queue<Integer> myQ = new LinkedList<>();
		
		myQ.add(sp);
		while(!myQ.isEmpty()) {
			int n = myQ.poll();
			LinkedList<Integer> list = arr.get(n);
			
			if (visit[n] > maxCnt) {
				result = n;
				maxCnt = visit[n];
			}else if (visit[n] == maxCnt && n > result) {
				result = n;
			}
			
			for (int i = 0; i < list.size(); i++) {
				Integer integer = list.get(i);
				if (visit[integer] == 0) {
					visit[integer] = visit[n]+1;
					myQ.add(integer);
				}
			}
			visit[sp] = 1;
		}
	}
}


