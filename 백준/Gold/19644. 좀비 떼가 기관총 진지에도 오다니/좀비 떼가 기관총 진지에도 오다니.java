import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int LENGTH;
    static int RANGE;
    static int DAMAGE;
    static int CAMMO;
    static Queue<Integer> zombies;
    static Queue<int[]> shotRange;  //0 : hp , 1: index
    static Queue<Integer> bombCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Boolean isSolved = true;
        
        LENGTH = Integer.parseInt(br.readLine());   //맵 길이
        StringTokenizer st = new StringTokenizer(br.readLine());
        RANGE = Integer.parseInt(st.nextToken());   //기관총 사거리
        DAMAGE = Integer.parseInt(st.nextToken());  //기관총 데미지
        CAMMO = Integer.parseInt(br.readLine());    //지뢰 개수

        int putZombieIndex = RANGE+1;

        zombies = new LinkedList<>(); //좀비 체력
        for (int i = 0; i < LENGTH; i++) {
            zombies.add(Integer.parseInt(br.readLine()));
        }

        /**
         * 로직
         * 바로 앞에 쏴서 죽으면 기관총
         * 바로 앞에 쏴도 안죽으면 지뢰
         * 난 한칸씩 앞으로 가면서
         */

        shotRange = new LinkedList<>();
        bombCnt = new LinkedList<>();

        //shotRange 초기 설정
        for (int i = 1; i < RANGE+1; i++) {
            putZombie(i);
        }


        //돌아가는 로직
        for (int i = 1; i < LENGTH+1; i++) {
            int polledZombieIndex = -1;

            if (calcHp().equals("gun")) {
                //기관총 쏴
                polledZombieIndex = shotRange.poll()[1];
            } else if(calcHp().equals("bomb")) {
                //수류탄 쏴
                polledZombieIndex = shotRange.poll()[1];
                bombCnt.add(putZombieIndex-1);
                CAMMO--;
            } else if (calcHp().equals("fail")){                //항복해
                isSolved = false;
                break;
            } else if (calcHp().equals("error")){
                System.out.println("에러");
                break;
            }

            //좀비 집어 넣기
            putZombie(putZombieIndex++);
            //폭탄맞은 좀비 계산
            if (polledZombieIndex == (bombCnt.peek()==null ? 0 : bombCnt.peek())){
                bombCnt.poll();
            }
        }

        System.out.println(isSolved?"YES":"NO");


    }

    private static void putZombie(int index) {  //0 : hp , 1: index
        int[] inputZombie = {(zombies.peek() == null ? 0 : zombies.poll()), index};
        shotRange.add(inputZombie);
    }

    public static String calcHp() {
        int indexGap = shotRange.peek()[1];
        if (indexGap > RANGE) {
            indexGap = RANGE;
        }

        int damage = (DAMAGE * indexGap) - (DAMAGE * bombCnt.size());

        if (damage >= shotRange.peek()[0])
            return "gun";
        else if (damage < shotRange.peek()[0] && CAMMO>0)
            return "bomb";
        else if (damage < shotRange.peek()[0] && CAMMO<=0)
            return "fail";

        return "error";
    }
}
