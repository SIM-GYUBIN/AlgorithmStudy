import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] arr;
	static int arrSize = 16;
	static boolean[][] visit;
	static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
	static int startPointX;
	static int startPointY;
	static int endPointX;
	static int endPointY;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = 10;
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			int no = Integer.parseInt(br.readLine());
			
			arr = new int[arrSize][arrSize];
			visit = new boolean[arrSize][arrSize];
			
			startPointX = 0;
			startPointY = 0;
			endPointX = 0;
			endPointY = 0;
			
			for (int i = 0; i < arrSize; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String line = st.nextToken();
				for (int j = 0; j < arrSize; j++) {
					int num = line.charAt(j)-'0';
					arr[i][j] = num;
					if (num==2) {
						startPointX = i;
						startPointY = j;
					} else if (num==3) {
						endPointX = i;
						endPointY = j;
					}
				}
			}


			/**
			 * logic
			 */
			
			int result = bfs(startPointX, startPointY);
			

			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> pos = new LinkedList<>();
		pos.add(new Integer[]{x,y});
		visit[x][y] = true;
		
		while (!pos.isEmpty()) {
			Integer[] poll = pos.poll();
			int posX = poll[0];
			int posY = poll[1];
			visit[posX][posY] = true;
			
			if (posX==endPointX && posY==endPointY) {
				return 1;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextX = posX + dir[i][0];
				int nextY = posY + dir[i][1];
				if (cango(nextX,nextY)) {
					pos.add(new Integer[]{nextX, nextY});
				}
			}
		}
		return 0;
	
	}

	private static boolean cango(int x, int y) {
		if(arr[x][y]==1) {
			return false;
		} else if (visit[x][y]) {
			return false;
		}
		return true;
	}

}

