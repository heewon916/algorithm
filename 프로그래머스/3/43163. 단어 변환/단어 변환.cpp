#include <string>
#include <vector>
#include <iostream> 
#include <cmath> 
#include <climits>
using namespace std;

/**
1. 1인 애들에 대해서 dfs 타고 들어가기 
*/ 
int answer = INT_MAX;
bool v[51];  // 단어 개수가 50개 이하니까 
void dfs(int idx, int depth, int n, string target, vector<string>& words, vector<vector<int>>& map){
    if(idx == n+1) return; 
    if(words[idx] == target){
        answer = min(answer, depth);
        return; 
    }
    
    for(int i=0; i<n+1; i++){
        if(!v[i] && map[idx][i] == 1){
            v[i] = true; 
            dfs(i, depth+1, n, target, words, map);
            v[i] = false; 
        }
    }
}


int solution(string begin, string target, vector<string> words) {
    answer = INT_MAX; 
    int startIdx = 0, endIdx = -1; 
    for(int i=0; i<words.size(); i++){
        if(words[i] == target) endIdx = i; 
    }
    if(endIdx == -1) return 0; // 변환할 수 없는 경우에는 0을 리턴함 
    // 문자열 간의 문자 개수 차이
    int n = words.size(); 
    words.insert(words.begin(), begin);
    vector<vector<int>> map(n+1, vector<int>(n+1, 0));
    for(int i=0; i<n+1; i++){
        string a = words[i]; 
        for(int j=i+1; j<n+1; j++){
            string b = words[j]; 
            int diffCnt = 0; 
            for(int k=0; k<a.length(); k++){
                if(a[k] != b[k]) diffCnt++; 
            }
            map[i][j] = map[j][i] = diffCnt; 
        }
    }
    
    // start부터 1인 애들만 찾아 다니기 
    // 문자열 번호, depth
    v[0] = true;  // 방문 처리 안해주면 큰일남.. 무한재귀의 시작임 
    
    dfs(0, 0, n, target, words, map); 
    
    return answer; // return문이 없어도 signal: segmentation fault (core dumped) 이 뜰 수 있구나 
}

/*
2차원 배열 먼저 만들기 
단어 간의 개수 차이 구해서 값 채우기 

start 인덱스부터 시작해서 차이가 1인 곳으로만 방문할 수 있는 걸로 
*/