#include <string>
#include <vector>
#include <iostream> 
#include <algorithm> // 이게 들어와야 하나봐 
#include <unordered_set> // 키의 존재 유무만 파악하면 되니까 

using namespace std;
/**
아이디어: 어떤 문자열이 다른 문자열의 접두사라면, 정렬 후에는 반드시 두 문자열이 바로 옆(인접)에 위치하게 됩니다.
*/
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    // 1. 사전 순(알파벳 순) 정렬
    // C++의 기본 sort는 문자열을 사전 순으로 정렬합니다.
    sort(phone_book.begin(), phone_book.end());

    // 2. 인접한 두 문자열만 비교하면 끝
    for (int i = 0; i < phone_book.size() - 1; i++) {
        // C++20 기준: 앞의 문자열이 뒤의 문자열 접두사인지 확인
        // if (phone_book[i+1].starts_with(phone_book[i])) return false;
        
        // C++11 이하 범용 호환 코드 (substr 대신 find를 써서 객체 생성 최소화)
        // 뒷 문자열의 0번째 인덱스에서 앞 문자열이 발견되는지 확인
        if (phone_book[i + 1].find(phone_book[i]) == 0) {
            return false;
        }
    }
    return true; // 끝까지 걸리지 않았다면 겹치는 접두사가 없음
}
// bool solution(vector<string> phone_book) {

//     sort(phone_book.begin(), phone_book.end(), [](string a, string b){
//         return a.length() < b.length();
//     });
    
//     unordered_set<string> hash_set; // 키만 저장하는 해시 자료구조 
    
//     for(string str: phone_book){
//         // 부분 문자열을 하나씩 잘라서 set에 있는지 확인한다 
//         for(int i=1; i<str.length(); i++){
//             // set.find()는 요소를 찾지 못하면 set.end()를 반환함
//             // 있으면 접두어가 있는 거니까 -> false 반환
//             if(hash_set.find(str.substr(0, i)) != hash_set.end()){
//                 return false; 
//             }
//         }
//         hash_set.insert(str);
//     }
    
    
//     return true;
// }