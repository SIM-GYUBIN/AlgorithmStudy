import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int DISH = Integer.parseInt(st.nextToken());
        int VARIABLE = Integer.parseInt(st.nextToken());
        int CONTINUITY = Integer.parseInt(st.nextToken());
        int COUPON = Integer.parseInt(st.nextToken());

        int[] sushi = new int[DISH];
        for (int cases = 0; cases < DISH; cases++) {
            st = new StringTokenizer(br.readLine());
            sushi[cases] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        int foo = 0;
        int ep = CONTINUITY;

        int[] dupArr = new int[VARIABLE + 1];
        int NoT = 0; //가짓수 개수

        List<Integer> meal = new ArrayList<>();
        
        //초기 리스트 설정
        for (int j = 0; j < CONTINUITY; j++) {
            meal.add(sushi[j]);
            //가짓수 +1
            if(dupArr[sushi[j]] == 0)
                NoT++;
            dupArr[sushi[j]]++;
        }


	//초기값의 쿠폰
	if(dupArr[COUPON] == 0) {
		foo++;
	}

	result = NoT + foo;

        //sp가 전체를 도는 loop
        for (int i = 1; i < DISH; i++) {
            foo = 0;

            //앞부분 제거 로직
            //중복배열에서 1빼고 (0일땐 안빼)
            //(중복이었을 때)연산 후 중복배열이 0
            if(dupArr[meal.get(0)] == 1) { //사라져버려서 가짓수 줄어듦
                NoT--;
            }
            dupArr[meal.get(0)]--;
            meal.remove(0);

            //뒤에 추가 로직(중복검사바로)
            meal.add(sushi[ep]);

            if(dupArr[sushi[ep]] == 0) {
        		NoT++;
            }
            dupArr[sushi[ep]]++;

            //쿠폰 레일에 있는지 확인
            //쿠폰 레일에 없음(중복x)
            if(dupArr[COUPON] == 0) {
                foo = 1;
            }


            if (result < NoT+foo) {
                result = NoT+foo;
            }


            //다음타임 세팅해주기
            if (ep == (DISH - 1)) {
                ep = 0;
            } else {
                ep++;
            }

        }


        System.out.println(result);
    }
}
