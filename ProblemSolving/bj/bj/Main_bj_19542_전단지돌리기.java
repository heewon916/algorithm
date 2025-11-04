package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_19542_전단지돌리기 {
	static int N, S, D; 
	static ArrayList<Integer>[] g; 
	static void input(BufferedReader br, StringTokenizer st) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		v = new boolean[N+1];
		g = new ArrayList[N+1]; 
		for(int i=0; i<=N; i++) g[i] = new ArrayList<>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g[a].add(b);
			g[b].add(a);
		}
	}
	/**
	 * 현재 위치한 정점 번호 node에 대해서 
	 * 인접하면서 거리가 D 이하인 노드 && 아직 방문안한 노드가 있다면 
	 * 그 노드를 방문처리한다. 
	 * @param node
	 */
	static void visit(int node) {
		for(int adj: g[node]) {
			if(!v[adj]) {
				int d = dist(node, adj);
				if(d <= D) {
					v[adj] = true; 	
					visitedCnt++;
				}
			}
		}
	}
	/**
	 * 정점 node와 인접한 정점 adj 사이의 최단 경로 계산 
	 * @param node
	 * @param adj
	 * @return
	 */
	static int dist(int node, int adj) {
		ArrayDeque<int[]> dq = new ArrayDeque<>(); 
		boolean[] vis = new boolean[N+1];
		dq.add(new int[] {node, 0}); // 정점 번호, 거리 
		vis[node] = true; 
		while(!dq.isEmpty()) {
			int[] cur = dq.poll(); 
			int n = cur[0]; 
			int d = cur[1]; 
			
			if(n == adj) return d; 
			for(int a: g[n]) {
				if(!vis[a]) {
					dq.offer(new int[] {a, d+1});
					vis[a] = true; 
				}
			}
		}
		return Integer.MAX_VALUE; // 방문 못하는 정점이면 무한대를 리턴 
	}
	static boolean[] v; 
	static int ans; 
	static int visitedCnt; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		input(br, st);
		
		ans = 0; 
		visitedCnt = 0; 
		/*
		 * 모든 정점을 방문하면 끝 
		 */
		while(true) {
			if(visitedCnt == N) break; 
			// 출발정점을 기준으로 visit 
			visit(S); 
			
			// 그 다음으로 이동할 정점은 
			// 방문 안 한 정점 && 그 정점과 연관된 정점 개수가 최대인 && 거리는? 
			/**
			 * 거리도 최소이면서 연관된 정점의 개수가 가장 많기를 바라는데 
			 */
			ArrayList<int[]> list = new ArrayList<>(); 
			for(int i=1; i<=N; i++) {
				if(!v[i]) list.add(new int[] {i, 0, 0}); // 정점 번호, 거리, 연관된 정점 개수 
			}
			for(int i=0; i<list.size(); i++) {
				
			}
		}
	}

}
/**
출발점에서 출발해서 모든 노드를 방문하고 출발점으로 돌아오는 것 
- 이때 현재 노드에서 거리가 D 이하인 정점은 모두 방문 처리가 가능하다 
최소한만 이동해서 모두 방문하고 싶은 것임
최단 거리는? 

정점 개수 10^5
출발점: 위의 것 중 하나 
힘 D: 10^5

입력: 정점 개수N, 출발점 번호, 힘 

그럼 출발점 기준으로 
	현재 노드에서 거리가 D 이하인 모든 노드 방문처리 후, 
	
	방문 안된 노드 중 가장 가까운 노드로 이동? 
	(근데 그리디가 최적이 맞을까.) 
	
	모든 노드가 방문 처리되었을 경우에는 출발점으로의 최단 경로만 더해주면 된다. 
*/