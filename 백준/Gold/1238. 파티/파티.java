
import java.util.*;
import java.io.*;
public class Main {
    static int n, m, x;
    static List<int[]>[] graph;
    static List<int[]>[] reverseGraph;
    public static void main(String[] args) throws Exception{
        StringTokenizer st = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        reverseGraph = new List[n + 1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        for(int i=1; i<=n; i++) reverseGraph[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, t});
            reverseGraph[b].add(new int[]{a, t});
        }

        int[] fromxdist = new int[n+1]; // x번 마을에서 각 마을까지의 최단 거리 -> 시작점 고정
        int[] reversedist = new int[n+1];
        // fromxdist 구하기
        dijk(fromxdist, graph);
        dijk(reversedist, reverseGraph);

        int max = 0;
        for(int i=1; i<=n; i++){
            max = Math.max(max, fromxdist[i] + reversedist[i]);
        }
        System.out.println(max);

    }
    static void dijk(int[] d, List<int[]>[] graph){
        Arrays.fill(d, Integer.MAX_VALUE);
        d[x] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[1] - o2[1]); // 정점, x정점과의 거리
        pq.add(new int[]{x, 0});
        boolean[] v = new boolean[n+1];
//        v[x] = true; // 여기서 방문처리 하면 뒤에서 q에 뭘 더 못 놓고 죽음
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curx = cur[0], curdist = cur[1];

            if(v[curx]) continue; // 여기가 포인트
            v[curx] = true;

            for(int[] adj: graph[curx]){ // 정점, x와의 거리
                if(d[adj[0]] > curdist + adj[1]){ // 여기서 방문 체크하고
                    d[adj[0]] = curdist + adj[1]; // 여기서 v[adj[0]] = true; 해버리면 더 짧은 경로 찾아도 못 옴
                    pq.add(new int[]{adj[0], d[adj[0]]});
                }
            }
        }
    }
}
/*
n개의 마을
n명의 학생
한 마을에 한 명

x번 마을에 모임

간선 m개 단방향
i번 간선 -> 비용 Ti

오고 가는데 가장 많은 시간을 소비한 사람?

n 1000 m 10000



 */
/*
이렇게 하면 이제 시간초과 뜸
// 학생별로 x까지의 최단 경로를 구해야 함
// bfs 불가능 - 가중치가 달라서 ㅇㅇ
int[] dist;
for(int std = 1; std <= n; std++){
    dist = new int[n+1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[std] = 0;
    v = new boolean[n+1];
    v[std] = true;
    pq = new PriorityQueue<>(
            (o1, o2) -> o1[1] - o2[1]); // 정점, x정점과의 거리
    pq.add(new int[]{std, 0});
    while(!pq.isEmpty()){
        int[] cur = pq.poll();
        int curx = cur[0], curdist = cur[1];

        if(v[curx]) continue; // 여기가 포인트
        v[curx] = true;

        for(int[] adj: graph[curx]){ // 정점, x와의 거리
            if(dist[adj[0]] > curdist + adj[1]){ // 여기서 방문 체크하고
                dist[adj[0]] = curdist + adj[1]; // 여기서 v[adj[0]] = true; 해버리면 더 짧은 경로 찾아도 못 옴
                pq.add(new int[]{adj[0], dist[adj[0]]});
            }
        }
    }
    fromxdist[std] += dist[std];
}
Arrays.sort(fromxdist);
System.out.println(fromxdist[fromxdist.length-1]);
 */