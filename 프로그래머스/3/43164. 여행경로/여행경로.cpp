#include <string>
#include <vector>
#include <unordered_map> 
#include <iostream>
#include <algorithm> 
using namespace std;

vector<string> answer; // 여러 루트를 저장해두는 곳 

// int&는 '수정 가능한 변수(L-value)'만 받을 수 있습니다. 하지만 cnt + 1은 계산 결과로 만들어진 **임시 값(R-value)**입니다.
bool dfs(string node, unordered_map<string, vector<pair<string, bool>>>& map, vector<string>& res, int cnt, int len){
    // 모든 간선을 다 사용하게 되면 그건 하나의 경로가 된다 
    if(cnt == len){
        answer = res; 
        return true; 
    }
    
    // node에 인접한 곳으로 경로를 탐색해본다. 
    // & 없이 선언하면 맵 안에 있는 데이터를 복사해 옵니다.
    // adj.second = true;라고 백날 수정해도 원본 맵의 티켓은 여전히 false인 상태입니다.
    // 반드시 for(auto& adj : map[node]) 처럼 참조자를 써야 맵의 원본 데이터가 수정됩니다.
    for(pair<string, bool>& adj: map[node]){
        if(adj.second) continue; 
        res.push_back(adj.first);  // 여기서 node가 아니라 adj.first를 해야 함 ㅇㅇ 
        adj.second = true; 
        if(dfs(adj.first, map, res, cnt+1, len)) return true;
        res.pop_back(); 
        adj.second = false; 
    }
    return false; 
}

vector<string> solution(vector<vector<string>> tickets) {
    // 전역변수 초기화 
    answer.clear(); 
    
    unordered_map<string, vector<pair<string, bool>>> map; 
    
    // 1. 맵: 정점 -> 갈 수 있는 정점 리스트 
    for(vector<string>& l: tickets){
        string a = l[0], b = l[1]; 
        map[a].push_back({b, false}); 
    }
    // 2. 키, 값 -> 값에 대해 오름차순 정렬 
    for(auto& entry: map){
        sort(entry.second.begin(), entry.second.end(), [](pair<string, bool> a, pair<string, bool>  b){
           return a.first < b.first; 
        });
        // cout << entry.first << " : "; 
        // for(string str: entry.second){
        //     cout << str << " "; 
        // }
        // cout << "\n";
    }
    
    vector<string> result; 
    result.push_back("ICN");
    dfs("ICN", map, result, 0, tickets.size());
    
    return answer; 
    // 2. icn을 출발점으로 하는 
    // 모든 정점을 방문하는 경로를 탐색해야 한다. 
    // 근데 최단경로는 아님 다 방문이 중요한 상황 
    // 그럼 dfs가 맞지 않나 싶은데
    // dfs(??);
}

/*
간선 모두 사용
[a,b] a->b 뜻 
알파벳 오름차순 경로 리턴 
출발점 icn 
정점 개수 10000개 이하 
주어진 경로가 2개 이상일 때 알파벳 오름차순으로 해야 한다라 
그럼 경로를 어떻게 저장하느냐가 고민이 되는 거네 
자바라면 어떻게 하지 
hashmap 
key value
string -> arrayList로 갈 듯 (배열오름차순으로? 아니면 pq 갈 수 있는 정점을 오름차순으로 정렬하면 되지 않을까 하는 마인드 )
hashmap 
icn -> jfk 
jfk -> hnd
hnd -> iad 

icn -> atl, sfo
sfo -> atl 
atl -> icn, sfo
icn -> atl -> sfo -> atl 
*/