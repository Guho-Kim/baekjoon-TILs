/*
    1. 두 사람이 선물을 "주고받음"
        -> 두 사람 사이에 더 많은 선물을 준 사람 : 다음 달에 선물 하나 받음
    2. 두 사람이 선물을 "안 주고 받음" or "동일하게 주고받음"
        -> 선물 지수가 더 큰 사람이 적은 사람에게 선물을 하나 받음
        2-1. 선물 지수까지 동일하면 다음 달에 선물 주고받지 않음
    
    return
        선물을 가장 많이 받을 친구가 받을 선물의 수
        
    [용어]
        선물 지수 : 이번 달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수

*/
import java.util.*;
class Solution {
    int totalCnt;
    Map<String, Integer> hashMap;
    

    public int solution(String[] friends, String[] gifts) {
        // =======================================================
        // 초기화
        // =======================================================
        this.totalCnt = 0;
        this.hashMap = new HashMap<>();
        int friendCnt = 0;
        int[][] giftCntArr = new int[friends.length][friends.length];
        int[] giftPoint = new int[friends.length];
        
        for(int i=0; i<friends.length; i++){
            hashMap.put(friends[i], friendCnt++);
        }
        for(int i=0; i<gifts.length; i++){
            String[] strArr = gifts[i].split(" ");
            int src = hashMap.get(strArr[0]);
            int dest = hashMap.get(strArr[1]);
            
            giftCntArr[src][dest]++;
            giftPoint[src]++;
            giftPoint[dest]--;
            
        }
        int[] expGiftCnt = new int[friends.length];
        // =======================================================
        // 비즈니스 로직(다음달 누가 선물 가장 많이 받을까?)
        // =======================================================
        for(int i=0; i<friends.length-1; i++){
            for(int j=i+1; j<friends.length; j++){
                // 1. 두 사람이 선물을 "주고받음"
                //  -> 두 사람 사이에 더 많은 선물을 준 사람 : 다음 달에 선물 하나 받음 
                if(giftCntArr[i][j] > giftCntArr[j][i]){
                    expGiftCnt[i]++;
                }else if(giftCntArr[i][j] < giftCntArr[j][i]){
                    expGiftCnt[j]++;
                }else{
                     // 2. 두 사람이 선물을 "안 주고 받음" or "동일하게 주고받음"
                     //    -> 선물 지수가 더 큰 사람이 적은 사람에게 선물을 하나 받음
                     //    2-1. 선물 지수까지 동일하면 다음 달에 선물 주고받지 않음
                    if(giftPoint[i] > giftPoint[j]){
                        expGiftCnt[i]++;
                    }else if(giftPoint[i] < giftPoint[j]){
                        expGiftCnt[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<expGiftCnt.length; i++){
            answer = answer < expGiftCnt[i] ? expGiftCnt[i] : answer;
        }
        
        
        return answer;
    }
}