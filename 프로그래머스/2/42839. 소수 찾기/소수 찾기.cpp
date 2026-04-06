#include <string>
#include <vector>
#include <set> 
#include <iostream> 
using namespace std;
// bool v[] = new bool[8]; // 해당 문자를 사용했는지 안했는지 표기 
bool v[8]; // 이렇게만 선언하면 돼.. 
set<int> results; 
/*
웬만해서는 전역 변수로 선언해서 처리하는 게 편하고, 
함수 인자로 값을 넘길 때 
- 걔를 함수 외부에서도 반영되는 변수일 때는 &를 붙이고, 
- 재귀할 때처럼 잠깐 쓰고 없어도 되는 값이면 & 붙이지 않기 

문자열 -> 숫자로 바꿀 때는 
string num 일 때 num.size() != 0이 아닐 때만 stoi(num)이 동작한다는 걸 잊지 마 
*/
bool checkPrime(string& num){
    if(num.size() == 0) return false; 
    int n = stoi(num); 
    if(n < 2) return false; 
    for(int i=2; i*i <= n; i++){
        if(n % i == 0) return false; 
    }
    return true; 
}


// 재귀로 값을 전달할 때는 & 연산자를 붙이지 않는다 
void dfs(string num, vector<char>& arr){
    if(checkPrime(num)) {
        results.insert(stoi(num)); 
    }
    for(int i=0; i<arr.size(); i++){
        if(v[i]) continue; 
        v[i] = true; 
        dfs(num+arr[i], arr); 
        v[i] = false; 
    }
}


int solution(string numbers) {
    // 1. 문자 배열로 만든다 
    vector<char> arr; 
    for(char c: numbers){
        arr.push_back(c); 
    }
    // 2. 부분집합 함수로 만들 수 있는 숫자들을 만들고 소수인지 체크한다 
    // c언어에서는 string을 인자로 넘겨서 문자를 하나씩 추가해도 괜찮다. 객체 생성이 아니라는 점이 좀 새로울지도 
    dfs("", arr); 
    
    // 3. 소수라면 set 자료구조에 넣어서 저장한다 

    return results.size();
}