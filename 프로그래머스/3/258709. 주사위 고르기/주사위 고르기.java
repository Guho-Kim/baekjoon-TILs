import java.util.*;
class Solution {
    int n;
    int[] sequence;
    int[] sequence2;
    int maxWinCnt;
    int[] answer;
    int[][] dice;
    int maxCombCnt;
    
    int combIdx;
    // dfs
    public void makeComb(int[] comb, int depth, int sum, int[] sequence){
        if(depth == n/2){
            comb[combIdx++] = sum;
            return ;
        }
        
        for(int i=0; i<6; i++){
            int diceNum = sequence[depth];
            makeComb(comb, depth+1, sum+dice[diceNum][i], sequence);
        }
        
    }
    
    public void backtrack(int selectedIdx, int selectedCnt){
        // terminate
        if(selectedCnt == n/2){
            // 각각 6^(n/2) 개씩의 숫자 조합 발생
            // n == 4
            // 각각 36개씩의 숫자 조합 발생
            // 36*36 가지의 승부 경우의 수 존재.
            int[] combA = new int[maxCombCnt];
            int[] combB = new int[maxCombCnt];
            int seqIdx = 0;
            int tempIdx = 0;
            for(int i=0; i<n; i++){
                if(seqIdx < n/2 && i==sequence[seqIdx]){
                    seqIdx++;
                    continue;
                }
                if(tempIdx <n/2){
                    sequence2[tempIdx++] = i;
                    
                }
            }
            combIdx = 0;
            makeComb(combA, 0, 0, sequence);
            // for(int i=0; i<combA.length; i++){
            //     System.out.print(combA[i] + " ");
            // }
            // System.out.println();

            combIdx = 0;
            makeComb(combB, 0, 0,sequence2);
            // for(int i=0; i<combB.length; i++){
            //     System.out.print(combB[i] + " ");
            // }
            // System.out.println();

            // ==================================
            // 비즈니스 로직
            // ==================================
            int winCnt = 0;
            int loseCnt = 0;
            // System.out.println("winCnt: "+ winCnt);
            for(int i=0; i<combA.length; i++){
                for(int j=0; j<combB.length; j++){
                    if(combA[i]>combB[j]){
                        winCnt++;
                    }else if(combA[i]<combB[j]){
                        loseCnt++;
                    }
                }
            }
            if(maxWinCnt < winCnt){
                maxWinCnt = winCnt;
                for(int i=0; i<n/2; i++){
                    answer[i] = sequence[i]+1;
                    // System.out.println(sequence[i]);
                }
            }
            
            if(maxWinCnt < loseCnt){
                maxWinCnt = loseCnt;
                for(int i=0; i<n/2; i++){
                    answer[i] = sequence2[i]+1;
                    // System.out.println(sequence[i]);
                }
            }
            
            return;
        }
        
        // generate new branch
        for(int i=selectedIdx; i<n; i++){
            sequence[selectedCnt] = i;
            backtrack(i+1, selectedCnt+1);
        }
    }
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        maxWinCnt=0;
        sequence = new int[n/2];
        sequence2 = new int[n/2];
        answer = new int[n/2];
        
        
        
        maxCombCnt = 1;
        for(int i=0; i<n/2; i++){
            maxCombCnt*=6;
        }
        sequence[0] = 0;
        backtrack(1, 0);
        
        
        return answer;
    }
}