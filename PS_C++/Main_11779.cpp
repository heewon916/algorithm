#include <iostream> 
#include <vector> 
#include <climits> 
#include <queue>
#include <stack> 
using namespace std; 

int n, m; 
int start, target;
int minDistance; 
vector<int> dijkstra(vector<vector<pair<int, int>>> graph){

    // 나로 오기 직전에 어느 정점을 방문해야 하는지 표시해둬야 경로 추적이 가능하다 
    vector<int> parent(n+1, 0); 
    parent[start] = -1; // 경로 추척할 때 -1을 만나면 끝난 것이기도 함 

    vector<int> dist(n+1, INT_MAX); 
    dist[start] = 0; // 시작 정점 기준으로 0으로 설정해둬야 함 
    
    // pq의 경우 first로 먼저 오름차순 정렬을 하게 된다.
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; 
    pq.push({dist[start], start}); 

    int minV, minDist; 
    while(!pq.empty()){
        // pq는 top()으로 맨 위에 있는 걸 확인할 수 있다 
        // 비용, 정점 
        pair<int, int> cur = pq.top(); pq.pop(); 
        
        minDist = cur.first; minV = cur.second; 

        if(dist[minV] < minDist) continue; 
        
        if(minV == target) {
            // 그 동안의 경로 뱉고 비용도 뱉어야 함 
            minDistance = minDist; 
            break;
        }

        for(pair<int, int> adj: graph[minV]){
            int v = adj.second, cost = adj.first; 
            if(dist[v] > minDist + cost){
                dist[v] = minDist + cost;
                pq.push({dist[v], v}); 
                parent[v] = minV; 
            }
        }

    }
    return parent; 
}
int main(){
    cin >> n; 
    cin >> m; 
    // i가 갈 수 있는 정점, 그때의 가중치
    vector<vector<pair<int, int>>> graph(n+1);  
    
    for(int i=0; i<m; i++){
        int a, b, c; 
        cin >> a >> b >> c; 
        // 비용, 정점
        graph[a].push_back({c, b}); // a->b 경로 추가      
    }

    // 출발 정점과 도착 정점 
    // 정점 번호는 1부터 시작한다
    cin >> start >> target; 

    vector<int> parent = dijkstra(graph);

    int count = 1; 
    stack<int> stk; 
    stk.push(target); 
    while(true){
        int c = stk.top(); 
        int p = parent[c]; 
        if(p == -1) break; 
        stk.push(p); 
        count++; 
    }
    cout << minDistance << "\n"; 
    cout << count << "\n"; 
    
    while(!stk.empty()){
        cout << stk.top() << " ";
        stk.pop(); 
    }
    system("pause"); 
    return 0; 
}
/*
n개의 정점 1000 
m개의 간선 

a->b 비용 가중치 최소화 

최소 비용과 경로 찾기 

단방향 그래프 

가중치가 1이 아님 + 출발점 -> 도착지 지정 -> 다익스트라 

*/