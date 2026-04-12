#include <string>
#include <vector>
#include <iostream> 
#include <unordered_set> 
using namespace std;
vector<char> chars; 
unordered_set<int> nums; 
// void perm(int cnt, int len, vector<char>& b, vector<bool>& v){
//     if(cnt == len){
//         // set에 추가하기 
//         string tmp = ""; 
//         for(int i=0; i<b.size(); i++){
//             tmp += b[i]; 
//         }
//         nums.insert(stoi(tmp)); 
//         return; 
//     }
//     for(int i=0; i<chars.size(); i++){
//         if(v[i]) continue; 
//         b[cnt] = chars[i]; 
//         v[i] = true; 
//         perm(cnt+1, len, b, v); 
//         v[i] = false; 
//     }
// }

int answer = 0; 
bool checkPrime(string& str){
    if(str.size() == 0) return false; 
    int num = stoi(str); 
    if(num < 2) return false; 
    for(int i=2; i*i <= num; i++){
        if(num % i == 0) return false; 
    }
    return true; 
}
void dfs(string str, vector<bool>& v){
    if(checkPrime(str)) { // stoi(str) 를 넘겨 주고 싶겠지만, str = "" 빈 문자열일 떄 core dumped가 발생한다
        nums.insert(stoi(str)); 
        // return; // 탈출을 해버리면 2-> 23 이런 식으로 더 못 나아감 !!! 
    }
    for(int i=0; i<chars.size(); i++){
        if(v[i]) continue; 
        v[i] = true; 
        dfs(str + chars[i], v); 
        v[i] = false; 
    }
}

int solution(string numbers) {
    for(char c: numbers){
        chars.push_back(c); 
    }
    int n = chars.size(); 
    vector<bool> v(n, false); 
    vector<char> b(n, 0); 
    // perm(0, n, b, v); 
    dfs("", v); 
    
    return nums.size();
}
/**
numbers -> 자릿수 분리 
자릿수 순서 정하고 
그 순서대로 수를 만들었을 때 set에 저장
그 수가 소수이면 answer++ 
*/