#include <string>
#include <vector>
#include <iostream> 
using namespace std;

// int dr[2] = {1, 0}; 
// int dc[2] = {0, 1}; 

/**
현재 위치; i, j에 대해서 
    오른쪽, 아래로만 이동하는데 물 웅덩이면 못 가고 
그러다 좌표가 m-1, n-1이면 끝 

-- 

dp .. 어디다 적용하냐 

각 지점마다 여기까지 올 수 있는 경로의 갯수를 누적하기? 
*/
// int count = 0; 
long DIV = 1000000007; 
// void dfs(int i, int j, int& m, int& n, vector<vector<int>>& map, vector<vector<bool>>& v){
//     if(i == m-1 && j == n-1){
//         count = (count + 1) % DIV; 
//         return; 
//     }
//     for(int d=0; d<2; d++){
//         int ni = i + dr[d]; 
//         int nj = j + dc[d]; 
//         if(ni<0 || ni>=m || nj<0 || nj>=n) continue; 
//         if(map[ni][nj] == -1) continue; // 물 웅덩이면 못 감 
        
//         map[ni][nj] = ? 이건 아닌 듯 
            
        
//         // if(v[ni][nj]) continue; // 이미 방문했으면 패스 
//         // v[ni][nj] = true;
//         // dfs(ni, nj, m, n, map, v); 
//         // v[ni][nj] = false; 
//     }
// }

int solution(int m, int n, vector<vector<int>> puddles) {
    vector<vector<int>> dp(n+1, vector<int>(m+1, 0)); 
    
    // count = 0; 
    for(vector<int> p: puddles){
        dp[p[1]][p[0]] = -1; // 물 웅덩이 표현하기 
    }
    dp[1][1] = 1; 
    
    /**
    아.. if문 미치 
    문제지점: (0,1)이 -1이어서 0 됐어 -> (0,2) 보는데 else if 구조면 i==0 || j==0 에 걸려서 무조건 1이 됨 (웅덩이 때문에 갈 방도가 없는데도 ㅇㅇ)
    해결방법 
    1. 그냥 n+1, m+1로 선언해서 자동 0이 처리되게 하기 => 얘가 가독성은 높겠죠
    2. int up = i==0? 0 : dp[i-1][j]; int left = j==0? 0 : dp[i][j-1]; dp[i][j] = (left + up) % DIV; 
    */
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(i == 1 & j == 1) continue; 
            // 물웅덩이는 갈 방법이 없으니 0 
            if(dp[i][j] == -1) {dp[i][j] = 0; continue;} 
            dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % DIV; 
        }
    }
    
//     for(int i=0; i<m; i++){
//         for(int j=0; j<n; j++){
//             cout << dp[i][j] << " "; 
//         }
//         cout << "\n"; 
//     }
    
    // dfs(0, 0, m, n, map, v); 
    
    return dp[n][m];
}