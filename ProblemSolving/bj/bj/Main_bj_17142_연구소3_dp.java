package bj;

import java.io.*; 
import java.util.*; 
public class Main_bj_17142_연구소3_dp {
	static int[][] map; 
	static int N, M; 
	// static boolean[][] done; 
	static ArrayList<Virus> list; 
	static int[] b; 
	static int emptyCount = 0; 
	static int minTime; // 클래스 객체로 풀이할 때 정답 
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1}; 
	
	static class Virus{
		int r, c, time; 
		Virus(int r, int c, int time){
			this.r = r; 
			this.c = c;
			this.time = time; 
		}
	}
	
	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minTime = Integer.MAX_VALUE; 
		map = new int[N][N]; 
		
		list = new ArrayList<>(); // 바이러스의 위치 리스트 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) list.add(new Virus(i, j, 0));
				else if(map[i][j] == 0) emptyCount++; 
			}
		}
	}
	
	static void comb(int cnt, int start) {
		if(cnt == M) {
			// System.out.println(Arrays.toString(b));
			ArrayList<Virus> virus = new ArrayList<>(); 
			for(int i=0; i<b.length; i++) {
				if(b[i] == 1) virus.add(list.get(i));
			}
			/**
			 * ++ emptyCount가 0이면 굳이 연산할 필요가 없으므로 바로 minTime 0으로 설정 
			 */
			if(emptyCount == 0) {
				minTime = Math.min(minTime, 0);
			}else {
				bfs(virus);				
			}
			return; 
		}
		for(int i=start; i<list.size(); i++) {
			b[i] = 1; 
			comb(cnt+1, i+1);
			b[i] = 0; 
		}
	}
	
	/**
	 * ++ 고쳐야 했던 핵심 부분; 
	 * @param virus
	 */
	static void bfs(ArrayList<Virus> virus) {
		ArrayDeque<Virus> dq = new ArrayDeque<>(); 
		/**
		 * ++ timeStamp[][] 배열 
		 * 미 방문은 -1 
		 * 활성 바이러스는 0 
		 */
		int[][] timeStamp = new int[N][N];
		for(int i=0; i<N; i++) Arrays.fill(timeStamp[i], -1); 
		
		for(Virus v: virus) {
			dq.offer(v); 
			timeStamp[v.r][v.c] = 0;  
		}
		Virus v = null;
		int maxEmptyTime = 0; 
		int currentEmptyCnt = emptyCount; 
		while(!dq.isEmpty()) {
			v = dq.poll();
			
			for(int d=0; d<4; d++) {
				int ni = v.r + di[d]; 
				int nj = v.c + dj[d]; 
				if(ni<0 || ni>=N || nj<0 || nj>= N) continue; 
				/**
				 * ++ 이미 방문했거나 벽이면 통과하는 게 가지치기 잘하는 거임 
				 */
				if(timeStamp[ni][nj] != -1 || map[ni][nj] == 1) continue; 
				
				/**
				 * 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 
				 * 비활성 바이러스가 활성으로 변한다. 
				 * 
				 * ++ 방문 안했고 빈칸이거나 비활성 바이러스면 방문해야됨 
				 */
				if(timeStamp[ni][nj] == -1 && (map[ni][nj] == 0 || map[ni][nj] == 2)) {
					dq.offer(new Virus(ni, nj, v.time+1));
					timeStamp[ni][nj] = v.time+ 1; 
					
					/**
					 * ++ 만약 빈칸에 전염시켰다면 (비활성 바이러스는 maxTime이나 emptyCount에 영향을 주지 않는다. 
					 * 
					 * emptyCount 1 빼고 
					 * 전체 빈칸이 되었는지는 모르지만, 일단 현재까지는 포함되는 거니까 maxEmptyTime을 갱신한다 
					 * ++ 주의점: maxEmptyTime을 또 1 증가시키면 안된다 
					 */
					if(map[ni][nj] == 0) {
						currentEmptyCnt--; 
						maxEmptyTime = timeStamp[ni][nj]; 
					}
				}
				/**
				 * ++ 만약 모든 빈칸이 감염되었으면 큐에 남은 비활성 바이러스를 더 이상 처리할 필요가 없다. 
				 * 따라서 바로 종료하자. 
				 */
				if(currentEmptyCnt == 0) {
					break; 
				}
			}
		}
		/**
		 * ++ 최종적으로 모든 빈칸이 감염되었는지만 확인하면 됨 
		 * 그렇다면, minTime을 갱신해야 한다. 
		 */
		if(currentEmptyCnt == 0) {
			minTime = Math.min(maxEmptyTime, minTime);
		}
		
	}

	static boolean checkAll(boolean[][] v) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!v[i][j] && map[i][j] == 0) return false; 
			}
		}
		return true; 
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		input(br, st);
		
		if(emptyCount == 0) {
			System.out.println(0);
			return; 
		}
		b = new int[list.size()]; 
		comb(0, 0);
		if(minTime == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minTime);
		}
	}

}
/*
ㅁㅊ 반례;; 
3 1
2 2 0
2 1 1
2 2 0
=> 3
-----
NxN 배열이 있고 50 x 50 
빈 칸 0 
벽 1
바이러스 2 
1<= M <= 비활성 바이러스 개수 <= 10 
M개만 활성상태로 만들었을 때 모든 빈칸에 바이러스가 퍼지는 최소시간 
(10C1 + 10C2 + 10C3 + 10C4) * 2 + 10C5 의 조합이 있겠지 
근데 M을 최소한으로 하라곤 안 했으니까 그냥 M개 최대한 바이러스를 많이 놓으면 도움이 될 것 같은데 
2의 위치 중 어디에 놓을 것인가 

바이러스가 있는 좌표를 갖고 온다 
그 좌표들에 대해서 M개를 선택한다
그 자리에 각각 활성화시킨다. 
	상하좌우 모든 빈 칸으로 동시에 복제된다 

*/