import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int N, maxNum;
	static String[] word;
	static char[] alphas;
	static int[] alphaNum;
	static int[][] rstNums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();


		N = Integer.parseInt(br.readLine());

		word = new String[N];

		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();
		}

		List<Character> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < word[i].length(); j++) {
				char a = word[i].charAt(j);
				if (!list.contains(a)) {
					list.add(a);
				}
			}
		}

		alphas = new char[list.size()];
		alphaNum = new int[list.size()];
		for (int i = 0; i < alphas.length; i++) {
			alphas[i] = list.get(i);
		}

		rstNums = new int[N][];
		for (int i = 0; i < N; i++) {
			rstNums[i] = new int[word[i].length()];
		}



		//logic
		maxNum = 0;
		func(0, new boolean[10]);


		System.out.println(maxNum);



	}

	private static void func(int flr, boolean[] visit) {	//10개중 length만큼 뽑기

		if (flr == alphas.length) {
//			System.out.println(Arrays.toString(alphaNum));
			parse();
//			System.out.println(Arrays.deepToString(rstNums));
			calculate();
			return;
		}


		for (int i = 0; i < 10; i++) {
			if (!visit[i]) {
				visit[i] = true;
				alphaNum[flr] = i;
				func(flr+1, visit);
				alphaNum[flr] = 0;
				visit[i] = false;
			}
		}
	}

	private static void calculate() {
		int sum = 0;
		for (int i = 0; i < rstNums.length; i++) {	//한 단어
			int tmpSum = 0;
			int flg = 1;
			for (int j = rstNums[i].length-1; j >= 0; j--) {
				tmpSum += rstNums[i][j]*flg;
				flg *= 10;
			}
			sum += tmpSum;
		}
		maxNum = Math.max(maxNum, sum);
	}

	private static void parse() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < rstNums[i].length; j++) {
				char picked = word[i].charAt(j);

				for (int w = 0; w < alphas.length; w++) {
					if (picked==alphas[w]) {
						rstNums[i][j] = alphaNum[w];
					}
				}
			}
		}
	}
}










