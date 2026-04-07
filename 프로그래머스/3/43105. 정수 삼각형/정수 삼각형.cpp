#include <string>
#include <vector>
#include <cmath> 
using namespace std;
long dp[501][501]; 
int solution(vector<vector<int>> triangle) {
    dp[0][1] = triangle[0][0]; 
    int h = triangle.size(); 
    int maxW = triangle[h-1].size(); 
    for(int r=1; r<h; r++){
        for(int c=1; c<=triangle[r].size(); c++){
            dp[r][c] = max(triangle[r][c-1] + dp[r-1][c-1], triangle[r][c-1] + dp[r-1][c]); 
        }
    }
    long answer = 0;
    for(int i=0; i<maxW; i++){
        answer = max(answer, dp[h-1][i]); 
    }
    
    return answer;
}