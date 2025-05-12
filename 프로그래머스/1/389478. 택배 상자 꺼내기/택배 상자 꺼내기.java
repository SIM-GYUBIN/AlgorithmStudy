import java.util.PriorityQueue;
// 문제는 몇 개를 꺼내냐임
// 몇층인지 알면 되지 않나
// 마지막거 몇층이고
// 대상이 몇층이고
// 마지막거 위치와 대상의 위치가 겹치는가 아닌가

// 택배 상자의 개수를 나타내는 정수 n, 가로로 놓는 상자의 개수를 나타내는 정수 w와 꺼내려는 택배 상자의 번호를 나타내는 정수 num
class Solution {
    public int solution(int n, int w, int num) {
        // PriorityQueue<Integer> pQ = new PriorityQueue<>();
        int answer = 0;
        
        int lastBoxFloor = (n-1)/w +1;
        int targetBoxFloor = (num-1)/w +1;
        
        boolean isDup = false;
        if (lastBoxFloor > targetBoxFloor) {
            // 나눠서 홀수면 우측에서 오는거임
            boolean Lleft = ((n-1)/w)%2==0 ? true : false; 
            int Ldiff = (n%w)==0 ? w : (n%w);
            
            boolean Nleft = ((num-1)/w)%2==0 ? true : false; 
            int Ndiff = (num%w)==0 ? w : (num%w);
            
            if (Lleft==Nleft) {
                if (Ldiff >= Ndiff) isDup = true;
            } else {
                if ((Ldiff+Ndiff)>w) isDup = true;
            }
        }
        answer = lastBoxFloor - targetBoxFloor;
        if (isDup || lastBoxFloor == targetBoxFloor) answer++;
        
        return answer;
    }
}