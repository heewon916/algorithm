package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_5427_불 {
	static int[] di = {-1, 1, 0, 0}; 
	static int[] dj = {0, 0, -1, 1}; 
	static int[][] graph;
	static int R, C; 
	static ArrayDeque<int[]> fire;
	static ArrayDeque<int[]> canGo;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
	
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
//			char[][] map = new char[R][C]; 
			graph = new int[R][C];
			for(int i=0; i<R; i++) Arrays.fill(graph[i], Integer.MAX_VALUE);
			// 상근이는 1로 시작함 
			
			
			int sx = -1, sy = -1; // 상근이의 시작 위치 
			fire = new ArrayDeque<>(); // 불의 위치 -- 회차마다 동서남북으로 퍼져나간다 
			canGo = new ArrayDeque<>(); 
			for(int i=0; i<R; i++) {
				String input = br.readLine(); 
				for(int j=0; j<C; j++) {
					char in = input.charAt(j);
					if(in == '@') { // 상근이 위치 
						sx = i; 
						sy = j; 
						graph[i][j] = 0; 
						canGo.offer(new int[] {i, j, 0}); // 좌표, 시간 
					}else if(in == '*') { // 불의 위치 
						graph[i][j] = -1; 
						fire.offer(new int[] {i, j});
					}else if(in == '#') { // 벽의 위치 
						graph[i][j] = -2; 
					}
				}
			}
			
			int ans = 0; 
			boolean canEscape = false; 
			while(true) {
				// 1. 불이 퍼진다 
				ArrayDeque<int[]> updateFire = new ArrayDeque<>();
//				updateFire.addAll(fire); // !!!!!!!!!!!!! 중복!!!!!!!!!!!!!!!!!!!!!
				while(!fire.isEmpty()) {
					int[] cur = fire.poll(); 
					
					// 불이 갈 수 있는 곳 -- 인접한 공간 && 벽 불가능 
					for(int d=0; d<4; d++) {
						int ni = cur[0] + di[d]; 
						int nj = cur[1] + dj[d]; 
						if(ni<0 || ni>=R || nj<0 || nj>=C) continue; 
						if(graph[ni][nj] == -2) continue; 
						if(graph[ni][nj] == -1) continue; // !!!!!!!!!!! 중복!!!!!!!!!!!!!!!!
						graph[ni][nj] = -1; 
						updateFire.offer(new int[] {ni, nj});
					}
				}
				fire = updateFire; 
				
				
				// 2. 불이 옮겨 붙고 나면 상근이가 움직인다!! 가장 가까운 곳으로 이동 
				ArrayDeque<int[]> updateGo = new ArrayDeque<>(); 
				while(!canGo.isEmpty()) {
					int[] cur = canGo.poll(); 
					int i = cur[0], j = cur[1], t = cur[2]; 
					
//					if(graph[i][j] == -1) continue; 
					if((i==0||i==R-1) || (j==0 || j==C-1)) {
						canEscape = true; 
						ans = t; 
						break; 
					}
					
					for(int d=0; d<4; d++) {
						int ni = i+di[d]; 
						int nj = j+dj[d]; 
						
						if(ni<0 || ni>=R || nj<0 || nj>=C) continue; 
						if(graph[ni][nj] == -1) continue; // 불이면 못 가고 
						if(graph[ni][nj] == -2) continue; // 벽이면 못 가고 
						if(graph[ni][nj] != Integer.MAX_VALUE) continue; // 이미 방문했으면 못 가고 
						
						updateGo.add(new int[] {ni, nj, t+1});
						graph[ni][nj] = t+1; 
					}
				}
				canGo = updateGo; 
				
				if(canEscape) break; 
				if(updateGo.isEmpty()) break; 
			}
			
			if(canEscape) sb.append(ans+1).append("\n");
			else sb.append("IMPOSSIBLE\n");
			
		}
		System.out.println(sb);

	}
}
/*

*/