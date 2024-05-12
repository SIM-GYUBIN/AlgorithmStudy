import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static double[][] arr;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	

		int T = Integer.parseInt(br.readLine());
		for (int caseNum = 1; caseNum <T+1; caseNum++) {

			String line = br.readLine();
//			line = line.trim();

			int left = 0;
			int right = line.length()-1;
			boolean isPal = true;
			while (left < right) {
				if(line.charAt(left)!=line.charAt(right)) {
					isPal = false;
					break;
				}
				left++;
				right--;
			}

			int result = isPal==true ? 1 : 0;


			sb.append("#").append(caseNum).append(" ").append(result).append("\n");

		}
		System.out.print(sb);
	}



}

