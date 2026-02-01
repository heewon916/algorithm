package bj;
import java.io.*;
import java.util.*;
public class Main_bj_2572 {
    static int n, m, k;
    static class Road{
        int node;
        char color;

        public Road(int node, char color) {
            this.node = node;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Road{" +
                    "node=" + node +
                    ", color=" + color +
                    '}';
        }
    }
    static ArrayList<Road>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        char[] color = new char[n];
        char[] input = br.readLine().toCharArray();
        for(int i=0; i<n; i++){
            color[i] = input[i];
        }
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        g = new ArrayList[m+1];
        for(int i=1; i<=m; i++) g[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            char c = st.nextToken().toCharArray()[0];
            g[s].add(new Road(e, c));
            g[e].add(new Road(s, c));
        }
        int maxScore = 0;
        for(Road r: g[1]){
            dfs(r, 1);
        }
    }
    static void dfs(Road r, int cnt){
        if(cnt == n){
            // n장의 카드
            return;
        }
        for(Road adj: g[r.node]){
            dfs(adj, cnt+1);
        }
    }
}
