import java.util.*;
/*
    제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 return

*/

class Solution {
    int level;
    int maxDiff;
    long totalTime;
    int puzzleSize;
    
    void init(int[] diffs){
        level = 1;
        maxDiff = 0;
        puzzleSize = diffs.length;
        for(int i=0; i<diffs.length; i++){
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        totalTime=0;
    }
    
    long getTime(int diff, int time_cur, int time_prev, int level){
        if(diff > level){
            return (long)(diff - level)*(time_cur+time_prev) + time_cur;
        }else{
            return time_cur;
        }
    }
    
    long playPuzzle(int[] diffs, int[] times, int level){
        // long puzzleTime = getTime(diffs[0], times[0], 1, level);
        long puzzleTime = times[0];
        for(int i=1; i<puzzleSize; i++){
            puzzleTime += getTime(diffs[i], times[i], times[i-1], level);
        }
        return puzzleTime;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        init(diffs);
        int low = 1;
        int high = 100000;
        
        while(low <= high){
            int mid = (low+high)/2;
            long puzzleTime = playPuzzle(diffs, times, mid);
            
            // 값이 너무 큽니다. 더 높은 level이 필요해요.
            if(puzzleTime > limit){
                low = mid+1;
            }else{
                high = mid-1;
                level = mid;
            }
        }
            
        // long puzzleTime = playPuzzle(diffs, times, level);
        // while(puzzleTime > limit){
        //     puzzleTime = playPuzzle(diffs, times, ++level);
        // }
        
        int answer = level;
        return answer;
    }
}