package bj;
import java.io.*;
import java.util.*;
public class Main_bj_31871 {
    static int n;
    static int m;
    static boolean[] v;
    static int answer = -1;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = Math.max(map[a][b], c);
        }

        v = new boolean[n+1];
        v[0] = true;
        dfs(0, 0, 0);
        System.out.println(answer);

    }

    static void dfs(int node, int cnt, int time){
        if(cnt == n){ // 모두 방문하면
            if(map[node][0] == 0) return; // 정문 복귀 불가
            answer = Math.max(answer, time + map[node][0]);
            return;
        }
        for(int i=1; i<=n; i++){
            if(v[i]) continue;
            if(map[node][i] != 0){
                v[i] = true;
                dfs(i, cnt+1, time+map[node][i]);
                v[i] = false;
            }


        }
    }
}
/*
N 9
모든 놀이기구를 1번씩만 방문
정문에서 시작해서 정문으로 돌아와야 됨
두 정점을 잇는 간선이 여러 개임
최장 시간이 필요함

그럼 정문부터 시작해서 어떤 순서로 방문할 건지가 중요해짐
그리고 그 정점을 방문할 때 어떤 간선을 사용할지도 중요해짐 보통은 더 큰 값을 사용해야 할 듯

결국 어떤 순서니까 정점이 최대 9개 -> 9!  362000

다익스트라: 한 정점에서 다른 정점으로의 최단 경로 + 정점 간의 최단 거리
 */