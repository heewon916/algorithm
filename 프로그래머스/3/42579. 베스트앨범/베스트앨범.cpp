#include <string>
#include <vector>
#include <algorithm> 

using namespace std;

vector<int> solution(vector<string> genres, vector<int> plays) {
    // 1. 장르별 총 재생 횟수 구하기 
    unordered_map<string, int> hmap; 
    // 2. 장르별 {재생횟수, 고유번호} 순으로 리스트 관리 
    unordered_map<string, vector<pair<int, int>>> playMap;
    for(int i=0; i<genres.size(); i++){
        hmap[genres[i]] += plays[i]; 
        playMap[genres[i]].push_back({plays[i], i});
    }
    
    // 3. 장르 순서 구하기 
    vector<pair<string, int>> sortHmap(hmap.begin(), hmap.end()); 
    sort(sortHmap.begin(), sortHmap.end(), [](const auto& a, const auto& b){
        if(a.second != b.second) return a.second > b.second; // 내림차순 
        return a.first < b.first; 
    });
    
    // 4. 장르별 곡 안에 정렬하기 
    for(auto& entry: playMap){
        auto& a = entry.second; 
        sort(a.begin(), a.end() ,[](const auto& a, const auto& b){
            if(a.first != b.second) return a.first > b.first; // 재생횟수는 내림차순
            else return a.second < b.second; // 고유번호는 오름차순 
        });
    }
        
    // 5. 장르별로 2곡씩 땡겨오기 
    vector<int> answer;
    for(auto entry: sortHmap){
        string genre = entry.first;
        vector<pair<int, int>> temp = playMap[genre]; 
        int len = (2 < temp.size())? 2: temp.size();  // 2곡만 담아야 함 
        for(int i=0; i<len; i++){
            answer.push_back(temp[i].second); 
        }
    }
    return answer;
}
/*
장르: 총 재생횟수 -> 
장르: {재생횟수, 고유번호}
*/