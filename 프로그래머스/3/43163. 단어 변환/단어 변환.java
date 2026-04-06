import java.util.*; 
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length; 
        
        for(int i=0; i<n; i++){
            if(words[i].equals(target)) answer++; 
        }
        if(answer == 0) return 0; 
        
        String[] newWords = new String[n+1];
        System.arraycopy(words, 0, newWords, 1, n);
        newWords[0] = begin; 
        
        System.out.println(Arrays.toString(newWords));
        
        int[][] diff = new int[n+1][n+1]; 
        for(int i=0; i<n+1; i++){
            for(int j=i+1; j<n+1; j++){
                int cnt = 0; 
                for(int k=0; k<newWords[i].length(); k++){
                    if(newWords[i].charAt(k) != newWords[j].charAt(k)) cnt++; 
                }
                diff[i][j] = diff[j][i] = cnt; 
            }
        }
        
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        boolean[] v = new boolean[n+1];
        v[0] = true; // begin이니까 
        q.add(new int[]{0, 0}); // index, depth
        while(!q.isEmpty()){
            int[] cur = q.poll(); 
            // System.out.println("curr word: " + newWords[cur[0]] + " depth: " + cur[1]);
            if(newWords[cur[0]].equals(target)) {
                answer = cur[1]; 
                break; 
            }
            
            for(int i=0; i<n+1; i++){
                if(!v[i] && diff[cur[0]][i] == 1){
                    v[i] = true; 
                    // System.out.println("=> add word: " + newWords[i] + " depth: " + (cur[1] + 1));  
                    q.add(new int[]{i, cur[1] + 1});
                }
            }
        }
        
        return answer;
    }
}
/*
2차원 배열을 만들어서 각 문자열 간의 다른 개수를 적어놓고? 

begin에서부터 시작해서 2차원 배열에서 차이가 1인 문자열의 인덱스를 큐에 넣어두고 
차례로 탐색해가기 - 이때 target을 만나면 거기서 그냥 끊으면 됨 

"hit", "hot", "dot", "dog", "lot", "log", "cog"
[0, 1, 2, 3, 2, 3, 3]
[1, 0, 1, 2, 1, 2, 2]
[2, 1, 0, 1, 1, 2, 2]
[3, 2, 1, 0, 2, 1, 1]
[2, 1, 1, 2, 0, 1, 2]
[3, 2, 2, 1, 1, 0, 1]
*/