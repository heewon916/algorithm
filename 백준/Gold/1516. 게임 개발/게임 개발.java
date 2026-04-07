
import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[] indegrees;
    static int[] times;
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        indegrees = new int[n+1];
        list = new List[n+1];
        times = new int[n+1];
        for(int i=1; i<=n; i++) list[i] = new ArrayList<>();

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            // 입력: 건물 i를 짓는데 걸리는 시간 + 건물i를 짓기 전에 지어야 하는 건물들 node
            // node -> i
            while(st.hasMoreTokens()){
                int node = Integer.parseInt(st.nextToken());
                if(node == -1) break;
                indegrees[i]++;
                list[node].add(i);
            }
        }

        // 모든 건물을 짓는 게 가능한 상황
        // 그럼 indegree = 0 인 정점을 시작으로
        // 인접한 정점을 방문하되, 그 시간이 최소가 되는 조합이어야 함

        finTimes = new int[n+1];
        func();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) sb.append(finTimes[i]).append("\n");
        System.out.println(sb);
    }
    static int[] finTimes;
    static void func(){
        // 1. 진입차수가 0인 정점을 찾는다
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            if(indegrees[i] == 0) {
                finTimes[i] = times[i];
                q.add(i);
            }
        }

        // 2. 진입차수가 0인 정점들에 대해서 갈 수 있는 정점들의
        // indegree를 -1
        // 0이 된 곳은 q에 다음에 갈 수 있는 경로니까 추가한다
        while(!q.isEmpty()){
            int node = q.poll();
            // [!!] 내 실수: 여기서 1번만 finTimes를 업데이트함
            for(int next: list[node]){
                // finTimes[node]: 지금 막 지어진 건물까지의 총 소요시간
                // times[next]: 다음에 지을 건물의 자체 공사시간
                // [!!] min이 아니라 max다 -> 최소 시간이라고 해서 min? 그건 오산
                // 그리고 node에서 갈 수 있는 모든 곳들에 대해서 바꿔줘야 한다.
                finTimes[next] = Math.max(finTimes[next], finTimes[node]+times[next]);
                indegrees[next]--;
                if(indegrees[next] == 0) {
                    q.add(next);
                }
            }
        }
//        answer = Math.min(answer, totalTime);
    }
}
/*
진입차수 필요
각 지점을 처리하는데 걸리는 시간 저장
진입차수가 0인 곳이 여러개일 수도 있음

내 실수:
시작점에서 끝점까지 가는 여러 길 중 가장 짧은 길을 찾으려 했던 로직을 짬

하지만 이 문제는
모든 길이 합쳐져야만 다음으로 넘어갈 수 있는 로직

위상정렬
1. 모든 정점 중 진입차수가 0인 정점을 전부 큐에 넣고
2. 하나씩 뺄 때
- 그 녀석과 연결되어 있는 정점들의 진입차수를 1씩 뺴고
    - 뺀 게 0이 되면 걔를 q에 추가한다

여기서 추가된 로직은 시간 계산인데
각 정점의 완료시간을 구하는 거니까

한 정점을 뺄 때
- 그 녀석과 연결된 진입차수가 0이 되는 로직은 유지하되
- 거기에 또 왔을 때, 전에 계산됐던 마지막 총 소요시간과 지금 이 경로로 왔을 때의 총 소요시간을 비교해야 한다

 */