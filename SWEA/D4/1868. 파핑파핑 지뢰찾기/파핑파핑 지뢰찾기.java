import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int size;
	static int cnt;
	static char[][] arr;
	static boolean[][] visit;
	static int[][] dirArr = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			size = Integer.parseInt(br.readLine());
			arr = new char[size][size];
			visit = new boolean[size][size];
			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[i] = st.nextToken().toCharArray();
			}
			

			cnt = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (!visit[i][j] && arr[i][j]=='.') {
						bfs(i,j);
					}
				}
			}
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (!visit[i][j] && arr[i][j]=='.') {
						cnt++;
					}
				}
			}
		

			sb.append("#").append(caseNum).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}


	private static void bfs(int x, int y) {
		if (visit[x][y] || arr[x][y]=='*')
			return;
		
		Queue<int[]> myQ = new LinkedList<>();
		myQ.add(new int[] { x, y });
		
		boolean goCnt = true;
		while (!myQ.isEmpty()) {
			int[] pos = myQ.poll();
			int nowX = pos[0];
			int nowY = pos[1];

			boolean isZero = true;
			for (int i = 0; i < 8; i++) {
				int nextX = nowX + dirArr[i][0];
				int nextY = nowY + dirArr[i][1];
				if (0 <= nextX && nextX < size && 0 <= nextY && nextY < size) {
					if (arr[nextX][nextY] == '*') {
						isZero = false;
					}
				}
			}
			
			if (isZero) {
				if(goCnt) {
					cnt++;
					goCnt = false;
				}
				visit[nowX][nowY] = true;
				for (int i = 0; i < 8; i++) {
					int nextX = nowX + dirArr[i][0];
					int nextY = nowY + dirArr[i][1];
					if (0 <= nextX && nextX < size && 0 <= nextY && nextY < size) {
						if(!visit[nextX][nextY]) {
							myQ.add(new int[] { nextX, nextY });
							visit[nextX][nextY] = true;
						}
					}
				}
			}

		}
	}
}


