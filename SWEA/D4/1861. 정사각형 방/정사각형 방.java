import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int size;
	static long sp;
	static long cnt;
	static long tmp;
	static boolean chkGood;
	static long[][] arr;
	static boolean[][] visit;
	static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			N = Integer.parseInt(br.readLine());
			arr = new long[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());	
				}
			}
			

			cnt = 0;
			sp = N*N;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					chkGood = false;
					tmp = 0;
					dfs(i,j,1);
					if (chkGood) {
						if (cnt == tmp) {
							if (arr[i][j] < sp) {
								sp = arr[i][j];
							}
						} else if (tmp > cnt) {
							sp = arr[i][j];
							cnt = tmp;
						}
					}
				}
			}
			
		

			sb.append("#").append(caseNum).append(" ").append(sp).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}


	private static void dfs(int x, int y, int depth) {
		
		boolean noWay = true;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dirArr[i][0];
			int nextY = y + dirArr[i][1];
			if (0<=nextX && nextX<N && 0<=nextY && nextY<N) {	
				if(arr[nextX][nextY] == arr[x][y]+1) {
					noWay = false;
					dfs(nextX, nextY, depth+1);
				}
			}
		}
		if (noWay) {
			if (depth >= cnt) {
				chkGood = true;
				tmp = depth;
			}
		}
	}
}


