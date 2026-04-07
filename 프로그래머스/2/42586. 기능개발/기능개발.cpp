#include <string>
#include <vector>
#include <iostream>
#include <cmath> 

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> left;  // 배포까지 남은 날 수 
    for(int i=0; i<progresses.size(); i++){
        int days = ceil((double)(100-progresses[i])/speeds[i]);
        left.push_back(days);
    }
    
    for(int a : left){
        cout << a << " "; 
    }
    cout << " \n ";
    
    int total = 0 ; 
    int lastMax = left[0]; 
    int groupCnt = 1; 
    vector<int> answer; 
    for(int i=1; i<left.size(); i++){
        if(lastMax >= left[i]) groupCnt++;
        else{ // left[i-1] < left[i]
            answer.push_back(groupCnt); 
            lastMax = left[i]; 
            groupCnt = 1; 
        }
        total++; 
    }
    if(total < progresses.size()){
        answer.push_back(groupCnt);    
    }
    
    
    for(int i: answer){
        cout << i << " ";
    }
    
    return answer; 
}
/*
진도가 100일 때 서비스에 반영 

개발속도 다 다름 

앞에 있는 기능들이 다 배포되어야 뒤에 있는 기능도 배포 가능함 

배포 순서 + 현재 작업 진도 배열 progresses 
각 작업의 개발 속도 speeds 

각 배포때마다 몇 개가 100 이상을 찍는지? 

각 작업별 배포하는데 걸리는 최소 날짜 수
- 각 작업별 남은 작업량 / 개발 속도 + 1 (몫 + 1)

93 30 55 
1  30 5
7  70 45

7  3  9 
7 3 | 9 

95 90 99 99 80 99 
5  10 1  1  20 1
1  1  1  1  1  1

5  10 1  1  20 1
5 | 10 1 1 | 20 1 
*/