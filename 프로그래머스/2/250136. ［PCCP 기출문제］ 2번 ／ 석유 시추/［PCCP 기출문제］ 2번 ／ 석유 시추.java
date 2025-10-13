/*
    매번 bfs로 영역 구하는 것이 좋지 않을까?
*/
import java.util.*;
class Solution {
    int rowSize, colSize;
    int maxOilMnt;
    int[] oilCols;
    int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    
    
    boolean[][] visited;
    int[][] land;
    int oilNum;
    
    class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row=row;
            this.col=col;
        }
    }
    
    public int solution(int[][] land) {
        rowSize = land.length;
        colSize = land[0].length;
        this.land = land;
        maxOilMnt = 0;
        oilCols = new int[colSize];
        visited = new boolean[rowSize][colSize];

        for(int row=0; row<rowSize; row++){
            for(int col=0; col<colSize; col++){
                if(visited[row][col] || land[row][col]==0) continue;
                bfs(row, col);
            }
        }
        for(int col=0; col<colSize; col++){
            maxOilMnt = maxOilMnt < oilCols[col] ? oilCols[col] : maxOilMnt;
        }
        int answer = maxOilMnt;
        return answer;
    }
    
    // 여기서 oil 양이랑 해당하는 colIdx를 구하자.
    void bfs(int r, int c){
        int oilCnt = 1;
        Set<Integer> oilIndices = new HashSet<>();
        oilIndices.add(c);
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(r,c));
        visited[r][c] = true;
        
        while(!queue.isEmpty()){
            Point curPoint = queue.poll();
            int row = curPoint.row;
            int col = curPoint.col;
            
            for(int[] dir : dirs){
                int nRow = row+dir[0];
                int nCol = col+dir[1];
                if(!isInMap(nRow, nCol) || land[nRow][nCol]==0 || visited[nRow][nCol]) continue;
                queue.add(new Point(nRow, nCol));
                visited[nRow][nCol] = true;
                oilCnt++;
                oilIndices.add(nCol);
            }
        }
        for(int colIdx : oilIndices){
            oilCols[colIdx]+=oilCnt;
        }
        
    }
    
    boolean isInMap(int row, int col){
        return row<rowSize && row >=0 && col<colSize && col>=0;
    }
}