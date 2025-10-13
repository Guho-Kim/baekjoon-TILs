import java.util.*;
class Solution {
    final int UP    = 0;
    final int RIGHT = 1;
    final int DOWN  = 2;
    final int LEFT  = 3;
    final int DONE  = 4;
    
    int[][] points;
    int[][] routes;
    int robotSize;
    int totalCnt;
    int[][] board;
    boolean[] visited;
    Robot[] robots;
    int finishedCnt;
    
    class Robot{
        int row;
        int col;
        int index;
        int[] route;
        public Robot(int row, int col, int[] route){
            this.row = row;
            this.col = col;
            this.index = 0;
            this.route = route;
        }
    }
    
    private void _init(int[][] points, int[][] routes){
        this.points = points;
        this.routes = routes;
        this.robotSize = routes.length;
        this.totalCnt = 0;
        this.board = new int[101][101];
        this.robots = new Robot[robotSize];
        for(int i=0; i<robotSize; i++){
            int[] point = points[routes[i][0]-1];
            robots[i] = new Robot(point[0], point[1], routes[i]);
        }
        this.finishedCnt=0;
        visited = new boolean[robotSize];
    }
    
    private int getDangerCnt(){
        int cnt=0;
        for(int i=1; i<101; i++){
            for(int j=1; j<101; j++){
                // System.out.print(board[i][j] + " ");
                if(board[i][j]>1){
                    cnt++;
                }
            }
            // System.out.println();
        }
        // System.out.println();
        return cnt;
    }
    private int getDirection(Robot robot){
        if(robot.index+1 == robot.route.length) return DONE;
        int dest = robot.route[robot.index+1]; // NullPointerException
        int row = robot.row;
        int col = robot.col;
        int dRow = points[dest-1][0];
        int dCol = points[dest-1][1];
        
        if(row < dRow){
            return DOWN;
        }else if(row > dRow){
            return UP;
        }else{
            if(col < dCol){
                return RIGHT;
            }else if(col > dCol){
                return LEFT;
            }else{
                return DONE;
            }
        }
    }
    private void moveAndCheckRobot(Robot robot, int i){
        if(robot.index+1 == robot.route.length) return;
        int src = robot.route[robot.index];
        int dest = robot.route[robot.index+1]; // NullPointerException
        
        // 어디로 갈까?
        int dir = getDirection(robot);
        
        // 도착이에요.
        if(dir == DONE){
            robot.index++;
            // 다음 목적지로 갈게요~
            dir = getDirection(robot);
            if(dir==DONE){
                // robot = null;
                visited[i] = true;
                finishedCnt++;
                return;
            }
        }
        
        if(dir==UP){
            robot.row--;
        }else if(dir==RIGHT){
            robot.col++;
        }else if(dir==DOWN){
            robot.row++;
        }else if(dir==LEFT){
            robot.col--;
        }
        
        return;
    }
    public int solution(int[][] points, int[][] routes) {
        _init(points, routes);
        while(finishedCnt < robotSize){
            // 로봇의 현재 위치를 카운트합니다.
            for(int i=0; i<robotSize; i++){
                Robot robot = robots[i];
                if(visited[i]) continue;
                // if(robot==null) continue;
                board[robot.row][robot.col]++;
            }
            int cnt = getDangerCnt();
            totalCnt += cnt;
            
            // 이동합시다.
            for(int i=0; i<robotSize; i++){
                Robot robot = robots[i];
                // if(robot==null) continue;
                if(visited[i]) continue;
                board[robot.row][robot.col]--;
                
                // 이동
                moveAndCheckRobot(robot, i);
            }
        }
        int answer = totalCnt;
        return answer;
    }
}