#include <string>
#include <vector>
#include <queue> 
#include <climits>
using namespace std;

struct Pos{
    int no; 
    int distance; 
};
int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    // 그래프 정의 
    vector<vector<int>> graph(n+1); 
    for(vector<int> e: edge){
        int a = e[0], b = e[1]; 
        graph[a].push_back(b); 
        graph[b].push_back(a); 
    }
    
    // {정점 번호, dist[정점]} -> 거리 기준 멀리 
    auto comp = [](Pos& a, Pos& b){
        if(a.distance != b.distance) return a.distance > b.distance; // pq는 최대힙이니까 내림차순 정렬 필요
        else return true; 
    };
    priority_queue<Pos, vector<Pos>, decltype(comp)> pq(comp); 
    vector<int> dist(n+1, INT_MAX); // 거리 저장용 
    dist[1] = 0; 
    pq.push({1, dist[1]}); 
    while(!pq.empty()){
        Pos cur = pq.top(); pq.pop(); 
        int minV = cur.no; 
        int minDist = cur.distance; 
        
        if(dist[minV] < minDist) continue; // 지금 막 계산된 게 최단이 아니면 고려할 필요가 없음
        
        for(int adj: graph[minV]){
            if(dist[adj] > minDist + 1){
                dist[adj] = minDist +1; 
                pq.push({adj, dist[adj]}); 
            }
        }
    }
    
    int maxV = 0; 
    for(int i=1; i<dist.size(); i++){
        if(maxV < dist[i]){
            maxV = dist[i]; 
        }
    }
    for(int i=0; i<dist.size(); i++){
        if(maxV == dist[i]){
            answer++;
        }
    }
    return answer;
}
/*
n개의 노드 
1..n (20000)
가장 멀리 떨어져 있는 노드의 개수 
최단경로라고 했을 때 각 노드까지 얼마의 depth인지 확인 필요
근데 간선 가중치가 1이니까 bfs로 충분함 
근데 다익으로 풀어보자 ㅇㅇ
*/