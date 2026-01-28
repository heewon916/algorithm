
import java.io.*;
import java.util.*;
public class Main{
    static int n, m, q;
    static ArrayList<Integer>[] g;
    static int[][] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        g = new ArrayList[n];
        for(int i=0; i<n; i++) g[i] = new ArrayList<>();

        dist = new int[n][n];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            g[a].add(b);
        }

        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], -1);
            dist[i][i] = 0;
        }

        for(int i=0; i<n; i++){
            bfs(i);
//            System.out.println(Arrays.toString(dist[i]));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int ans = search(u, v);
            if(ans == Integer.MAX_VALUE) sb.append(-1).append("\n");
            else sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    /**
     * 모든 정점에서 bfs를 돌려서 n번으로 각 정점 간의 최단 거리를 구해둔다.
     * 갈 수 없으면 -1임
     * @param node
     */
    static void bfs(int node){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[n];
        v[node] = true;
        q.add(new int[]{node, 0});
        while(!q.isEmpty()){
            int[] c = q.poll();
            int curr = c[0], depth = c[1];
            for(int adj: g[curr]){
                if(!v[adj]){
                    v[adj] = true;
                    q.add(new int[]{adj, depth+1});
                    dist[node][adj] = depth+1;
                }
            }
        }
    }

    /**
     * u와 v 둘다 방문할 수 있는 겹치는 정점을 찾고
     * 그 dist[u][x], dist[v][x] 중 max값을 찾는다.
     * @param u
     * @param v
     */
    static int search(int u, int v){
        boolean[] candidate = new boolean[n];
        // 겹치는 정점을 찾고
        for(int i=0; i<n; i++){
            if(dist[i][u] != -1 && dist[i][v] != -1){
                candidate[i] = true;
            }
        }
        // i->u, i->v 두 경로 중 최댓값을 구해서
        // 그런 최댓값 중 최솟값을 찾는 게임
        int max = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            if(candidate[i]){
                max = Math.min(max, Math.max(dist[i][u], dist[i][v]));
            }
        }
        return max;
    }
}
/*
아이디어:
그래프를 인접행렬로 구현할거야. 인접한 곳은 1로 표시할거야.
그러니까 map[i][i] = 1 일 거고 입력받은 두 수 a,b에 대해서도 map[a][b] = 1이야

그리고 쿼리 u v 를 입력으로 받고 search(u, v)로 넘길거야.
search에서 하는 일은
인접행렬에서 열 기준으로 u랑 v에서 1인 곳을 찾아
그곳들 중 중복이 되는 정점을 알아내
그 정점을 후보로 삼아서 u,v 각각에 대한 bfs를 또 돌리려고 하는데

누가 봐도 시간복잡도 터짐
--
전처리: 안에서 중복으로 처리하는 bfs를 밖으로 끄집어냄
모든 정점에 대해 최단경로를 계산해둠
못 가는 경우는 -1로 표시함

그러고 search 그대로 진행
주의: map으로 비교하면 안됨. dist로 산정해야 함
-- 
시간초과
인접행렬 문제점
 */