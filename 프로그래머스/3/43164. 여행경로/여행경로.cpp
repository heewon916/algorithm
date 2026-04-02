#include <string>
#include <vector>
#include <unordered_map> 
#include <algorithm> 
#include <iostream>
using namespace std;

void dfs(unordered_map<string, vector<string>> map, string start, int visitCnt, int n){
    if(visitCnt == n){
        for()
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;
    unordered_map<string, vector<string>> map; 
    
    for(vector<string>& list: tickets){
        string s = list[0], e = list[1]; 
        map[s].push_back(e); 
    }
    
    for(auto& entry: map){
        cout << entry.first << ":"; 
        sort(entry.second.begin(), entry.second.end(), [](string a, string b){
           return a < b; // 오름차순 정렬  
        });
        for(string str: entry.second){
            cout << str << " "; 
        }
        cout << "\n";
    }
    

    dfs(map, "ICN", 1, map.size());
    
    
    return answer;
}
/*
간선 모두 사용
[a,b] a->b 뜻 
알파벳 오름차순 경로 리턴 
출발점 icn 
정점 개수 10000개 이하 

hashmap 
icn -> jfk 
jfk -> hnd
hnd -> iad 

icn -> atl, sfo
sfo -> atl 
atl -> icn, sfo

icn -> atl -> sfo -> atl 
*/