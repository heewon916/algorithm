#include <queue> 
#include<vector>
using namespace std;

vector<int> dr = {-1, 1 ,0, 0}; 
vector<int> dc = {0, 0, -1, 1}; 
struct Pos{
    int r, c, depth; 
};
int solution(vector<vector<int> > maps)
{
    int n = maps.size(); // r size
    int m = maps[0].size(); // c size 
    // r, c, depth 
    queue<Pos> q; 
    q.push({0, 0, 1}); 
    vector<vector<bool>> v(n, vector<bool>(m, false)); 
    v[0][0] = true; 
    int answer = -1; 
    while(!q.empty()){
        Pos cur = q.front(); q.pop(); 
        int r = cur.r, c = cur.c, depth = cur.depth; 
        if(r == n-1 && c == m-1){
            answer = depth; 
            break;
        }
        for(int d=0; d<4; d++){
            int nr = r + dr[d]; 
            int nc = c + dc[d];
            if(nr<0 || nr>=n || nc<0 || nc>=m) continue; 
            if(v[nr][nc] || maps[nr][nc] == 0) continue; 
            q.push({nr, nc, depth+1}); 
            v[nr][nc] = true; 
        }
    }
    
    return answer;
}
/*
지나가야 하는 칸의 개수 최솟값 
bfs 
도착 불가 -> -1 출력 
0은 벽 1은 길 
출발: 0,0 
도착: n-1, m-1 
*/