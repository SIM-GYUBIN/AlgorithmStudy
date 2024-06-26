import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        /**
         * R 함수 투포인터로 뒤집어도 시간초과
         * D 함수에서 매번 실제로 자르면 시간초과 발생
         *
         *
         * 걸렸던 테스트 케이스
         * n이 0인 경우
         *
         * D가 에러를 내뿜는지 않는지
         * n이 1이거나 0이거나 할 경우에 , R이랑 섞여서 나오는 경우
         *
         * https://www.acmicpc.net/board/view/140389 <= 반례 모음 없이는 못풀었을듯 ㅜ
         */

        StringBuilder resultsb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String line = br.readLine();
            char[] charArray = line.toCharArray();

            int n = Integer.parseInt(br.readLine());
            int[] intArray = new int[n];

            String line1 = br.readLine();
            String substring = line1.substring(1, line1.length() - 1);
            StringTokenizer st = new StringTokenizer(substring, ",");
            for (int j = 0; j < n; j++) {
                String nums = st.nextToken();
                intArray[j] = Integer.parseInt(nums);
            }


            boolean errorFlag = false;
            boolean reversed = false;
            int startIndex = 0;
            int endIndex = n;
            for (char func:charArray) {
                if (Objects.equals(func, 'R')) {    //뒤집기
                    if (!reversed) {
                        int tmp = startIndex-1;
                        startIndex = endIndex-1;
                        endIndex = tmp;
                        reversed = !reversed;
                    } else {
                        int tmp = startIndex+1;
                        startIndex = endIndex+1;
                        endIndex = tmp;
                        reversed = !reversed;
                    }


                } else if (Objects.equals(func, 'D')) {
                    if (!reversed) {
                        if (startIndex >= endIndex || intArray.length==0) {
                            errorFlag = true;
                            break;
                        }
                    } else {
                        if (startIndex <= endIndex || intArray.length==0) {
                            errorFlag = true;
                            break;
                        }
                    }

                    if (reversed) {
                        startIndex--;
                    } else {
                        startIndex++;
                    }
                }
            }
            if (errorFlag) {
                resultsb.append("error").append("\n");
            } else {
                String result = "";
                if (!reversed) {

                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (int idx = startIndex; idx < endIndex; idx++) {
                        sb.append(intArray[idx]);
                        if (idx != endIndex-1)  sb.append(",");
                    }
                    sb.append("]");
                    result = String.valueOf(sb);

                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (int idx = startIndex; idx > endIndex; idx--) {
                        sb.append(intArray[idx]);
                        if (idx != endIndex+1)  sb.append(",");
                    }
                    sb.append("]");
                    result = String.valueOf(sb);
                }
                result = result.replace(" ", "");
                resultsb.append(result).append("\n");
            }
        }
        System.out.println(resultsb);
    }
}
