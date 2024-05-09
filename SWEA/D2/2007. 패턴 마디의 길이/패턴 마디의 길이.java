import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for (int caseNum = 1; caseNum <T+1; caseNum++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			

			int length = 0;
			for (int j=1; j<=10; j++) {	//반복 길이
				String line1 = "";
				String line2 = "";
				for (int k = 0; k < j; k++) { 
					line1 += line.charAt(k);
					line2 += line.charAt(j+k);
				}
				
				if (line1.equals(line2)) {
					length = j;
					break;
				}
			}
			
			sb.append("#").append(caseNum).append(" ").append(length).append("\n");
		}
		System.out.print(sb);
		
	}
}
