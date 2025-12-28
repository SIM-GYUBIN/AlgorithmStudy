class Solution {
    boolean[] dayArr = new boolean[8];
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = schedules.length;
        int sat = 6- startday;
        if (startday==7) {sat=6;}
        int sun = 7- startday;
        
        
        outer:
        for (int man=0; man<schedules.length; man++) {
            dayArr = new boolean[8];
            System.out.println(man+"번남 돌립니다");
            int rule = calTime(schedules[man] + 10);
            for (int time=0; time<7; time++) {
                dayArr[time] = true;
                if (time==sat || time==sun) {continue;}
                System.out.println(man+"번남"+time+" 요일에"+ rule+" 그리고 "+ timelogs[man][time]);
                if (rule < timelogs[man][time]) {
                    answer--;
                    System.out.println(man+"번남"+ rule+"을 안지키고 "+ timelogs[man][time] +" 에 왔습니다.");
                    continue outer;
                }
            }
        }
        
        
        return answer;
    }
    
    public int calTime(int time) {
        int ten = time%100;
        if (ten>=60) {
            time += 100;
            time -= 60;
        }
        return time;
    }
}