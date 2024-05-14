import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
//	static int N;
	static int R;
	static int C;
	static char[][] lang;
	static boolean visited[][][][];
	static boolean isEnd;
	
	static class Info {
		int x, y, dir, mem; 
		
		 public Info(int x, int y, int dir, int mem) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.mem = mem;
		}
	}

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			lang = new char[R][C];
			
			boolean hasAnotation = false;
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					char inputChar = line.charAt(j);
					lang[i][j] = inputChar;  
					if (inputChar=='@')
						hasAnotation = true;
				}
			}
			
			visited = new boolean[R][C][4][16];	//x, y, 방향, 메모리

			isEnd = false;
			if (hasAnotation) {
				bfs();
			}
			
			String result = isEnd ? "YES" : "NO";

			sb.append("#").append(caseNum).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}


	private static void bfs() {
		
		Queue<Info> myQ = new LinkedList<>();
		myQ.add(new Info(0, 0, 0, 0));
		
		
		/**
		 * 0 오른쪽, 1 왼쪽, 2 아래, 3 위
		 */
		int ex =0;
		int ey = 0;
		while (!myQ.isEmpty()) {
			Info info = myQ.poll();
			ex=info.x;
			ey=info.y;

			
			if (visited[info.x][info.y][info.dir][info.mem]) {
				continue;
			} else {
				visited[info.x][info.y][info.dir][info.mem] = true;
			}
			
			int nextX = info.x;
			int nextY = info.y;
			int newDir = info.dir;
			int newMem = info.mem;
			
			switch (lang[info.x][info.y]) {
			case '<':
				newDir = 1;
				break;
			case '>':
				newDir = 0;
				break;
			case '^':
				newDir = 3;
				break;
			case 'v':
				newDir = 2;
				break;
			case '_':
				if (info.mem==0)
					newDir = 0;
				else
					newDir = 1;
				
				break;
			case '|':
				if (info.mem==0)
					newDir = 2;
				else
					newDir = 3;
				
				break;
			case '?':
				for (int i = 0; i < 4; i++) {
					newDir = i;
					nextX = whatsNextX(info.x, newDir);
					nextY = whatsNextY(info.y, newDir);
					myQ.add(new Info(nextX, nextY, newDir, newMem));
				}
				continue;
			case '.':
				
				break;
			case '@':
				isEnd = true;
				break;

			case '+':
				if (info.mem == 15)
					newMem = 0;
				else
					newMem += 1;

				break;
			case '-':
				if (info.mem == 0)
					newMem = 15;
				else
					newMem -= 1;
				
				break;
				
			default:
				int num = lang[info.x][info.y] - '0';
				newMem = num;

				break;
			}
			nextX = whatsNextX(nextX, newDir);
			nextY = whatsNextY(nextY, newDir);
			myQ.add(new Info(nextX, nextY, newDir, newMem));
		}
	}
	private static int whatsNextX(int x, int direction) {
		switch (direction) {
		case 3:	//상
			if (x==0) {
				return R-1;
			}
			return x-1;
		case 2:	//하
			if (x==R-1) {
				return 0;
			}
			return x+1;
		}
		return x;
	};
	
	private static int whatsNextY(int y, int direction) {
		switch (direction) {
		case 1:	//좌
			if (y==0) {
				return C-1;
			}
			return y-1;
		case 0:	//우
			if (y==C-1) {
				return 0;
			}
			return y+1;
		}
		return y;
		
	};
}


