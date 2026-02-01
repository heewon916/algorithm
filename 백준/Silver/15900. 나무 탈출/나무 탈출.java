import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[] cnt;
    static ArrayList<Integer>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        cnt = new int[n+1];
        g = new ArrayList[n+1];
        for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cnt[a]++;
            cnt[b]++;
            g[a].add(b);
            g[b].add(a);
        }

        long depthSum = 0;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        boolean[] v = new boolean[n+1];
        long[] depth = new long[n+1];
        v[1] = true;
        depth[1] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int adj: g[cur]){
                if(!v[adj]){
                    v[adj] = true;
                    depth[adj] = depth[cur] + 1;
                    q.add(adj);
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(cnt[i] == 1 && i != 1){
                depthSum += depth[i];
            }
        }
        System.out.println(depthSum % 2 == 1 ? "Yes" : "No");
    }
}