#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int total = brown + yellow; 
    for(int width=total; width>=1; width--){
        if(total % width == 0){
            // 약수이면 진행 
            int height = total / width; 
            int side = width + height - 2; 
            if(side * 2 == brown) {
                answer.push_back(width); 
                answer.push_back(height); 
                return answer; 
            }
        }
    }
    return answer;
}