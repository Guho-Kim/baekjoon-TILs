/*
    / or \ or |

    n = 4
    0 1 0 1 0 0 0 1 0
    0 1 0 1 0 1 0 1 0
    1 0 1 0 1 0 1 0 1
    / or \ 이걸로 먼저 해야함.
        -> | 가능한 곳에 경우의수 2의 거즙제곱 추가됨

    {L, R, ㅁ}
    n개 중 위 3 개를 서로 다르게 선택하는 경우의 수는 직접 구현해야함.
    0,0,n
    0,1,n-1
    ...
    n,0,0
    
    // dp로 풀어봅시다..!
    
*/


import java.util.*;
class Solution {
    int totalCnt;
    int n;
    int[][] dp;
    
    public int solution(int n, int[] tops) {
        this.n = n;
        dp = new int[n][3];
        
        totalCnt=0;
        dp[0][0]=1;
        dp[0][1]=1;
        if(tops[0]==0){
            dp[0][2]=1;
        }else{
            dp[0][2]=2;
        }
            
        for(int i=1; i<n; i++){
            // dp[i][0] : 현재 left이고 i번째까지 고려했을 때, 경우의 수
            dp[i][0] = dp[i-1][0]+dp[i-1][2];
            
            // dp[i][1] : 현재 right이고 i번째까지 고려했을 때, 경우의 수
            dp[i][1] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2];
            
            // dp[i][1] : 현재 정삼각형이고 i번째까지 고려했을 때, 경우의 수
            dp[i][2] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2];
            
            if(tops[i]==1){
                dp[i][2]*=2;
            }
            
            for(int j=0; j<3; j++) dp[i][j]%=10007;
                
        }
        
        int answer = (dp[n-1][0]+dp[n-1][1]+dp[n-1][2])%10007;
        return answer;
    }
}