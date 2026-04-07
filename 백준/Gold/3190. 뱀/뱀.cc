#include <iostream>
#include <vector> 
#include <deque> 

using namespace std; 
struct CMD{
    int afterSec; 
    char dir; 
};
vector<CMD> cmdlist;
int n, k; // 100, 100 
vector<int> dr = {-1, 0, 1, 0}; 
vector<int> dc = {0, 1, 0, -1}; 
int main(){
    cin >> n; // 보드 크기  
    cin >> k; // 사과 개수 

    vector<vector<int>> map(n, vector<int>(n, 0)); 
    for(int i=0; i<k; i++){
        int r, c; 
        cin >> r >> c; // 1-index/... 
        map[r-1][c-1] = 1; 
    }

    int l; 
    cin >> l; // 뱀의 변환 횟수 

    int sec; 
    char tmp;
    // sec초가 끝난 후에 왼쪽(d-1) 또는 오른쪽(d+1)으로 방향을 회전시킨다.  
    for(int i=0; i<l; i++){
        cin >> sec >> tmp; 
        cmdlist.push_back({sec, tmp}); 
    }

    int d = 1; // 오른쪽을 기점으로 시작한다 
    int r, c; 
    int nr, nc; 
    deque<pair<int, int>> dq; 
    dq.push_back({0, 0}); 
    map[0][0] = 2; 
    int time = 0; 
    int listIdx = 0; 
    while(true){
        time++; 
        pair<int, int> pos = dq.front();
        r = pos.first; c = pos.second; 

        nr = r + dr[d]; 
        nc = c + dc[d]; 

        if(nr < 0 || nr >= n || nc<0 || nc>=n) break; // 벽
        if(map[nr][nc] == 2) break; // 자기 자신 

        if(map[nr][nc] == 1){ // 사과이면 
            map[nr][nc] = 2;  // 사과 없어지고 꼬리 그대로 
            dq.push_front({nr, nc});
        } else { // 벽/자기자신/사과 다 아닌 빈칸이면 꼬리 이동 필요 
            // 꼬리 지우기
            pair<int, int> tail = dq.back(); 
            map[tail.first][tail.second] = 0; // 맨 뒤에 있는 꼬리 없애고 
            dq.pop_back(); 

            // 머리 이동 
            dq.push_front({nr, nc}); 
            map[nr][nc] = 2; 
        }
        
        
        // X초가 끝난 뒤에 회전시킨다 
        if(listIdx < l && time == cmdlist[listIdx].afterSec){
            int changeD = cmdlist[listIdx].dir; 
            if(changeD == 'L'){// 왼쪽으로 회전 d-1
                d = (d+3) % 4;  
            } else { // 오른쪽으로 회전 d+1
                d = (d+1) % 4; 
            }
            listIdx++; // 이걸 빼먹었네 
        }
    }

    cout << time ;
    return 0; 
}
/*
nxn 배열 
상하좌우 끝에는 벽이 있음 

시작점 (1,1) 길이 = 1 방향 = 오른쪽 

오른쪽 방향 기준: 상 -> 우 -> 하 -> 좌 

사과 = 1 
뱀 = 2

1. 꼬리 유지 + 한 칸 앞으로 
- 만약 벽 or 자기 자신 -> return 
- 만약 사과 -> 꼬리 유지 + 길이 증가 + 사과 없어짐 
- 만약 빈 칸 -> 꼬리 그 방향으로 1칸 이동 (원래 칸 비우기 필수)
*/