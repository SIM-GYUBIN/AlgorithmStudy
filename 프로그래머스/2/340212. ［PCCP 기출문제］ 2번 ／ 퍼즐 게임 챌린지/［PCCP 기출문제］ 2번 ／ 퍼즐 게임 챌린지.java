class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int maxDiff = 1;
        for (int i =0; i<diffs.length; i++) {
            if (diffs[i]>maxDiff) {maxDiff = diffs[i];}
        }
        
        int minL = 1;
        int maxL = maxDiff;
        int level = maxDiff/2 + 1;
        int minTime = Integer.MAX_VALUE;
        
        // getTime 돌려서 limit 넘으면 level 올리기
        // getTime 돌려서 limit 충족하면 level 낮추기
        int result = Integer.MAX_VALUE;
        do {
            long rst = getTime(level, diffs, times);
            if (rst > limit) { // 미충족
                minL = level+1;
                level = (minL+maxL)/2;
            } else { // 충족
                if (level<result) {result=level;}
                maxL = level-1;
                level = (minL+maxL)/2;
            }
        } while (minL <= maxL);
        // } while (false);
        
        
        return result;
    }
    
    private long getTime (int level, int[] diffs, int[] times) {  
        long turnTime = 0;
        int t = diffs.length;
        for (int i = 0; i < t; i++) {
            if (level >= diffs[i]) {          
                turnTime += times[i];
            } else {
                turnTime += (times[i]+times[i-1]) * (diffs[i] - level) + times[i];
            }
        } 
        return turnTime;
    }
}