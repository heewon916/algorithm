#include <iostream> 
#include <vector> 
#include <queue> 
#include <cmath>
using namespace std; 


int main(){
    int n; 
    cin >> n; 

    auto comp = [](pair<int, int>& a, pair<int, int>& b){
        if(a.first != b.first) return a.first > b.first; // 내림차순 
        else return a.second > b.second; // 내림차순 
    };
    priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp) > pq(comp);
    int num; 
    vector<int> res; 
    // cout << "----\n";
    for(int i=0; i<n; i++){
        cin >> num; 
        if(num == 0){ // 제거 
            if(pq.empty()){
                // cout << 0 << "\n"; 
                res.push_back(0);
                continue; 
            }
            pair<int, int> num = pq.top(); pq.pop(); 
            res.push_back(num.second); 
            // cout << num.second << "\n"; 
        }else{ // 추가 
            pq.push({abs(num), num}); 
        }
    }
    // cout << "----\n";
    for(int i=0; i<res.size(); i++){
        cout << res[i] << "\n"; 
    }
    // system("pause"); 
    return 0; 
}
/*
n개의 수 
배열에 x 넣기 
절댓값이 가장 작은 값 

절댓값, 원래 값 
- 절댓값 오름차순 
- 원래 값 오름차순
*/