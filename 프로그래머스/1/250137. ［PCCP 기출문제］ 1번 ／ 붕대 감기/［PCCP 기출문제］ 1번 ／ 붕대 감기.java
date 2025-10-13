/*
    t초동안 붕대 -> x*t 체력 회복
        -> t초 연속 성공 -> y 체력 회복

    bandage[] : 시전 시간, 초당 회복량, 추가 회복량
*/

class Solution {
    int t, x, y;
    int maxHealth;
    int health;
    int[][] attacks;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        t=bandage[0];   // 시전 시간
        x=bandage[1];   // 초당 회복량
        y=bandage[2];   // 추가 회복량
        this.health = health;   // 체력
        this.maxHealth = health;
        this.attacks = attacks; // 공격 시간, 피해량
        int time = 0;
        int healthTime = 0;
        for(int[] attack : attacks){
            int attackTime = attack[0];
            int damage = attack[1];
            int tempTime = 0;
            
            while(time < attackTime){
                System.out.println(time + " | " +health+  " | " +healthTime);
                // 매초 회복
                health = health + x > maxHealth ? maxHealth : health + x;
                healthTime++;
                
                // 사전 시간동안 공격받지 않았어용
                if(healthTime == t){
                    health = health + y > maxHealth ? maxHealth : health + y;
                    healthTime=0;
                }
                time++;
            }
            
            if(time == attackTime){
                health -= damage;
                healthTime=0;
                time++;
            }
            if(health <= 0) break;
        }
        
        
        
        int answer = health <= 0 ? -1 : health;
        return answer;
    }
}