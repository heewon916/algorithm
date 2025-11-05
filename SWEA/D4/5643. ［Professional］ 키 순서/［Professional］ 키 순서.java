import java.util.*; 
import java.io.*; 
public class Solution {
	/**
	 * start 정점을 시작으로 방문할 수 있는 모든 학생들을 방문해보자. 
	 * @param start
	 * @param g
	 * @return
	 */
	public static int bfs(int start, ArrayList<Integer>[] g) {
		boolean[] v = new boolean[N+1]; // 방문 가능한 학생에는 true가 저장되어 있다 
		ArrayDeque<Integer> q = new ArrayDeque<>(); 
		q.add(start);
		v[start] = true; 
		while(!q.isEmpty()) {
			int cur = q.poll(); 
			
			for(int adj: g[cur]) {
				if(!v[adj]) {
					q.offer(adj);
					v[adj] = true; 
				}
			}
		}
//		System.out.println(Arrays.toString(v));
		int cnt = 0; 
		for(int i=1; i<N+1; i++) {
			if(v[i]) cnt++;
		}
		return cnt; 
	}
	public static int N, M; 
	public static ArrayList<Integer>[] g;
	public static ArrayList<Integer>[] reverse_g;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 학생 수 
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 비교 횟수 (그러니까 간선) 
			
			g = new ArrayList[N+1]; 
			reverse_g = new ArrayList[N+1]; 
			
			for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 
			for(int i=1; i<N+1; i++) reverse_g[i] = new ArrayList<>(); 
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				g[a].add(b); 
				reverse_g[b].add(a);
			}
			int ans = 0; 
			for(int no=1; no<N+1; no++) {		
//				System.out.println("학생 번호: " + no);
				int a = bfs(no, g);
				int b = bfs(no, reverse_g);
//				System.out.println(a + " " + b);
				if((a+b) == N+1) {
					ans++; 
//					System.out.println("-> 가능");
				}
			}
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		System.out.println(sb);
	}

}
/*
각 정점 기준으로 
bfs해서 내 앞에 몇 명 있고 
반대 방향으로 bfs해서 내 뒤에 몇 명 있는지 센 다음에 
그게 N-1명이면 나는 순서를 알 수 있는 사람인 것임 
*/