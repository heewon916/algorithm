import java.util.*; 
class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length; 
        int m = maps[0].length; 
        int[][] depth = new int[n][m]; 
        boolean[][] v = new boolean[n][m]; 
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        v[0][0] = true; 
        depth[0][0] = 1; 
        q.add(new int[]{0, 0});
        while(!q.isEmpty()){
            int[] cur = q.poll(); 
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d]; 
                int nc = cur[1] + dc[d]; 
                if(nr<0 || nr>=n || nc<0 || nc>=m) continue; 
                if(!v[nr][nc] && maps[nr][nc] == 1){
                    depth[nr][nc] = depth[cur[0]][cur[1]] + 1; 
                    v[nr][nc] = true; 
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return depth[n-1][m-1] == 0? -1 : depth[n-1][m-1]; 
    }
}
/*
상대 팀 진영에 빨리 도착 
검은색 못 감 
상하좌우 가능 
지나가야 하는 칸의 최솟값 
도착할 수 없으면 -1 

bfs 같고 방문하면 depth+1 하면 될 듯 
*/