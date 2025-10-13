/*
    0. 카드 n/3장 가짐. 교환 가능 동전 coin개 가짐
    1. 카드 순서대로 2장 뽑음.
        1-1. 카드뭉치가 비면 게임 종료
        1-2. 뽑은 카드는 카드당 동전 하나 소모해서 가지거나, 동전 소모하지 않고 버릴 수 있음
    2. 카드 수의 합이 n+1 되도록 두 장을 내고 다음 라운드로 진행 가능.
        2-1. 만약 불가능하면 게임 종료


    ex) n=18
    
    시작 카드 : 6장
    6장 -> 8장 -> 10장 -> 12장 ...
    R1  -> R2  -> R3  -> R4 ...
    
    n/3 + 2m 장을 가지고 가능한가?
        -> m+1 라운드까지 가능
    
    [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]
    R0 : 3 6 7 2
    R1 : 3 2 | 1 10
    R2 : 2 | 1 5 9
    R3 : 2 | 1 9 12
    R4 : | 1 9 12 4
    
*/
import java.util.*;
class Solution {
    int n;
    public int solution(int coin, int[] cards) {
        Set<Integer> cardSet = new HashSet<>();
        Set<Integer> additionalCardSet = new HashSet<>();
        n = cards.length;
        for(int i=0; i<n/3; i++){
            cardSet.add(cards[i]);
        }
        
        
        int round = 0;
        
        int idx = n/3;
        while(true){
            round++;
            if(idx==n){
                break;
            }
            // 카드 2장 선택
            additionalCardSet.add(cards[idx++]);
            additionalCardSet.add(cards[idx++]);
            
            // original 안에서 해결 가능한가요?
            if(isOriginalOnly(cardSet)){
                continue;
            }
            
            // 새로운 카드와 가능한가요?
            if(coin > 0 && isOriginalAndAdditional(cardSet, additionalCardSet)){
                coin--;
                continue;
            }
            
            // 새로운 카드에서 가능한가요?
            if(coin > 1 && isAdditionalOnly(additionalCardSet)){
                coin-=2;
                continue;
            }
            
            break;
        }
        
        int answer = round;
        return answer;
    }
    public boolean isOriginalOnly(Set<Integer> cardSet){
        for(int number : cardSet){
            if(cardSet.contains(n+1-number)){
                cardSet.remove(number);
                cardSet.remove(n+1-number);
                return true;
            }
        }
        return false;
    }

    public boolean isOriginalAndAdditional(Set<Integer> cardSet, Set<Integer> additionalCardSet){
        for(int number : cardSet){
            if(additionalCardSet.contains(n+1-number)){
                cardSet.remove(number);
                additionalCardSet.remove(n+1-number);
                return true;
            }
        }
        return false;
    }

    public boolean isAdditionalOnly(Set<Integer> additionalCardSet){
        for(int number : additionalCardSet){
            if(additionalCardSet.contains(n+1-number)){
                additionalCardSet.remove(number);
                additionalCardSet.remove(n+1-number);
                return true;
            }
        }
        return false;
    }

}