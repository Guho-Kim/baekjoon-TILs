/*
                    in      |    out
    생성             0            >1
    도넛(모든)       >=1            1     
    막대(마지막)     >=1            0
    8자(가운데)     >=2             2
*/

import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        // =================================
        // 초기화
        // =================================
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int[] answer = new int[4];
        
        for(int[] edge : edges){
            out.put(edge[0], out.getOrDefault(edge[0], 0)+1);
            in.put(edge[1], in.getOrDefault(edge[1], 0)+1);
            
            set.add(edge[0]);
            set.add(edge[1]);
        }
        // =================================
        // 비즈 로직
        // =================================
        //                 in      |    out
        // 생성             0            >1
        // 도넛(모든)       >=1            1     
        // 막대(마지막)     >=1            0
        // 8자(가운데)     >=2             2
        for(int node : set){
            int inCnt = in.getOrDefault(node, 0);
            int outCnt = out.getOrDefault(node, 0);
            if(inCnt==0 && outCnt>1){
                answer[0] = node;
            }
            if(inCnt>=1 && outCnt == 0){
                answer[2]++;
            }
            if(inCnt>=2 && outCnt == 2){
                answer[3]++;
            }
            
        }
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}