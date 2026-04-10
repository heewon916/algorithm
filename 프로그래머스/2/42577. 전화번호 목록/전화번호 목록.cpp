#include <string>
#include <vector>
#include <iostream> 
#include <algorithm> 
using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    sort(phone_book.begin(), phone_book.end()); 
    
    for(int i=0; i<phone_book.size()-1; i++){
        // for(int j=i+1; j<phone_book.size(); j++){ 
        // 앞의 문자열이 뒤의 문자열의 접두사인지만 확인하면 된다. 
        if(phone_book[i+1].find(phone_book[i]) == 0){ 
            return false; 
        }
        // }
    }
    return true;
}
/*
접두어 

사전순 정렬
123 < 1234 

정렬을 먼저 하고 
앞에 있는 게 뒤에 있는 것들 중에서 찾았을 때 그 위치가 0이면 접두어겠네 

*/