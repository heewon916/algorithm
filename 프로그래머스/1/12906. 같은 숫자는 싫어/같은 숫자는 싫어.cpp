#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    int lastnumber = arr[0]; 
    answer.push_back(lastnumber);
    for(int i=1; i<arr.size(); i++){
        if(lastnumber != arr[i]){
            answer.push_back(arr[i]);
            lastnumber = arr[i]; 
        }
    }
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    cout << "Hello Cpp" << endl;

    return answer;
}
/*
연속적으로 나타나는 숫자 제거하려면
마지막으로 나타난 숫자를 알고 있어야 함 

*/