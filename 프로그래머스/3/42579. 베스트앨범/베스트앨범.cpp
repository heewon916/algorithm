#include <string>
#include <vector>
#include <iostream> 
#include <unordered_map> 
#include <algorithm>
using namespace std;
struct Song{
    int songNo; 
    int playCnt; 
};
// vector: 단일 요소만을 이어붙인다. 
// vector<string, int> 이런 구조는 없다 

vector<int> solution(vector<string> genres, vector<int> plays) {
    unordered_map<string, int> genreMap;
    unordered_map<string, vector<Song>> playMap; 
    for(int i=0; i<genres.size(); i++){
        genreMap[genres[i]] += plays[i]; 
        playMap[genres[i]].push_back({i, plays[i]}); // 구조체 형태로 삽입해야 한다. {} 
    }
    
    // map은 정렬이 불가능하다 순서 개념 자체가 없기 때문이다 
    // 따라서 map으로 만든 데이터를 정렬하고 싶으면 vector로 바꿔서 진행해줘야 한다 
    // vector: ArrayList/ pair: <?, ?> 딱 2개만 담을 수 있는 배열 -> 그래서 first, second 로 접근한다 
    vector<pair<string, int>> genreList(genreMap.begin(), genreMap.end());
    
    sort(genreList.begin(), genreList.end(), [](pair<string, int> a, pair<string, int> b){
        return a.second > b.second; 
    });
    
    vector<int> answer; 
    // & 얘를 붙이면 위치 복사라서 무지성 복사가 안 일어난다고 함 
    for(const auto& pair: genreList){
        string genre = pair.first; 
        vector<Song> songs = playMap[genre];
        
        // Song을 이제 정렬해야 함 
        sort(songs.begin(), songs.end(), [](Song& a, Song& b){
           if(a.playCnt == b.playCnt){
               return a.songNo < b.songNo; // 고유 번호는 오름차순 정렬 
            }
            return a.playCnt > b.playCnt; // 재생 횟수는 내림차순 정렬 
        });
        
        int cnt = 0; 
        for(Song& s: songs){
            answer.push_back(s.songNo); 
            cnt++; 
            if(cnt == 2) break; 
        }
    }
    
    return answer; 
    
    
}

/**
나열 순서: 장르의 총 재생횟수 내림차순 > 장르 내 노래별 재생 횟수 내림차순 > 고유 번호 오름차순 

1. 장르: 총 재생횟수
2. 장르별로 노래들 -> 재생횟수로 정렬 장르: <노래, 횟수> -> 횟수로 내림차순 > 고유 번호 오름차순
*/