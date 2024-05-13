import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int K;
	static int[] customer;

	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			customer = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				customer[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(customer);
			
			/**
			 * logic
			 */
			boolean isPossible = true;
			int custIdx = 0;
			int stock = 0;
			for (int sec = M; custIdx < N; sec=sec+M) {
				
				// 손님을 지나치지 않았나 검사(여러명일 수도) 지나쳣다면 빵을 줄수 있었나 검사
				int custNum = 0;
				for (int i = custIdx; i < N; i++) {
					if (customer[i]<sec) {
						custNum++;
						custIdx++;
					} else {
						break;
					}
				}
				if(stock<custNum) {
					isPossible = false;
					break;
				}
				stock = stock - custNum;
				
				//중간에 끊어주기 혹시나 custIdx가 여기서 초과
				if(custIdx > N-1) {
					break;
				}
					
					
				//정시에 온사람
				stock += K;
				for (int i = custIdx; i < N; i++) {
					if (customer[i] == sec) {
						stock--;
						custIdx++;
						if (stock<0) {
							isPossible = false;
						}
					} else {
						break;
					}
				}
			}
			
			String result = isPossible ? "Possible" : "Impossible";
			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}


}

