import java.util.*; 
class Solution {
    boolean[] v; 
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[n]; 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!v[j] && computers[i][j] == 1){
                    v[j] = true; 
                    bfs(computers, j, n);
                    answer++; 
                }
            }
        }
        return answer;
    }
    public void bfs(int[][] computers, int j, int n){
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        q.add(j);
        v[j] = true; 
        while(!q.isEmpty()){
            int cur = q.poll(); 
            for(int i=0; i<n; i++){
                if(!v[i] && computers[cur][i] == 1){
                    q.add(i);
                    v[i] = true; 
                }
            }
        }
    }
}
/*
컴퓨터끼리 연결되어있는지 궁금
*/