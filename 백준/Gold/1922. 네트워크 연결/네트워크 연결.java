
import java.util.*;
import java.io.*;
public class Main{
    static List<int[]> graph;
    static int n, m;
    static int parent[];
    static int find(int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        else {
            parent[aRoot] = bRoot;
            return true; 
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.add(new int[]{a, b, c});
        }
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        graph.sort((o1, o2) -> o1[2] - o2[2]);
        int answer = 0;
        for(int[] edge: graph){
            int a = edge[0], b = edge[1], cost = edge[2];
            if(union(a,b)) answer += cost;
        }
        System.out.println(answer);


    }
}
/*
모든 정점 연결 MST
N 1000
간선 개수 10^5
간선 주어짐
간선 중심 -> 크루스칼
 */