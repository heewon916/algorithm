#include <string>
#include <vector>
#include <unordered_map> 
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    unordered_map<string, int> map; 
    
    // 참가자 추가: C++에서는 맵에 없는 키에 접근할 때 기본값이 0으로 초기화된다 
    for(string name: participant){
        map[name]++; 
    }
    
    // 완주자 제거 
    for(string name: completion){
        map[name]--; 
    }
    
    // 값이 0보다 크면 완주하지 못한 사람임 
    for(auto entry: map){
        if(entry.second > 0){
            return entry.first; 
        }
    }
    return ""; 
}