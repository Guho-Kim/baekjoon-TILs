import java.util.*;
/*

*/
class Solution {
    int rowSize, colSize;
    int minStep;
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    int rrt, rct, brt, bct;
    int[][] maze;
    boolean[][] redVisited;
    boolean[][] blueVisited;
    
    public void backtrack(int rr, int rc, int br, int bc, int step){
        //System.out.println("--- step : " + step);
        //System.out.println("--- red  : (" + rr + ", " + rc + ")");
        //System.out.println("--- blue : (" + br + ", " + bc + ")");
        // terminate
        if(rr==rrt && rc==rct && br==brt && bc==bct){
            minStep = step > minStep ? minStep : step;
        }
        
        //prune
        if(step >= 16) return;
        
        // next
        if(rr == rrt && rc == rct){
            // red hold
            for(int[] bDir : dirs){
                int nbr = br + bDir[0];
                int nbc = bc + bDir[1];

                if(!inMaze(nbr,nbc)) continue;
                if(rr==nbr && rc==nbc) continue;
                if(blueVisited[nbr][nbc]) continue;
                blueVisited[nbr][nbc]=true;
                backtrack(rr, rc, nbr, nbc, step+1);
                blueVisited[nbr][nbc]=false;
            }
        }else if(br==brt && bc==bct){
            // blue hold
            for(int[] rDir : dirs){
                int nrr = rr + rDir[0];
                int nrc = rc + rDir[1];

                if(!inMaze(nrr,nrc)) continue;
                if(nrr==br && nrc==bc) continue;
                if(blueVisited[nrr][nrc]) continue;
                redVisited[nrr][nrc]=true;
                backtrack(nrr, nrc, br, bc, step+1);
                redVisited[nrr][nrc]=false;
            }
        }else{
            for(int[] rDir : dirs){
                for(int[] bDir : dirs){
                    int nrr = rr + rDir[0];
                    int nrc = rc + rDir[1];
                    int nbr = br + bDir[0];
                    int nbc = bc + bDir[1];

                    if(!inMaze(nrr,nrc) || !inMaze(nbr,nbc)) continue;
                    if(nrr==nbr && nrc==nbc) continue;
                    if(nrr==br && nrc==bc && nbr==rr && nbc==rc) continue;
                    if(redVisited[nrr][nrc] || blueVisited[nbr][nbc]) continue;
                    redVisited[nrr][nrc]=true;
                    blueVisited[nbr][nbc]=true;
                    backtrack(nrr, nrc, nbr, nbc, step+1);
                    redVisited[nrr][nrc]=false;
                    blueVisited[nbr][nbc]=false;
                }
            }
        }
    }
    public int solution(int[][] maze) {
        rowSize = maze.length;
        colSize = maze[0].length;
        minStep = Integer.MAX_VALUE;
        int rr, rc, br, bc;
        rr = rc = br = bc = 0;
        this.maze=maze;
        redVisited = new boolean[rowSize][colSize];
        blueVisited = new boolean[rowSize][colSize];
        for(int r=0; r<rowSize; r++){
            for(int c=0; c<colSize; c++){
                if(maze[r][c]==1){
                    rr=r;
                    rc=c;
                }else if(maze[r][c]==2){
                    br=r;
                    bc=c;
                }else if(maze[r][c]==3){
                    rrt=r;
                    rct=c;
                }else if(maze[r][c]==4){
                    brt=r;
                    bct=c;
                }else if(maze[r][c]==5){
                    blueVisited[r][c]=true;
                    redVisited[r][c]=true;
                }
            }
        }
        redVisited[rr][rc]=true;
        blueVisited[br][bc]=true;
        
        backtrack(rr,rc,br,bc,0);
        if(minStep== Integer.MAX_VALUE){
            minStep = 0;
        }
        int answer = minStep;
        return answer;
    }
    
    
    
    public boolean inMaze(int r, int c){
        return r>=0 && r<rowSize && c>=0 && c<colSize;
    }
}