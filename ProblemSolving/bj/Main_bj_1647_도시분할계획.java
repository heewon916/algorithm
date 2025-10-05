import java.io.*; 
import java.util.*; 
public class Main_bj_1647_도시분할계획 {

	/**
	 * 배운 점: 
	 * 임의의 두 집 사이에 경로가 항상 존재한다. => 집들은 최소 한 가지 길로는 연결되어 있다. 
	 * 마을에는 집이 하나 이상 있어야 한다 => 꼭 반반으로 쪼개지 않아도 된다. 
	 * 마을 끼리는 연결 필요 없음 
	 * 마을 안에 있는 집들은 서로 연결되어 있어야 한다. => 임의의 두 집 사이에 경로가 항상 존재해야 한다. 
	 * 
	 * => 전체 정점에 대해서, 가중치 합이 최소가 되도록 하고, 그 중에서 가장 가중치가 큰 경로만 제거하면 그게 답이 된다. 
	 * 
	 * 이 문제에서 가장 중요한 포인트는 
	 * 1. 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재한다. 
	 * 2. 전체 길들의 가중치 합이 최소가 되어야 한다. 
	 * 
	 * => 다익스트라가 아닌, 최소 스패닝 트리를 구하면 되는 문제였다. 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<int[]>[] g = new List[N+1];
		for(int i=1; i<=N; i++) g[i] = new ArrayList<>(); 
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			g[A].add(new int[] {B,C});
			g[B].add(new int[] {A,C});
		}
		
		boolean[] v = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1])); 
		PriorityQueue<Integer> result_path = new PriorityQueue<>((o1, o2) -> o2-o1); 
		
		int[] prim = new int[N+1]; 
		Arrays.fill(prim, Integer.MAX_VALUE);
		prim[1] = 0; 
		pq.add(new int[] {1, prim[1]});
		int cnt = 0; 
		while(!pq.isEmpty()) {
			int[] cur = pq.poll(); 
			int minVertex = cur[0]; 
			int min = cur[1]; 
			if(v[minVertex]) continue; 
			v[minVertex] = true; 
//			System.out.println(minVertex + "-> ");
			result_path.offer(min);
			
			if(++cnt == N) break; 
			
			for(int[] adj : g[minVertex]) {
				if(!v[adj[0]] && prim[adj[0]] > adj[1]) {
					prim[adj[0]] = adj[1]; 
					pq.offer(new int[] {adj[0], prim[adj[0]]});
				}
			}
			
		}
		result_path.poll(); 
		int answer = 0; 
		while(!result_path.isEmpty()) {
			int diff = result_path.poll(); 
			answer += diff;
//			System.out.println("diff = " + diff);
		}
		System.out.println(answer);
	}

}
