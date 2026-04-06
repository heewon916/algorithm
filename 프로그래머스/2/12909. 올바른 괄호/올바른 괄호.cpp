#include<string>
#include <iostream>
#include <stack>
using namespace std;

bool solution(string s)
{
    stack<char> stk; 
    for(int i=0; i<s.size(); i++){
        // string s의 특정 인덱스 s[i]: char type이다
        // 그래서 "" 가 아니라 ''를 써야 한다
        if(s[i] == '(') stk.push(s[i]);
        else{
            // ")" 만나서 pop하러 왔는데 비어있음 사고 
            if(stk.empty()) return false; 
            // stack.pop() 리턴 타입은 void임 
            // stack.top();을 하고 pop() 해야 됨
            char popped = stk.top(); 
            stk.pop(); 
            // "(" 이게 아니면 또 사고 
            if(popped != '(') return false;  
        }
    }
    // 다 돌고 왔는데 큐가 안 비어있으면 또 사고
    if(!stk.empty()) return false; 
    return true;
}