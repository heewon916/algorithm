#include <iostream> 
#include <vector> 
#include <map> 
#include <stack> 
using namespace std; 

int main(){
    int n,b; 
    int left; 
    cin >> n >> b; 
    map<int, char> cmap; 
    int start = 10; 
    char str = 'A'; 

    for(int i=0; i<26; i++){
        cmap[start+i] = str+i; 
    }

    string answer = ""; 
    stack<char> stk; 
    while(n>0){
        left = n % b; // n/b 하기 전에 먼저 나머지르르... 
        n /= b; 
        /**
         * 진법 변환할 때 주의점: n/b 하기 전에 먼저 나머지를 구해야 하고 
         * 차례대로 쌓은 걸 뒤집어 줘야 한다
         */
        if(left >= 10) stk.push(cmap[left]);  
        else stk.push(left + '0'); 
    }
    
    while(!stk.empty()){
        char cur = stk.top(); stk.pop(); 
        answer += cur; 
    }
    cout << answer ; 
    system("pause");
    return 0; 
}