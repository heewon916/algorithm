import java.util.*; 
class Solution {
    static int[] b; 
    static boolean[] v; 
    static int maxCount; 
    static int N; 
    static int answer; 
    static void perm(int k, int[][] dungeons, int cnt){
        if(cnt == N){
            int leftE = k; 
            // 그럼 이 순서대로 한번 방문을 해보자 이거지 
            int ableCount = 0; 
            for(int i=0; i<N; i++){
                int pos = b[i]; // 이 인덱스의 던전을 방문하자 
                int need = dungeons[pos][0]; 
                int cost = dungeons[pos][1]; 
                if(need > leftE) break; 
                if(cost > leftE) break; 
                leftE -= cost; 
                ableCount++; 
            }
            answer = Math.max(answer, ableCount);
            return; 
        }
        for(int i=0; i<N; i++){
            if(v[i]) continue; 
            b[cnt] = i; 
            v[i] = true; 
            perm(k, dungeons, cnt+1);
            v[i] = false; 
        }
    }
    public int solution(int k, int[][] dungeons) {
        answer = 0; 
        b = new int[dungeons.length];
        N = dungeons.length;  // 던전 개수 
        v = new boolean[N]; 
        perm(k, dungeons, 0);
        return answer;
    }
}
/*
최소 필요 피로도 >= 소모 피로도 

이 던전들을 나열하는 거죠 
*/