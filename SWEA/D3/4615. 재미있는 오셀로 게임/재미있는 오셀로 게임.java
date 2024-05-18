import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int[][] arr;
	static int[][] dirArr = {{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		

		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int [N+1][N+1];
			
			//초기화
			arr[N/2][N/2] = 2;
			arr[N/2][N/2+1] = 1;
			arr[N/2+1][N/2] = 1;
			arr[N/2+1][N/2+1] = 2;
			
		
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int stone = Integer.parseInt(st.nextToken());
				arr[x][y] = stone;
				
				int dStone = stone==1 ? 2 : 1; 
				
				
				
				for (int dir = 0; dir < 8; dir++) {
					int nX = x + dirArr[dir][0];
					int nY = y + dirArr[dir][1];
					
					Queue<Integer[]> myQ = new LinkedList<>();
					while (true) {
						if (0<nX && nX<N+1 && 0<nY && nY<N+1) {	
							if (arr[nX][nY]== dStone) {
								myQ.add(new Integer[] {nX, nY});
							} else if (arr[nX][nY]== stone) {
								while (!myQ.isEmpty()) {
									Integer[] temp = myQ.poll();
									arr[temp[0]][temp[1]] = stone;
								}
								break;
							} else {
								break;
							}
						} else {
							break;
						}
						
						nX += dirArr[dir][0];
						nY += dirArr[dir][1];
					}
				}
			}
			
			
			
			
			
			int blCnt =0;
			int whCnt =0;
			
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if (arr[i][j]==1) {
						blCnt++;
					} else if (arr[i][j]==2) {
						whCnt++;
					}
				}
			}

			sb.append("#").append(caseNum).append(" ").append(blCnt).append(" ").append(whCnt).append("\n");
		}
		System.out.print(sb);
	}
}


