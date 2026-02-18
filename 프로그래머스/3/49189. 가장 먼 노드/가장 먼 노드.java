import java.util.*; 

class Solution {
    List<Integer>[] graph; 
    int[] dist;
    int maxDist; 
    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new List[n+1]; 
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>(); 
        }
        
        for(int i=0; i<edge.length; i++){
            int a = edge[i][0], b = edge[i][1]; 
            graph[a].add(b); graph[b].add(a); 
        }
        
        dist = new int[n+1]; // 정점별 1번과의 거리 
        bfs(n); 
        
        maxDist = 0; 
        for(int i=0; i<n+1; i++){
            maxDist = Math.max(maxDist, dist[i]);
        }
        
        for(int i=1; i<n+1; i++){
            if(maxDist == dist[i]) answer++; 
        }
        return answer;
    }
    public void bfs(int n){
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        boolean[] v = new boolean[n+1]; 
        q.add(1); 
        v[1] = true; // 1번 정점부터 시작한다 
        dist[1] = 0; 
        while(!q.isEmpty()){
            int cur = q.poll(); 
            for(int a: graph[cur]){
                if(!v[a]){
                    v[a] = true; 
                    q.add(a);
                    dist[a] = dist[cur]+1; 
                }
            }
        }
    }
}
/*
각 노드까지의 최단 경로 길이를 모두 구하고 거기서 max값을 구해서? 세면 될 것 같은데 
*/