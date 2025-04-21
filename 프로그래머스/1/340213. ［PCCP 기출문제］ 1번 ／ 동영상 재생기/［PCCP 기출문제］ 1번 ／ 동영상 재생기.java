class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        // System.out.println(pos.split(":")[0]);
        int viLenNum = stringToNum(video_len);
        int posNum = stringToNum(pos);
        int opSNum = stringToNum(op_start);
        int opENum = stringToNum(op_end);
        
        for (int i=0; i<commands.length; i++) {
            String command = commands[i];
            
            // 오프닝 건뛰
            if (opSNum <= posNum && posNum <= opENum) {
                posNum = opENum; 
            } 
            
            // 팀 
            if (command.equals("next")) {
                if (viLenNum-posNum < 10) {
                    posNum = viLenNum;
                } else {
                    posNum += 10;
                }
            } else if (command.equals("prev")) {
                if (posNum < 10) {
                    posNum = 0;
                } else {
                    posNum -= 10;
                }
            }
            
            // 오프닝 건뛰
            if (opSNum <= posNum && posNum <= opENum) {
                posNum = opENum; 
            } 
        }
        
        String answer = numToString(posNum);
        return answer;
    }
    
    private int stringToNum(String time) {
        int bun = Integer.parseInt(time.split(":")[0]);
        int cho = Integer.parseInt(time.split(":")[1]);
        
        return bun*60 + cho;
    }
    
    private String numToString(int time) {
        String bun = String.valueOf(time/60);
        String cho = String.valueOf(time%60);
        
        if (bun.length()==1) {bun = "0" + bun;}
        if (cho.length()==1) {cho = "0" + cho;}
        
        return bun +":"+cho;
    }
}