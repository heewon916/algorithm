#include <string>
#include <vector>
#include <algorithm> 
#include <queue> 

using namespace std;
 
int bfs(int n, int node, vector<vector<int>>& g){
    vector<bool> v(n+1, false);
    
    queue<int> q; 
    q.push(node); 
    v[node] = true; 
    
    int visitedCnt = 1;
    
    while(!q.empty()){
        int cur = q.front(); q.pop(); 
        
        for(int next: g[cur]){
            if(!v[next]) {
                q.push(next); 
                v[next] = true; 
                visitedCnt++; 
            }
        }
    }
    return visitedCnt; 
}

int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    
    vector<vector<int>> graph(n+1);
    vector<vector<int>> reverseGraph(n+1); 
    
    for(vector<int> edge: results){
        int a = edge[0], b = edge[1]; 
        graph[b].push_back(a); // b->a 정방향 그래프 
        reverseGraph[a].push_back(b); // a->b 역방향 그래프 
    }
    
    for(int i=1; i<=n; i++){
        // 시작점 i, 그래프, 카운트개수 
        int cnt = bfs(n, i, graph);
        int rCnt = bfs(n, i, reverseGraph); 
        if(cnt + rCnt == n+1){ // 모든 정점을 방문할 수 있었다면; 중복 방문한 거 없는지 체크 필요
            answer++; 
        }
    }
    
    return answer;
}
/*
a, b : b->a 방향이 정방향 그래프/ a->b 방향이 역방향 그래프 
*/