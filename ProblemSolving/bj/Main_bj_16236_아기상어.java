import java.io.*; 
import java.util.*;
public class Main_bj_16236_아기상어{
	static int N;
	static int[][] map; 
	static int[] shark; // 좌표 
	static int sharkSize; 
//	static boolean[][] v; // 큐에 담으면 true가 된다. 
	static PriorityQueue<Fish> pq; 
	static class Fish{
		int r, c, dist; 
		public Fish(int r, int c) {
			// 현재 아기상어 위치에 대해서, bfs로 갈 수 있는 최단 거리를 구해야 한다. 
			this.r = r; 
			this.c = c; 
			this.dist = bfs(r, c);
		}
	}
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1}; 
	/**
	 * 상어가 있는 곳에서, 최단 경로를 리턴하게 된다. 
	 * 이때 상어의 크기보다 큰 곳은 지나갈 수 없다. 
	 * 
	 * !!!!!!!!!!!! 주의 !!!!!!!!!!!!!!!!!
	 * 1. 방문 안했고, 나보다 크기가 작다고 해도 
	 * 2. 갈 방도가 없을 수도 있다. 
	 * 3. 이때 거리가 0이 아니라 무한대로 주어져야 한다. 
	 * 4. 따라서 항상 dist를 주는 것이 아니라, Integer.MAX_VALUE로 처리할 줄 알아야 한다. 
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	static int bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		boolean[][] bfsV = new boolean[N][N]; 
		q.add(new int[] {shark[0], shark[1], 0}); // 좌표, 거리 
		bfsV[shark[0]][shark[1]] = true; // 아기상어 위치 방문 처리 
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int y = cur[0], x = cur[1], dist = cur[2]; 
			if(y == r && x == c) return dist; 
			for(int d=0; d<4; d++) {
				int ny = y+dy[d], nx = x+dx[d];
				if(ny<0 || ny>=N || nx<0 || nx>=N) continue; 
				if(!bfsV[ny][nx] && map[ny][nx] <= sharkSize) {
					q.add(new int[] {ny, nx, dist+1});
					bfsV[ny][nx] = true; 
				}
			}
		}
//		System.out.println(r+ ", " + c + "까지의 최단거리: " + minDist);
		return Integer.MAX_VALUE; 
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N]; 
		shark = new int[2]; 
		sharkSize = 2; // 아기상어 초기 크기 2 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark[0] = i; shark[1] = j; 
				}
			}
		}
		pq = new PriorityQueue<>((o1, o2)->{
			if(o1.dist == o2.dist) {
				if(o1.r == o2.r) return o1.c - o2.c; 
				else return o1.r - o2.r; 				
			}else {
				return o1.dist - o2.dist; 
			}
		});
//		v = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]>=1 && sharkSize > map[i][j]) {
					pq.offer(new Fish(i, j));
//					v[i][j] = true; 
				}
			}
		}
		int eatCnt = 0; 
		int time = 0; 
		while(!pq.isEmpty()) {
			Fish eat = pq.poll(); // 지금 이 물고기를 먹는다. 
			if(eat.dist == Integer.MAX_VALUE) break; 
//			System.out.println("지금 먹을 곳: " + eat.r + ", "  + eat.c);
			// 먹은 자리는 0이 되고, 아기상어는 이 자리로 온다. 
			map[eat.r][eat.c] = 0; 
			map[shark[0]][shark[1]] = 0; 
			shark[0] = eat.r; 
			shark[1] = eat.c; 
			// 시간은 eat위치까지 오는데 걸린 거리만큼 걸린다. 
			time += eat.dist; 
			eatCnt++; // 한 마리 먹었으니 +1
			if(sharkSize == eatCnt) {
				sharkSize++; 
				eatCnt = 0; 
			}
			pq.clear();
			/**
			 * !!!!!!! 주의 !!!!!!!! 
			 * 바뀐 아기 상어에 대해서 다시 먹을 수 있는 곳을 계산해 넣는다. 
			 * 1. 기존에는 visited 배열을 해서, 이미 큐에 들어가 있는 애들은 따로 업데이트하고, 그 외에 또 갈 수 있는 곳을 
			 * 업데이트 했었는데, 이렇게 하면 계산 오류가 발생할 수 있다. 
			 * 2. 따라서, 그냥 pq를 비우고, 현재 아기상어에 대해서 먹을 수 있는 좌표를 새로이 구하는 게 맞다. 
			 * 3. 따라서 visited 배열은 여기서 무의미하다. 
			 * ==> bfs라고 모든 조건에 !v[i][j] 붙이는 거에 습관화가 되지는 말자. 
			 */
			// 바뀐 아기 상어에 대해서 다시 먹을 수 있는 곳들을 계산해 넣는다. 
			// 큐에 들어간 애들을 방문처리할 이유는 없다. 그냥 다시 처음부터 보는 게 더 깔끔쓰
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] >=1 && sharkSize > map[i][j]) {
						pq.add(new Fish(i, j));
					}
				}
			}
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("-------------");
		}
		System.out.println(time);
	}
	
}
/*
[문제 이해하기] 
nxn배열 
물고기 m마리 

처음 아기상어 크기 = 2
1초마다 상하좌우 인접한 칸으로 이동가능하다. 
<갈 수 있는 칸> 
나보다 큰 물고기가 있는 칸 => 못 감 
나보다 작거나 같은 크기의 물고기가 있는 칸 => 갈 수 있음 

<먹을 수 있는 물고기> 
나보다 크기가 작은 물고기만 가능하다. 

1. 더 이상 먹을 수 있는 물고기가 전체 배열에 없으면 끝난다. 
2. 먹을 수 있는 물고기가 있으면 
1마리 이상일 때, 우선순위 
	1. 거리가 가까운 물고기 -> 거리 = 지나야 하는 칸의 개수의 최솟값 (x,y) -> (r,c)로 갈 때의 최단경로 - 크기가 큰 물고기 = 장애물 
	2. 같은 거리인 물고기 여러 개이면 -> r이 작은 순으로 -> 그래도 같으면 -> c가 작은 순으로 
	
어디로 갈 지 한번에 1초 
먹으면 그 칸은 0이 된다. 

<아기 상어 크기 +1> 
나의 크기 == 먹은 물고기 개수 => +1 
먹은 물고기 개수 <- 0 초기화 

[설계하기] 
아기상어 위치 x,y에 대해서 크기가 n일 때 

전체 배열에 대해서 먹을 수 있는 곳을 큐에 모두 담는다. 
큐에 아무것도 없다는 건 더 이상 먹을 수 있는 물고기가 없다는 뜻 -> break 
아직 방문 안했고(큐에 안 담았고) && 크기가 나보다 작은 곳에 대해서 
우선순위 큐 <- (좌표, 크기)
- 우선순위 계산: 거리 = (현재 위치, 좌표)간의 bfs 거리 - 나보다 크기가 큰 물고기는 지나갈 수 없다. 

거리만큼 time 증가하고, 
거기로 이동해서 먹은 물고기 개수 == 나의 크기이면 
	내 크기 +1
	먹은 물고기 개수 <- 0 초기화 
	
거기로 이동해서 전체 배열에 대해서 
	아직 방문 안했고 && 크키가 나보다 작은 곳이 있는지 살피고 있으면 큐에 넣기 

*/