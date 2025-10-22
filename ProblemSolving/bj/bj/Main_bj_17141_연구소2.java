package bj;
import java.util.*;
import java.io.*; 

public class Main_bj_17141_연구소2 {
	static int N, M; 
	static int[][] map; 
	static ArrayList<int[]> virusPlaces; 
	static void input(BufferedReader br, StringTokenizer st) throws Exception{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minTime = Integer.MAX_VALUE; 
		map = new int[N][N]; 
		virusPlaces = new ArrayList<>(); 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusPlaces.add(new int[] {i, j});
			}
		}
	}
	static int[] b; 
	static void comb(int cnt, int start) {
		if(cnt == M) {
			/**
			 * 바이러스 어디에 둘지 결정한 상태 
			 * 그럼 바이러스들을 기준으로 전체 빈칸에 퍼뜨려봐야한다. 
			 * 
			 * 
			 */
			int[][] tmp = new int[N][N]; 
			for(int i=0; i<N; i++) {
				Arrays.fill(tmp[i], -1);
			}
			for(int i=0; i<b.length; i++) {
				int r = virusPlaces.get(i)[0]; 
				int c = virusPlaces.get(i)[1]; 
				
				if(b[i] == 1) {
					tmp[r][c] = 0; 
				}
			}
			spreadVirus(tmp);
		}
		for(int i=start; i<virusPlaces.size(); i++) {
			b[i] = 1;
			comb(cnt+1, i+1);
			b[i] = 0; 
		}
		
	}
	/**
	 * 바이러스가 있는 곳은 0으로 체크되어 있는 tmp 
	 * 미방문인 곳은 -1으로 되어 있다. 
	 * 
	 * 모든 바이러스는 1초마다 동시에 상하좌우로 퍼진다. 
	 * 
	 * dq <- 현재 바이러스가 들어있는 좌표  
	 * 
	 * dq가 빌 때까지 
	 * 		int[] <- dq.poll 
	 * 		
	 * 		그 녀석의 상하좌우에 대해서 
	 * 			미방문이고 (-1) && 벽이 아니고 && 범위 내에 있으면 
	 * 				그 위치의 시각 = 현 위치의 시간에 + 1
	 * 	
	 * @param tmp
	 */
	static int[] di = {-1, 1, 0, 0}; 
	static int[] dj = {0, 0, -1, 1}; 
	static int minTime; 
	static void spreadVirus(int[][] timeStamp) {
		ArrayDeque<int[]> dq = new ArrayDeque<>(); 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(timeStamp[i][j] == 0) dq.add(new int[] {i, j}); 
			}
		}
		while(!dq.isEmpty()) {
			int[] cur = dq.poll(); 
			int r = cur[0]; 
			int c = cur[1]; 
			for(int d=0; d<4; d++) {
				int nr = r + di[d]; 
				int nc = c + dj[d]; 
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue; 
				// 벽이 아니고 && 미방문인 곳
				if(map[nr][nc] != 1 && timeStamp[nr][nc] == -1) {
					dq.offer(new int[] {nr, nc});
					timeStamp[nr][nc] = timeStamp[r][c] + 1; 
				}
			}
		}
		// 모든 빈칸에 바이러스가 있는지 
		// 벽이 아닌 곳에 대해서만 
		int time = 0; 
		boolean notAll = false; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 벽이 아닌데, 방문하지 못한 곳이 있다? --> 바이러스를 모든 빈칸에 퍼트리지 못했다. 
				if(map[i][j] != 1 && timeStamp[i][j] == -1) {
					notAll = true; 
					break; 
				}
				time = Math.max(time, timeStamp[i][j]);
			}
			if(notAll) break; 
		}
		// 모든 빈칸이 바이러스로 가득차면
		if(!notAll) minTime = Math.min(minTime, time);
	}
	public static void main(String[] args) throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		input(br, st); 
		b = new int[virusPlaces.size()]; // virusPlaces 기준으로 어느 위치에 바이러스를 둘 지 결정해야 한다. 
		comb(0, 0);
		if(minTime == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minTime);
		}
	}
}
/*
NxN 배열 50 x 50 
바이러스 개수 M 10 
빈칸 0 -> 바이러스 놓을 수 있음 
모든 바이러스: 1초 동안 상하좌우로 인접한 모든 빈칸0으로 동시에 복제됨 

0 빈칸 
1 벽 
2 바이러스 놓을 수 있는 칸 

2가 있는 칸 중에서 M개 고르면 됨 -> 조합이지 
*/