import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static int N,M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<Integer, String> pokemonFindName = new HashMap<>();
        Map<String, Integer> pokemonFindNum = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            pokemonFindName.put(i, line);
            pokemonFindNum.put(line, i);
        }

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            if (isNumber(line)) {
                sb.append(pokemonFindName.get(Integer.parseInt(line))).append("\n");
            } else {
                sb.append(pokemonFindNum.get(line)).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean isNumber(String line) {
        boolean isNumber = false;
        for (int i = 0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))) {
                isNumber = true;
            } else {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

}