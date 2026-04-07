#include <string>
#include <vector>
#include <queue> 
#include <algorithm> 
#include <iostream> 
#include <cmath> 
using namespace std;
vector<vector<int>> graph(101); 
vector<bool> v(101, 0); // bfs 할 때마다 선언하는 것보다 fill 하는 게 더 낫다
int bfs(int n, int v1, int v2){
    int visitedCnt = 1; 
    queue<int> q; 
    q.push(v1); 
    v[v1] = true; 
    while(!q.empty()){
        int cur = q.front(); q.pop(); 
        for(int adj: graph[cur]){
            if((cur==v1 && adj==v2) || (cur==v2 && adj==v1)) continue; 
            if(!v[adj]){
                q.push(adj); 
                v[adj] = true; 
                visitedCnt++; 
            }
        }
    }
    return visitedCnt; 
}

int solution(int n, vector<vector<int>> wires) {
    int answer = 101; 
    for(vector<int> arr: wires){
        graph[arr[0]].push_back(arr[1]); 
        graph[arr[1]].push_back(arr[0]); 
    }
    
    for(auto& edge: wires){
        int visitedCnt = bfs(n, edge[0], edge[1]);
        answer = min(answer, abs((n-visitedCnt) - visitedCnt)); 
        fill(v.begin(), v.end(), false); 
    }

    
    return answer;
}

/*
각 정점마다 연결된 정점 개수를 세두고 
가장 많은 정점과 연결된 정점의 간선 중 하나를 잘라낸다 
*/