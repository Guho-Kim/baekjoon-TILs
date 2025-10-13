import java.util.*;
/*
    처음 위치 : 0분 0초
    기능
        1. 10초 전으로 이동
            prev
                -> 10초 미만일 경우 처음 위치로 이동
        2. 10초 후로 이동
            next
                -> 10초 후로 이동. 남은 시간이 10초 미만일 경우 마지막 위치로 이동.
        3. 오프닝 건너뛰기
            op_start <= 현재 재생 위치 <= op_end 인 경우 자동으로 오프닝이 끝나는 위치로 이동


    String video_len    : 동영상 길이
    String pos,         : 기능 수행 직전의 재생 위치
    String op_start,    : 오프닝 시작 시각
    String op_end,      : 오프닝 끝나는 시각
    String[] commands   : 사용자 입력 = "mm:ss"
    
    출력
        모든 명령어 이후의 위치
*/

class Solution {
    int posSec;
    int opStartSec;
    int opEndSec;
    int videoSec;
    
    int getSec(String time){
        int mm = Integer.parseInt(time.split(":")[0]);
        int ss = Integer.parseInt(time.split(":")[1]);
        return mm*60+ss;
    }
    
    String getStringTime(int sec){
        int mm = sec/60;
        int ss = sec%60;
        return String.format("%02d:%02d", mm, ss);
    }
    
    void init(String pos, String op_start, String op_end, String video_len){
        posSec = getSec(pos);
        opStartSec = getSec(op_start);
        opEndSec = getSec(op_end);
        videoSec = getSec(video_len);
    }
    
    void skipOpenning(){
        if(opStartSec <= posSec && posSec <= opEndSec){
            posSec = opEndSec;
        }
    }
    
    void prevVideo(){
        if(posSec < 10){
            posSec = 0;
        }else{
            posSec -= 10;
        }
    }
    
    void nextVideo(){
        if(posSec+10 > videoSec){
            posSec = videoSec;
        }else{
            posSec += 10;
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        init(pos, op_start, op_end, video_len);
        skipOpenning();
        for(String command : commands){
            if("next".equals(command)){
                nextVideo();
            }else if("prev".equals(command)){
                prevVideo();
            }            
            skipOpenning();
        }
        
        
        String answer = getStringTime(posSec);
        return answer;
    }
}