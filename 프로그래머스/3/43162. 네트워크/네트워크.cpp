#include <string>
#include <vector>
#include <queue> // Java에서 ArrayDeque이랑 같은 역할을 한다 
using namespace std;

int answer = 0; 
void bfs(int node, vector<vector<int>>& computers, vector<bool>& v, int n){
    queue<int> q; 
    q.push(node); 
    v[node] = true; 
    while(!q.empty()){
        int cur = q.front();
        q.pop(); 
        
        for(int i=0; i<n; i++){
            if(!v[i] && computers[cur][i] == 1){
                q.push(i); 
                v[i] = true; 
            }
        }
    }
    
}

int solution(int n, vector<vector<int>> computers) {
    answer = 0;
    vector<bool> v(n, false); 
    for(int i=0; i<n; i++){
        if(!v[i]) {
            answer++; 
            bfs(i, computers, v, n); 
        }
    }
    
    return answer;
}
/*
네트워크 개수는 정점마다 bfs를 돌려야 한다 
*/