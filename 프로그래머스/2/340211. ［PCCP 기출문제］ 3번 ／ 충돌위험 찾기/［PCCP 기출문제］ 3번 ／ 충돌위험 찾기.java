import java.util.*; 
class Solution {
    public record Visit(int time, int r, int c) {} 
    public HashMap<Visit, Integer> map = new HashMap<>(); 
    
    // 시작점, 목적지, 출발시각/ rt값: 목적지 도착시각
    public int move(int[] s, int[] e, int time){
        // 맨허튼 거리로 이동 필요
        int sr = s[0], sc = s[1], er = e[0], ec = e[1]; 
        
        while(sr != er){
            sr += (er > sr)? 1: -1; 
            Visit visit = new Visit(time++, sr, sc);
            map.put(visit, map.getOrDefault(visit, 0) + 1);
        }
        
        while(sc != ec){
            sc += (ec > sc)? 1: -1; 
            Visit visit = new Visit(time++, sr, sc);
            map.put(visit, map.getOrDefault(visit, 0) + 1);
        }
        
        return time; 
    }
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        for(int[] route: routes){
            int time = 1; // 1대당 초기화
            
            // 처음 위치 기록 
            int[] start = points[route[0] - 1];
            Visit startVisit = new Visit(0, start[0], start[1]);
            map.put(startVisit, map.getOrDefault(startVisit, 0) + 1);
            
            // [2,3,4,5]
            for(int i=0; i<route.length-1; i++){
                // now: 2, nxt:3 -> now: 3, nxt:4
                int[] now = points[route[i]-1]; // 좌표 
                int[] nxt = points[route[i+1]-1];
                
                // now -> nxt로 가는데 경로 찾고, 해시맵에 기록                 
                time = move(now, nxt, time); 
            }
        }
        
        // 개수 세기 
        for (int v: map.values()){
            if(v > 1){
                answer++; 
            }
        }
        return answer;
    }
}

/**
n개의 포인트가 있고, 그 중에서 m개의 포인트로 경로가 구성되어 있음. 
그런 경로의 개수는 운송차 x개만큼 있음 
이동 경로는 맨헤튼 거리로 이동한다고 함 
동시간에 같은 위치에 있으면 충돌했다고 판단함 

그 시간 + 그 칸에 방문한 애들을 알 수 있으면 좋으련만 
그럼 각 칸마다 hashmap {[time, r, c]:int, count:int} 특정 초에 방문한 애들의 개수를 세면? 되지 않을까.. 

한 대가 이동하는 방식을 고려한다고 하면 
    r 먼저 이동한다고 했으니까 그거 먼저 이동하는 방식으로 탐색하는 함수 하나 만들기

한 대만 고려하면 
    그 경로의 길이가 최대 100
        그 다음 지점으로 이동한다고 했을 때 최대 200거리
    -> 2 * 10^4 
=> 최대 100대 => 2 * 10^6 

이러한 과정을 해시맵에 초 단위로 기록함 

그리고 해시맵에서 count > 1인 경우를 센다? 맞나 
*/ 