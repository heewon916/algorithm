class Solution {
    boolean[] v; 
    int answer = -1; 
    public int solution(int k, int[][] dungeons) {
        v = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return answer;
    }
    public void dfs(int left, int[][] dungeons, int cnt){
        for(int i=0; i<dungeons.length; i++){
            if(!v[i] && left >= dungeons[i][0]){
                v[i] = true; 
                dfs(left-dungeons[i][1], dungeons, cnt+1);
                v[i] = false; 
            }
        }     
        answer = Math.max(answer, cnt);
    }
}