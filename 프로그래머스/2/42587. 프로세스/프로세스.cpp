#include <string>
#include <vector>
#include <algorithm> // max(), min() 함수 있음
#include <queue> 
using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    // 프로세스번호, 우선순위 값 
    queue<pair<int, int>> q;
    // 우선순위 값을 내림차순으로 갖고 있음 
    // pq는 기본적으로 내림차순 정렬; 최대 힙임 
    priority_queue<int> pq; 
    
    for(int i=0; i<priorities.size(); i++){
        q.push({i, priorities[i]});
        pq.push(priorities[i]);
    }
    
    /**
    맨 앞에 있는 애를 뽑고 
    얘의 우선순위 == maxP -> pop -> maxP 갱신 
    더 작으면 -> pop -> push
    */
    int seq = 1; 
    while(!q.empty()){
        pair<int, int> cur = q.front(); 
        q.pop(); 
        
        int curNo = cur.first; 
        int curP = cur.second; 
        int maxP = pq.top(); 
        
        if(curP == maxP){
            // maxP 갱신 
            pq.pop(); 
            maxP = pq.top(); 
            // 찾는 프로세스면 겟 
            if(curNo == location) return seq; 
            seq++; 
        }else{
            // push 
            q.push(cur);
        }
    }
    return answer;
}
/*
프로세스가 몇 번째로 실행되는지 알아내기

전체적으로 maxPriority 값을 유지해야 할 것 같은데? 

가장 큰 우선순위 값을 만나면 pop 
아니면 push 

pop했으면 우선순위 값도 그 다음으로 큰 애로 변경

우선순위 값: 내림차순 정렬 
*/