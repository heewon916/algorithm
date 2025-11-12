import java.util.*; 
// [[1, 2], [3, 4], [2, 4]]
class Solution {
    static int diff_cnt; 
    public int solution(int n, int[][] wires) {
        int[] connects = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1]; 
        for(int i=1; i<n+1; i++) graph[i] = new ArrayList<>(); 
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0]; 
            int b = wires[i][1]; 
            
            connects[a]++; 
            connects[b]++; 
            graph[a].add(b); 
            graph[b].add(a);
        }
        for(int i=1; i<=n; i++){
            System.out.println(i + ": " + graph[i]);
        }
        // ArrayList<Integer> candidates = new ArrayList<>();
        // int max_connects = 0; 
        // for(int i=0; i<connects.length; i++){
        //     if(max_connects < connects[i]) max_connects = connects[i]; 
        // }
        // for(int i=0; i<connects.length; i++){
        //     if(max_connects == connects[i]) candidates.add(i);   
        // }
        
        // 그래프에서 내가 해야 되는 건 candidates에 있는 정점들에 대해서 
        // 그 후보에 연결되어 있는 간선을 하나씩 없앴을 때 생기는 두 그룹의 정점 개수 차이 계산 
        diff_cnt = Integer.MAX_VALUE; 
        for(int point=1; point<=n; point++){
            int l = graph[point].size(); 
            boolean[] able = new boolean[n+1]; // point와 연결된 정점 개수만큼만 
            Arrays.fill(able, true); 
            System.out.println("후보: " + point);
            for(int adj: graph[point]){
                able[adj] = false; 
                System.out.println("- 연결 끊긴 정점: " + adj );
                bfs(able, graph, wires, n, point);
                able[adj] = true; 
            }
        }
        // for(int point: candidates){
        //     int l = graph[point].size(); 
        //     boolean[] able = new boolean[n+1]; // point와 연결된 정점 개수만큼만 
        //     Arrays.fill(able, true); 
        //     System.out.println("후보: " + point);
        //     for(int adj: graph[point]){
        //         able[adj] = false; 
        //         System.out.println("- 연결 끊긴 정점: " + adj );
        //         bfs(able, graph, wires, n, point);
        //         able[adj] = true; 
        //     }
        // }
        
        return diff_cnt;
    }
    static void bfs(boolean[] able, ArrayList<Integer>[] g, int[][] wires, int n, int start){
        boolean[] v = new boolean[n+1]; // 각 정점 방문 여부 
        ArrayDeque<Integer> q = new ArrayDeque<>(); 
        q.add(start);
        v[start] = true; 
        while(!q.isEmpty()){
            int cur = q.poll(); 
            
            for(int adj : g[cur]){
                boolean canVisit = true; 
                if(able[adj] == false){
                    canVisit = false; 
                }
                if(!v[adj] && canVisit){
                    q.add(adj);
                    v[adj] = true; 
                }
            }
        }
        
        int groupA_cnt = 0; 
        int groupB_cnt = 0; 
        System.out.print("방문한 정점: ");
        for(int i=1; i<v.length; i++){
            if(v[i]) {
                groupA_cnt++; 
                System.out.print(i + " "); 
            }
            else groupB_cnt++; 
        }
        System.out.println();
        System.out.println(Arrays.toString(v));
        System.out.println(groupA_cnt + " " + groupB_cnt);
        diff_cnt = Math.min(Math.abs(groupA_cnt - groupB_cnt), diff_cnt);
    }
}
/*
그래프를 무조건 2개로 나눔
근데 그룹이 갖고 있는 정점 개수가 최대한 비슷해야함 

그럼 조합으로 그냥 반으로 나누는데 

연결된 정점이 가장 많은 애를 기준으로 간선을 하나씩 없애는 건 어떨까 
1 2 3 4 5 6 7 8 9 
1 1 3 4 1 1 3 1 1

4번에서 간선 하나를 없앨 때 그랬을 때의 각 그룹이 갖게 되는 정점 개수 
그게 여러개면? 

1 2 3 4 5 6 7 
1 2 2 2 1 1 3 

7에서 하나씩 잘라봐야 함 

1 -- 2
     |
3 -- 4
*/