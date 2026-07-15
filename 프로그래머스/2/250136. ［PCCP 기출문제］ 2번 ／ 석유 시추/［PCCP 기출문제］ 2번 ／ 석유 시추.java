import java.util.*; 

class Solution {
    public HashMap<Integer, Integer> mping = new HashMap<>(); 
    public int grpNo = 1; 
    public boolean[][] v; 
    public int[] di = {-1, 1, 0, 0};
    public int[] dj = {0, 0, -1, 1}; 
    public void makeMping(int[][] land, int i, int j, int n, int m){
        Queue<int[]> dq = new ArrayDeque<>(); 
        dq.offer(new int[]{i, j});
        v[i][j] = true; 
        land[i][j] = grpNo;
        
        int cnt = 0; 
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll(); 
            int ci = cur[0], cj = cur[1];
            land[ci][cj] = grpNo; 
            cnt++; 
            for(int d=0; d<4; d++){
                int ni = ci+di[d], nj = cj+dj[d]; 
                if(ni<0 || ni>=n || nj<0 || nj>=m) continue; 
                if(v[ni][nj]) continue; 
                if(land[ni][nj] == 0) continue; 
                dq.offer(new int[]{ni, nj}); 
                v[ni][nj] = true; 
            }
        }
        
        mping.putIfAbsent(grpNo, cnt); 
        return; 
        
    }
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length; 
        v = new boolean[n][m]; 
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!v[i][j] && land[i][j] == 1){
                    makeMping(land, i, j, n, m); 
                    grpNo++; 
                }
            }
        }
        // for(Map.Entry<Integer, Integer> entry: mping.entrySet()){
        //     System.out.println(entry.getKey() + ": " + entry.getValue());
        // }
        int answer = 0;
        for(int c=0; c<m; c++){
            int ttl = 0; 
            Set<Integer> colset = new HashSet<>(); 
            for(int r=0; r<n; r++){
                if(land[r][c] == 0) continue; 
                colset.add(land[r][c]);
            }
            for(Integer i : colset){
                ttl += mping.get(i);
            }
            answer = (ttl < answer)? answer: ttl;  
        }
        
        return answer;
    }
}
/*
(1) 네트워크 개수마냥, 상하좌우 인접한 것끼리 묶어서 그룹의 번호를 맵에 적는 동시에 그 그룹 내에 포함되는 칸의 개수도 세서, 그룹번호:칸의개수 key-value로 구성하고
-> bfs나 dfs + hashMap 구조 쓰면 될 것 같고 

(2) 이제 열마다 어떤 번호가 포함되는지 확인함과 동시에 total value(칸의 개수)값을 열마다 구해가며
maximum으로 처리하면 되지 않을까 싶기는 한데 
-> 100 x 100 처리 될 듯 

100x100이니까 10^4 
제한시간이 10초니까 10* 2^3 시간 충분하지 않나? 
*/