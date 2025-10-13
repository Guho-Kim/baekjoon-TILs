import java.util.*;
/*
    n명 사용자 m개 판매
    할인율
*/
class Solution {
    int[] discounts = {10, 20, 30, 40};
    int[][] users;
    int[] emoticons;
    int[] emoticonDiscount;
    int maxCost, maxCnt;
    
    public void backtrack(int idx){
        if(idx == emoticons.length){
            int totalCnt = 0;
            int totalCost = 0;
            for(int[] user : users){
                int rate = user[0];
                int limit = user[1];
                int price = 0;
                for(int i=0; i<emoticons.length; i++){
                    if(rate > emoticonDiscount[i]) continue;
                    price += emoticons[i]*(100-emoticonDiscount[i])/100;
                }
                if(limit <= price){
                    totalCnt++;
                }else{
                    totalCost += price;
                }
            }
            
            if(maxCnt < totalCnt){
                maxCnt = totalCnt;
                maxCost = totalCost;
            }else if(totalCnt == maxCnt){
                if(maxCost < totalCost){
                    maxCost = totalCost;
                }
            }
            
            return;
        }
        
        for(int discount : discounts){
            emoticonDiscount[idx] = discount;
            backtrack(idx+1);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        int subscriberCnt = 0;
        int userSize = users.length;
        this.users=users;
        this.emoticons = emoticons;
        maxCnt = maxCost = 0;
        emoticonDiscount = new int[emoticons.length];
        backtrack(0);
  
        
        int[] answer = {maxCnt, maxCost};
        return answer;
    }
}