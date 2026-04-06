import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<int[]> edgeList;
    static int[] parent;
    static int find(int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        if(aRoot > bRoot) parent[aRoot] = bRoot;
        else parent[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();

        int[] start = new int[3];
        for(int i=0; i<=m; i++){ // m(X) m+1(O)
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 그래프
            edgeList.add(new int[]{a, b, c});
        }

        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i; // 0도 초기화 필요
        // 최적의 경로: 내림차순 정렬 1-> 0
        edgeList.sort((a, b)-> {
            if(b[2] != a[2]) return b[2] - a[2];
            else if(a[0] != b[0]) return a[0]-b[0];
            else return a[1] - b[1];
        });
        int edgeCount = 0;
        int best = 0;
        for(int[] e: edgeList){
            int a = e[0], b = e[1], c = e[2];
            if(!union(a, b)) continue;
            edgeCount++; // 포함한 간선 개수
            if(c == 0) best++;
            if(edgeCount == n) break; // n-1 (X) n (O) 
        }

        for(int i=0; i<=n; i++) parent[i] = i;
        // 최악의 경로: 오름차순 정렬 0-> 1
        edgeList.sort((a, b)->{
            if(b[2] != a[2]) return a[2] - b[2];
            else if(a[0] != b[0]) return a[0]-b[0];
            else return a[1] - b[1];
        });
        edgeCount = 0;
        int worst = 0;
        for(int[] e: edgeList){
            int a = e[0], b = e[1], c = e[2];
            if(!union(a, b)) continue;
            edgeCount++; // 포함한 간선 개수
            if(c == 0) worst++;
            if(edgeCount == n) break;
        }

        System.out.println(worst*worst - best*best);

    }
}
/*
모든 정점 연결
간선 가중치가 최소

근데 간선 중심으로 풀이해야 함 -> 크루스칼

이때 최악의 피로도 최적의 피로도 모두 필요

근데 최악의 경로 -> 오르막이 우선시 (간선은 0->1)로 정렬
최선의 경로 -> 내리막이 우선시 (간선을 1->0)
 */