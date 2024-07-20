import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> noSeeHear = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), 0);
        }

        for (int i = 0; i < M; i++) {
            String line = br.readLine();

            Integer def = map.getOrDefault(line, 1);
            if(def==0) {    //중복이면
                noSeeHear.add(line);
            }
        }

        noSeeHear.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(noSeeHear.size());
        for (int i = 0; i < noSeeHear.size(); i++) {
            System.out.println(noSeeHear.get(i));
        }
    }
}
