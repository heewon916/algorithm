#include <string>
#include <vector>
#include <queue> 
#include <iostream> 
using namespace std;

// 좌표 최대 50 1-index 니까 51까지 필요 -> 2배니까 102
int map[102][102]; 
int dr[4] = {-1, 1, 0, 0}; 
int dc[4] = {0, 0, -1, 1}; 
struct Pos{
    int r, c; 
    int depth; 
};
vector<vector<bool>> v(102, vector<bool>(102, false)); 
int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0; 
    for(vector<int> pos: rectangle){
        // 좌측 하단 c, 좌측 하단 r, 우측 상단 c, 우측 상단 r 
        int leftC = pos[0] *2; 
        int leftR = pos[1] *2; 
        int rightC = pos[2] *2; 
        int rightR = pos[3] *2; 
        for(int r=leftR; r<=rightR; r++){
            for(int c=leftC; c<=rightC; c++){
                // 좌표가 테두리가 아니면; 어느 하나 좌측 하단/상단, 우측 하단/상단에 해당하는 좌표가 없어야 한다 
                if(r>leftR && r<rightR && c>leftC && c<rightC) map[r][c] = -1; 
                else {
                    // 만약 이미 다른 사각형의 내부인 경우에는 테두리가 될 수 없다. 
                    if(map[r][c] != -1) map[r][c] = 1; 
                }
            }
        }
    }
    int startC = characterX * 2; 
    int startR = characterY * 2; 
    int targetC = itemX * 2; 
    int targetR = itemY * 2; 

    // 시작점에서 1인 경로에 대해서만 움직인다. 
    // 결국 최단경로니까 로직은 bfs가 맞다. 근데 양쪽으로 둘 다 갈 수 있으면 depth 계산이 필요해보임 
    // r, c, depth 
    queue<Pos> q; 
    q.push({startR, startC, 0}); 
    v[startR][startC] = true; 
    while(!q.empty()){
        Pos cur = q.front(); q.pop(); 
        // cout << cur.r << ", " << cur.c << ", " << cur.depth << "\n"; 
        if(cur.r == targetR && cur.c == targetC) {
            answer = cur.depth / 2; 
            // cout << "도착" ; 
            break; 
        }

        // 현재 방향 기준으로 상하좌우로만 이동하는데 
        // 방문 안했고 && 그게 테두리이면 갈 수 있다 
        for(int d=0; d<4; d++){
            int nr = cur.r + dr[d]; 
            int nc = cur.c + dc[d]; 
            if(nr<0 || nr>101 || nc<0 || nc>101) continue; 
            if(v[nr][nc] || map[nr][nc] == -1 || map[nr][nc] == 0) continue; // 조건을 이렇게 뺄거면 방문함 && 내부임 && 빈칸임(얘도 같이 빼줘야지;!!!!)

            v[nr][nc] = true; 
            q.push({nr, nc, cur.depth+1}); 
        }
    }
    
    return answer;
}
/*
테두리는 1 내부는 -1로 표기하되
이미 다른 사각형의 내부가 아닐 때만 1로 표기하기 
이때 경로로만 이동해야 되는데 바로 인접해있으면 경로가 아님에도 그리로 뻥뛰기?를 해버릴 수 있어서 좌표를 2배로 늘리는 전략을 활용한다. 

주의점 x,y는 각각 열,행에 대한 숫자임 
---
테두리는 2로 내부는 1로 표현한다고 치면 
2만 따라가면 되는건데 
그러면 내부를 전부 순회하는 이슈가 발생할 듯 
좌표값이 1 ~ 50이라고 치면 선 따라 가다가 사방이 전부 1이면 돌아가고 
아니면 계속 가면 되는 .. 그런 거 아닐까 bfs 누적합인가 
*/