#include <vector>
#include <queue>

using namespace std;
vector<int> di = {-1, 1, 0, 0}; 
vector<int> dj = {0, 0, -1, 1};
struct State{
    int i, j; 
    int depth; 
};
int bfs(int n, int m, vector<vector<int>>& maps){
    queue<State> q; 
    vector<vector<bool>> v(n, vector<bool>(m, false));
    q.push({0, 0, 1}); // 시작점 
    v[0][0] = true; 
    
    bool able = false; 
    while(!q.empty()){
        State cur = q.front(); 
        int ci = cur.i, cj = cur.j;
        int cd = cur.depth; 
        if(ci == n-1 && cj == m-1){
            able = true; 
            return cd; 
        }
        q.pop(); 
        for(int d=0; d<4; d++){
            int ni = ci + di[d]; 
            int nj = cj + dj[d];
            if(ni<0 || ni>=n || nj<0 || nj>=m) continue; 
            if(v[ni][nj]) continue;
            if(maps[ni][nj] == 1){ // 벽이 없으면 
                v[ni][nj] = true; 
                q.push({ni, nj, cd+1});        
            }
        }
    }
    if(!able) return -1; 
    
}

int solution(vector<vector<int>> maps)
{
    int n = maps.size(); 
    int m = maps[0].size(); 
    int answer = bfs(n, m, maps);
    return answer;
}

/*
1,1에서 시작해서 n,m로 가는데 최단 경로가 필요함 
가중치가 다 똑같으니까 bfs 

*/