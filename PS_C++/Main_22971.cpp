#include <string> 
#include <iostream> 
#include <vector> 
#include <algorithm> 
#include <set> 
#include <map> 

using namespace std; 

int main(){
    int answer = 0; 

    int n, m; 
    cin >> n >> m; 

    vector<string> strings(n); // 접두사 검사 
    for(int i=0; i<n; i++){
        cin >> strings[i]; 
    }

    sort(strings.begin(), strings.end());

    for(int i=0; i<m; i++){
        string search; 
        cin >> search; 

        // 3. lower_bound로 search 이상의 값이 처음 나오는 위치 탐색
        // 이 함수 자체가 이진 탐색으로 동작합니다.
        auto it = lower_bound(strings.begin(), strings.end(), search); 

        // 4. 찾은 문자열이 search를 접두사로 포함하는지 확인
        // strings[j].find(search) == 0 와 같은 의미지만 더 안전함
        /**
         * .end()의 의미: 배열의 맨 끝 다음 칸(end)
         * it->: lower_bound가 찾아낸 위치에 있는 문자열 자체를 가리킵니다. (예: "apple")
         * .substr(0, search.size()): 해당 문자열의 **0번 인덱스(시작점)**부터 search의 길이만큼만 싹둑 자르라는 뜻입니다.
         */
        if(it != strings.end() && it->substr(0, search.size()) == search){
            answer++; 
        }
    }
    cout << answer; 
    system("pause"); 
    return 0; 

}
// 집합 S에 있는 문자열이 접두어가 되는지 확인한다 