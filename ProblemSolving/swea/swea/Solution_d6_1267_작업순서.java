package swea;
import java.util.*; 
import java.io.*; 
public class Solution_d6_1267_작업순서 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		for(int tc=1; tc<=10; tc++) {
			sb.append("#").append(tc).append(" "); 
			st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] g = new ArrayList[V+1];
			for(int i=1; i<V+1; i++) g[i] = new ArrayList<>(); 
			
			int[] indegree = new int[V+1];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				g[a].add(b);
				indegree[b]++; 
			}
			
			ArrayDeque<Integer> dq = new ArrayDeque<>(); 
			for(int i=1; i<V+1; i++) {
				if(indegree[i] == 0) {
					dq.offer(i);
				}
			}
			
			while(!dq.isEmpty()) {
				int node = dq.poll(); 
				sb.append(node).append(" ");
				
				for(int adj: g[node]) {
					indegree[adj]--; 
					if(indegree[adj] == 0) {
						dq.offer(adj);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
