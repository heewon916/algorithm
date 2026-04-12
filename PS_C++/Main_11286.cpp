#include <iostream> 
#include <vector> 
#include <queue> 
using namespace std; 

int main(){
    int n; 
    cin >> n;

    auto comp = [](auto& a, auto& b){
        if(a.first != b.first) return a.first > b.first; // 최대힙 -> 내림차순으로 해야 최소힙
        else return a.second > b.second; 
    };

    priority_queue<pair<int, int>, vector<pair<int ,int>>, decltype(comp)> pq(comp); 
    
    vector<int> res; 
    int num; 
    for(int i=0; i<n; i++){
        cin >> num; 
        if(num != 0){ // 값 추가 
            pq.push({abs(num), num});
        }else { // 값 제거 
            auto cur = pq.top(); pq.pop(); 
            res.push_back(cur.second); 
        }
    }

    for(int i=0; i<res.size(); i++){
        cout << res[i] << "\n";
    }
    system("pause"); 
    return 0; 
}
/*
1. 정수 절댓값을 넣는다 
2. 0 -> 가장 작은 값 빼기 -> 배열에서 제거
- 절댓값 가장 작은 걸 빼고 
- 그것마저도 같으면 원래 값이 작은 거 
*/