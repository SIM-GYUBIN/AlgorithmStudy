import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class Solution {
	static int N;
	static double[][] arr;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			arr = new double[N][2];

			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int mid = Integer.parseInt(st.nextToken());
				int fin = Integer.parseInt(st.nextToken());
				int work = Integer.parseInt(st.nextToken());
				
				double score = 0.35*mid + 0.45*fin + 0.2*work;

				arr[i][1] = score; 
				arr[i][0] = i+1;
			}

			Arrays.sort(arr, new Comparator<double[]>() {
				@Override
				public int compare(double[] o1, double[] o2) {
					int num = -1;
					if (o1[1] - o2[1] < 0) {
						num = 1;
					}
					return num;
				}
			});
			int rank=0;
			for (int i = 0; i < N; i++) {
				if (arr[i][0] == K) {
					rank = i+1;
				}
			}
			
			
			int flag = N/10;
			
			String[] ranks= {"A+","A0","A-","B+","B0","B-","C+","C0","C-","D0"};
			
			int index = (rank-1)/flag;
			String result = ranks[index];


			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}



}

