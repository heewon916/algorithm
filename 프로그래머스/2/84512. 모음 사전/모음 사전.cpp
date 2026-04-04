#include <string>
#include <vector>

using namespace std;
string seq = "AEIOU";
int cnt = 0; 
int answer = 0; 
// target은 변화 없이 계속 참조만 하니까 굳이 값 전달 안하고 주소만 전달해도 충분하다 
void dfs(string& target, string current){
    // 1. target == current 면 끝나고 
    if(target == current) {
        answer = cnt; 
        return; 
    }
    if(current.length() == 5) return; 
    for(int i=0; i<5; i++){
        if(answer != 0) return; 
        cnt++; 
        dfs(target, current + seq[i]); 
    }
}

int solution(string word) {
    answer = 0;
    cnt = 0; 
    string start = "A"; 
    dfs(word, ""); 
    return answer;
}
/*
근데 이 문제는 완탐이면 string = "A"부터 시작해서 뒤에 하나씩 붙이다가 
길이가 5가 되면 
    index = 4로 두고 거기를 E -> U로 바꾸고 
    만약 이게 또 끝나면 
    index = 3으로 두고 
길이가 4가 돼서 그 자리에서 또 E->U 이렇게 도는 거면 stack 쓰면 되지 않을까?
*/