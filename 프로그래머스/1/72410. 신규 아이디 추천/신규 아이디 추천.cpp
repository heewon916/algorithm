#include <string>
#include <vector>
#include <cctype>

using namespace std;

string solution(string new_id) {
    string answer = ""; 
    // 1. 소문자 치환 
    for(char& c: new_id){
        c = tolower(c);
    }
    // 2. 알파벳 소문자, 숫자, -, _, . 제외 모두 제거 
    string tmp; 
    for(char& c: new_id){
        if(isdigit(c) || islower(c) || c == '-' || c == '_' || c == '.')
            tmp += c; 
    }
    answer = tmp; 
    // 3. ... -> . 로 치환
    tmp = "";  
    for(char& c: answer){
        if(!tmp.empty() && tmp.back() == '.' && c == '.') continue; 
        tmp += c; 
    }
    answer = tmp; 
    // 4. 처음이나 끝에 . 이면 제거 
    if(!answer.empty() && answer.front() == '.') answer.erase(0, 1); 
    if(!answer.empty() && answer.back() == '.') answer.pop_back(); 
    
    // 빈 문자열이면 'a'대입 
    if(answer.empty()) answer = "a"; 
    
    // 5. 16자 이상 -> 16번째 문자부터 제거. -> 제거 후 . 가 맨 끝에 있으면 제거
    if(answer.length() >= 16){
        answer = answer.substr(0, 15); 
    }
    if(!answer.empty() && answer.back() == '.') answer.pop_back(); 
    
    // 6. 2자 이하 -> 마지막 문자 맨 끝에 3이 될때까지 반복해서 붙임 
    if(!answer.empty() && answer.length() <= 2) {
        char c = answer.back(); 
        while(answer.length() < 3){
            answer += c; 
        }
    }
    
    return answer;
}