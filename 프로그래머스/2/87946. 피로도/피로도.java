import java.util.*; 

class Solution {
    int n;
    int[] b; 
    boolean[] v;
    int answer = 0; 
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length; // 던전 개수 
        b = new int[n]; // 일단 모든 던전을 방문하는 것으로 가정하고 진행한다. 
        v = new boolean[n]; 
        perm(0, k, dungeons);
        return answer;
    }
    public void perm(int cnt, int k, int[][] dungeons){
        if(cnt == n){
            // b에 있는 번호 순서대로 계산해보기 
            // System.out.println(Arrays.toString(b));
            int left = k; 
            int maxVisit = 0; 
            for(int idx=0; idx<n; idx++){
                int i = b[idx]; 
                int limit = dungeons[i][0]; 
                int d = dungeons[i][1]; 
                if(left >= limit && (left-d >=0)){
                    left = left - d; 
                    maxVisit++; 
                }else{
                    break; // 더 이상 진행 불가 
                }
            }
            answer = Math.max(answer, maxVisit);
            return; 
        }
        for(int i=0; i<n; i++){
            if(v[i]) continue; 
            v[i] = true; 
            b[cnt] = i; 
            perm(cnt+1, k, dungeons); 
            v[i] = false; 
        }
    }
}
/*
permMain으로 풀면 될 것 같음
*/