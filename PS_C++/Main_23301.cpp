#include <vector> 
#include <iostream> 
#include <algorithm> 
using namespace std; 

int n, t; 
int main(){
    cin >> n >> t; 

    vector<int> map(1001, 0);
    int k, s, e; 
    for(int i=0;i <n; i++){
        cin >> k; 
        for(int j=0; j<k; j++){
            cin >> s >> e; 
            for(int time=s; time<e; time++){
                map[time]++; 
            }
        }
    }
    for(int i=0; i<20; i++){
        cout << map[i] << " "; 
    }
    cout << "\n"; 

    
 
    vector<int> dp(1001, 0);
    dp[0] = map[0];
    cout << dp[0] << " ";  
    for(int i=1; i<t; i++){
        dp[i] = dp[i-1] + map[i];
        cout << dp[i] << " ";  
    } 
    int res = dp[t-1]; // 이게 문제였을 듯 하군 
    int start = 0, end = t; 
    for(int i=t; i<1001; i++){
        dp[i] = dp[i-1] + map[i] - map[i-t]; 
        if(i<20)cout << dp[i] << " "; 
        if(res < dp[i]){
            end = i+1; 
            start = end - t; 
            res = dp[i]; 
        }
    }
    cout << "\n";
    cout << start << " " << end; 
    system("pause"); 
    return 0; 
}
/*
T시간 진행 
참가자들의 시간 만족도 최대인 시간 

시간 만족도 = 스터디 시간 동안 각 참가자들이 참여하는 시간의 총 합 
*/