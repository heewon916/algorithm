
import java.io.*;
import java.util.*;
public class Main {
    static int n, m;
    static List<int[]>[] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, cost});
//            graph[b].add(new int[]{a, cost}); // 한쪽에만 저장해야 하는 이유: 단방향 그래프임!!
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 방문 배열 없이도 가능하다 홀리슅
//        boolean[] v = new boolean[n+1];
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a, b)-> a[1] - b[1]);
        q.add(new int[]{start, dist[start]});

        /**
         * 경로 역추적 하려면 내가 이전에 방문해야 하는 정점을 저장해둬야 한다
         */
        int parent[] = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        parent[start] = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0], cost = cur[1];
//            if(v[node]) continue;
            if(cost > dist[node]) continue;
//            route.add(node); // 그리고 큐에서 뺄 때 경로에 추가하면 안됨 -> 그게 최단경로라는 보장이 없음
//            v[node] = true;

            if(node == target) break;

            for(int[] adj: graph[node]){
                // !v[adj[0]] &
                if(dist[adj[0]] > cost + adj[1]){
                    dist[adj[0]] = cost + adj[1];
                    q.add(new int[]{adj[0], dist[adj[0]]});
                    parent[adj[0]] = node; // 여기서 업데이트
                }
            }
        }
//        int count = 0;
//        for(int i=1; i<=n; i++){
//            if(v[i]) count++;
//        }
        Stack<Integer> route = new Stack<>();
        int curr = target;

        /**
         * 경로 역추적
         */
        while(curr != 0){
            route.add(curr);
            curr = parent[curr];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dist[target]).append("\n").append(route.size()).append("\n");
        while(!route.isEmpty()){
            sb.append(route.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
/*
n개의 정점 10^3
m개의 간선 10^5

출발지 A 도착지 B
최소 비용, 경로

출발점과 도착점이 정해져있다면 mst 구하는 거 아니니까 프림이나 크루스칼 아니고
다익스트라나 dfs, bfs인데
 */