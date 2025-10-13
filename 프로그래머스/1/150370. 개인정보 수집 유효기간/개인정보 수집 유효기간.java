/*
    파기해야할 개인정보의 번호를 return


*/
import java.util.*;

class Solution {
    Map<String, Integer> termsMap;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        termsMap = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            String[] strArr = terms[i].split(" ");
            termsMap.put(strArr[0], Integer.parseInt(strArr[1]));
        }
        String[] todayArr = today.split("\\.");
        int todayY = Integer.parseInt(todayArr[0]);
        int todayM = Integer.parseInt(todayArr[1]);
        int todayD = Integer.parseInt(todayArr[2]);
        
        List<Integer> answerList = new ArrayList<>();
        
        for(int i=0; i<privacies.length; i++){
            String[] strArr = privacies[i].split(" ");
            String[] privacyArr = strArr[0].split("\\.");
            int term = termsMap.get(strArr[1]);
            
            int privacyY = Integer.parseInt(privacyArr[0]);
            int privacyM = Integer.parseInt(privacyArr[1]);
            int privacyD = Integer.parseInt(privacyArr[2]);
            
            privacyM += term;
            
            while(privacyM > 12){
                privacyY++;
                privacyM -= 12;
            }
            if(isDelete(todayY, todayM, todayD, privacyY, privacyM, privacyD)){
                answerList.add(i+1);
            }
            
            
            
        }
        
        
        int[] answer = new int[answerList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    private boolean isDelete(int tY, int tM, int tD, int pY, int pM, int pD){
        if(tY > pY){
            return true;
        }else if(tY == pY){
            if(tM > pM){
                return true;
            }else if(tM == pM){
                if(tD >= pD){
                    return true;
                }
            }
        }
        return false;
    }
}