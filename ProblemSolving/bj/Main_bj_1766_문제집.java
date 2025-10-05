import java.io.*; 
import java.util.*;
public class Main_bj_1766_문제집 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 문제 개수 
		int M = Integer.parseInt(st.nextToken()); // 정보 개수 
		
		int[] indegrees = new int[N+1];
		List<Integer>[] g = new List[N+1];
		for(int i=1; i<=N; i++) g[i] = new ArrayList<>(); 
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			g[from].add(to);
			indegrees[to]++; 
		}
		
		/**
		 * 1. 진입차수가 0인 애들 중에서, 값이 더 작은 애를 먼저 풀어야 한다. 
		 * 2. 걔가 사라지고 나면, 인접한 애들에 대해서 진입차수-1
		 * 3. 만약에 진입차수가 0이 되면 추가 
		 */
//		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			if(indegrees[i] == 0) pq.add(i);
		}
		boolean[] visited = new boolean[N+1];
		StringBuilder sb = new StringBuilder(); 
		while(!pq.isEmpty()) {
			int cur = pq.poll(); 
			visited[cur] = true; 
			sb.append(cur).append(' ');
			for(int a: g[cur]) {
				if(!visited[a]) {
					if(--indegrees[a] == 0) {
						pq.offer(a);						
					}
				}
			}
		}
		System.out.println(sb);
	}

}
