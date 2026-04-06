#include <string>
#include <vector>
#include <cmath> 

using namespace std;

int answer = 0; // 방문할 수 있는 최대 던전의 개수 
int b[9]; // 던전 개수 최대 8개
bool v[9]; 

void perm(int cnt, int& k, vector<vector<int>>& dungeons){
    if(cnt == dungeons.size()){
        int left = k; 
        for(int i=0; i<dungeons.size(); i++){
            int di = b[i];  // 현재 방문하는 던전 번호 
            if(left >= dungeons[di][0]){
                left -= dungeons[di][1]; // 소모 피로도만큼 뺀다 
                answer = max(answer, i+1); 
            } else{
                answer = max(answer, i); 
                return; 
            }
        }
        return; 
    }
    for(int i=0; i<dungeons.size(); i++){
        if(v[i]) continue; 
        v[i] = true; 
        b[cnt] = i; 
        perm(cnt+1, k, dungeons); 
        v[i] = false; 
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    answer = 0; 
    perm(0, k, dungeons); 
    return answer;
}
// 7! 이니까 방문 순서를 정하면 되지 않을까 
// 가다가 남은 k 체력 < 지금 방문해야 하는 던전의 최소 피로도 -> 더 못 가는 단계 
// 이때까지 방문한 던전 개수 누적 -> max() 처리하기 
// b[i] = 현재 던전 인덱스 
// 인자: 현재 남은 체력, 이때까지 방문한 던전 개수 누적하는 거,
// 인자가 필요없을지도 -> 그냥 perm 돌려서 정한 순서대로 게산하다가 더 못 가는 상황이면 그때까지 방문한 던전 개수로 max 비교하면 되잖아 