#include <string>
#include <vector>
#include <unordered_map> 
#include <iostream> 
using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    
    unordered_map<string, int> map; 
    
    for(vector<string> a : clothes){
        map[a[1]]++; 
    }
    
    for(auto entry: map){
        answer *= (entry.second + 1);
    }
    
    return answer-1; // 아무것도 안 입지는 않으니까 -1 
}