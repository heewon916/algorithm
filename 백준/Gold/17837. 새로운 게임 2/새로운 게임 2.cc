#include <iostream> 
#include <vector> 

using namespace std; 

int n, k; // 체스칸의 크기, 말의 개수 
struct Horse{
    int r, c, d; // 행, 열, 이동 방향; 1-index 
};
vector<int> dr = {0, 0, -1, 1}; 
vector<int> dc = {1, -1, 0, 0}; 

int main(){
    cin >> n >> k ; 
    
    // 색상만 입력해둠 - 색은 안 바뀌니까 ㅇㅇ 
    vector<vector<int>> colorMap(n, vector<int>(n, 0)); 
    int tmp; 
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> tmp; 
            colorMap[i][j] = tmp; 
        }
    }
    // 말이 올라가는 그래프: 인덱스만 저장한다 
    vector<vector<vector<int>>> map(n, vector<vector<int>>(n)); 

    // 말 정보만 따로 저장해야 할 것 같은데: 그래야 1턴마다 1번~k번 말 다 순회를 하지 않나
    // 근데 map이랑 이 list랑 동기화 잘 해야 될 듯 
    vector<Horse> horses; 


    // 말의 정보 입력받기 
    int r, c, d; 
    for(int i=0; i<k; i++){
        cin >> r >> c >> d; 
        r--; c--; // 1-index 처리 
        d--; // 방향도 1부터 순서대로 우 좌 상 하 임 

        map[r][c].push_back(i); // 말 저장 
        horses.push_back({r, c, d}); // 
    }

    int turn = 1; 
    bool gameOver = false;  // 한 칸의 말 개수>=4 가 되는지 턴 돌 때는 
    while(turn <= 1000){ 
        // 1턴마다 1번~k번의 말들을 이동한다 
        for(int i=0; i<k; i++){ //[!!] &연산자를 잊지 마 
            Horse& h = horses[i]; 
            int r = h.r, c = h.c, d = h.d; 

            // 내가 가려고 하는 칸이 무슨 색이냐
            int nr = r + dr[d]; 
            int nc = c + dc[d]; 

            // 만약 범위를 벗어나면: 파란색 로직과 동일
            if(nr<0 || nr>=n || nc<0 || nc>=n || colorMap[nr][nc] == 2){
                // A의 방향을 반대로 만들고 
                d = (d % 2 == 0)? d+1 : d-1; 
                // 이동하려는 칸이 파랑이면
                nr = r + dr[d]; 
                nc = c + dc[d];
                h.d = d;  
                if(nr<0 || nr>=n || nc<0 || nc>=n || colorMap[nr][nc] == 2) continue; 
                // else{
                //     // 빨강이나 흰색이면 이동한다
                //     h.r = nr; 
                //     h.c = nc; 
                // }
            }

            // 위에서 파란색이거나 범위 밖으로 가면 방향 바꾸고 다시 판단한다고 했으니 
            // 흰색/ 빨강 처리하는 게 else if문으로 묶이면 처리가 정상적으로 안된다 

            if(colorMap[nr][nc] == 0){ // 흰색 
                // 현재 내가 있는 위치에 말들을 가져와야 해 
                // 지금 h가 있는 위치부터 시작해서 끝까지 챙겨야 함 
                vector<int> tmp; 
                int curPos; 
                for(int j=0; j<map[r][c].size(); j++){
                    if(i == map[r][c][j]) {  // i: 말의 ID , j: 인덱스
                        curPos = j; 
                        break; 
                    }
                }
                // map[r][c] 에서 curPos부터 쭉 옮기고, 지운다 
                for(int j=curPos; j<map[r][c].size(); j++){
                    tmp.push_back(map[r][c][j]); 
                }

                map[r][c].erase(map[r][c].begin() + curPos, map[r][c].end());

                
                // 그 순서 그대로 옮긴다 
                for(int j=0; j<tmp.size(); j++){
                    map[nr][nc].push_back(tmp[j]); 
                    // 말들도 같이 업데이트 
                    horses[tmp[j]].r = nr; 
                    horses[tmp[j]].c = nc; 
                }

                if(map[nr][nc].size() >= 4) {
                    gameOver = true; 
                    break; 
                }

            }else{ // 빨간색 
                // 현재 내가 있는 위치에 말들을 가져와야 해 
                // 지금 h가 있는 위치부터 시작해서 끝까지 챙겨야 함 
                vector<int> tmp; 
                int curPos; 
                for(int j=0; j<map[r][c].size(); j++){
                    if(i == map[r][c][j]) {
                        curPos = j; 
                        break; 
                    }
                }
                // map[r][c] 에서 curPos부터 쭉 옮기고, 지운다 
                for(int j=curPos; j<map[r][c].size(); j++){
                    tmp.push_back(map[r][c][j]); 
                }

                map[r][c].erase(map[r][c].begin() + curPos, map[r][c].end());

                
                // 그 순서 역순으로 옮긴다 
                for(int j=tmp.size()-1; j>=0; j--){
                    map[nr][nc].push_back(tmp[j]); 
                    horses[tmp[j]].r = nr; 
                    horses[tmp[j]].c = nc; 
                }

                if(map[nr][nc].size() >= 4) {
                    gameOver = true; 
                    break; 
                }
            }

            // // 말이 4개 이상 쌓인 곳이 있는지 확인한다 
            // for(int u=0; u<n; u++){
            //     for(int v=0; v<n; v++){
            //         if(map[u][v].size() >= 4) {
            //             gameOver = true; 
            //             break; 
            //         }
            //     }
            //     if(gameOver) break; 
            // }

            // if(gameOver) break; 
        }
        
        if(gameOver) break; 
        turn++;

        
    }
    if(gameOver) cout << turn; 
    else cout << -1; 

    return 0; 
}
/*
nxn 배열 
말 개수 k 

말을 올릴 수 있다 

턴 1번: 1번 말 ~ k번말 순서대로 이동 한 바퀴 
stop 지점: 말 개수 >= 4 인 곳 생기면 

3차원 배열인 듯 

각 칸 = vector<Horse>가 된다 
0 1.. n 이라고 할 때 > push_back이니까 n 쪽이 최신임 

이동하려는 칸 
1. 흰색 -> 나 포함 + 나보다 뒤에 있는 인덱스 애들 싹 다 고대로 뽑고 > 차례대로 push_back 
2. 빨강 -> 흰색이랑 똑같이 뽑고 > 뒤에서부터 push_back 
3. 파랑 -> A번 말의 방향 반대로 > 이동하려는 칸 = 파랑 -> stop / 아니면 1,2번 방식 그대로 진행 
4. 범위 벗어나면 -> 3번 함수 호출 
*/