#include <string>
#include <vector>
#include <cmath> 
#include <climits> 
#include <queue> 
#include <iostream> 
using namespace std;

/**
시작점을 기준으로 모든 정점까지의 최단 경로를 계산한다. 
갈 수 있는 minVertex, minDist를 계속해서 업데이트한다 
근데 가중치가 다 1이니까 그냥 bfs 돌리는 게 더 간단하다
*/
void dijkstra(int node, vector<vector<int>>& graph, vector<int>& dist){
    // {정점, dist[정점]} + 최소힙으로 만들고 싶으면 greater 사용 필수
    // [!!] 주의사항: 정렬 기준이 first 먼저 하고 second이기 때문에 {dist[정점], 정점}으로 선언해야 한다 
    /**
    priority_queue<pair<int, int>, vector<int, int>, greater<pair<int, int>>> pq;
    - 정렬기준이 pair<int, int>에서 first 기준으로 정렬한다
    만약 최대힙으로 하고 싶으면 priority_queue<pair<int, int>> 까지만 적어도 된다 
    */
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    dist[node] = 0; // pq에 push 하기 전에 무조건 0으로 세팅이 필수다
    pq.push({dist[node], node}); 
    
    
    while(!pq.empty()){
        // pq는 top()으로 가져가야 한다
        pair<int, int> cur = pq.top(); pq.pop(); 
        int minDist = cur.first; 
        int minV = cur.second;
        
        if(dist[minV] < minDist) continue; 
        
        for(int next: graph[minV]){
            if(dist[next] > minDist + 1){
                dist[next] = minDist + 1; 
                pq.push({dist[next], next}); 
            }
        }
    }
}


int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    vector<vector<int>> graph(n+1);
    
    for(vector<int> e: edge){
        graph[e[0]].push_back(e[1]);
        graph[e[1]].push_back(e[0]);
    }
    
    vector<int> dist(n+1, INT_MAX);
    
    dijkstra(1, graph, dist); 
    
    int maxDist = 0; 
    for(int a: dist){
        cout << a << " "; 
        if(a == INT_MAX) continue; 
        maxDist = max(maxDist, a); 
    }
    
    for(int a: dist){
        if(a == maxDist) answer++; 
    }
    return answer;
}
/*
1번 정점 기준 최단경로 계산
그 중에서 가장 긴 최단경로 ㄱㄱ 

노드의 개수 n 20000 
양방향 
50000개 간선 

그래프를 만들고 
다익스트라? 
*/