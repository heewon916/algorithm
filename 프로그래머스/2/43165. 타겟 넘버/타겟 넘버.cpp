#include <string>
#include <vector>
#include <algorithm> 
#include <iostream> 
using namespace std;

int answer = 0; 
void dfs(vector<int>& b, int index, int total, int target){
     // 이거 종료 조건 안 주면 메모리 침범 발생; C++에서는 Segmentation Fault라고 뜬다.
    // index = 0 .. b.size()-1까지 동작하고 b.size()가 됐을 때 total == target인지 계산이 필요하다 
    if(index > b.size()) return; 
    if(index == b.size()){
        if(total == target){
            answer++; 
            return;   
        } 
    }
    dfs(b, index+1, total + b[index], target);
    dfs(b, index+1, total - b[index], target);
}
int solution(vector<int> numbers, int target) {
    answer = 0; // 프로그래머스 환경에서 전역 변수 초기화는 필수 
    
    // 주의할 점
    // vector<int> b(numbers.size(), 0); 
    // 이후 for문으로 push_back하게 되면 
    // 0들 뒤에 숫자가 붙는다.
    vector<int> b; 
    for(int i=0; i<numbers.size(); i++){
        b.push_back(numbers[i]);
    }
    vector<bool> v(numbers.size(), false);
    dfs(b, 0, 0, target);
    return answer; 
}

/**
perm 돌려도 되는건지는 잘 모르겠다.. 
*/