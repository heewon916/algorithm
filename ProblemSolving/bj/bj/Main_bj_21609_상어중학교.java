package bj;

import java.io.*; 
import java.util.*; 
public class Main_bj_21609_상어중학교 {
	static int N, M; 
	static int[][] map; 
	static class Group{
		boolean[][] selected; 
		int size; 
		int rainbowCnt; 
		int baseX, baseY; 
		public Group() {
			selected = new boolean[N][N]; 
			size = 0; 
			baseX = -1; 
			baseY = -1; 
		}
		public Group(boolean[][] v, int size) {
			this.selected = v; 
			this.size = size; 
			setBase(); 
			setRainbow(); 
		}
		void setRainbow() {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(selected[i][j] && map[i][j] == 0) rainbowCnt++; 
				}
			}
		}
		void setBase() {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(this.selected[i][j] && map[i][j] > 0) {
						baseX = i; 
						baseY = j; 
						return; 
					}
				}
			}
		}
	}
	static int[] di = {-1, 1, 0, 0}; 
	static int[] dj = {0, 0, -1, 1}; 
	static Group bfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		boolean[][] v = new boolean[N][N];
		int size = 0; 
		int common = 0; 
		q.offer(new int[] {i, j});
		v[i][j] = true; 
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0]; 
			int c = cur[1];
			for(int d=0; d<4; d++) {
				int ni = r+di[d]; 
				int nj = c+dj[d];
				if(ni<0 || ni>=N || nj<0 || nj>=N) continue; 
				if(!v[ni][nj] && map[ni][nj] != -1 && (map[ni][nj] == map[i][j] || map[ni][nj] == 0)) {
					v[ni][nj] = true; 
					q.offer(new int[] {ni, nj});
				}
			}
		}
		// 만약 일반 블록 개수가 0개이고, 블록 그룹 크기가 1개라면 아웃 
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(v[r][c]) size++; 
				if(v[r][c] && map[r][c] >= 1 && map[r][c] <= M) common++; 
			}
		}
		if(size <= 1 || common == 0) return null; 
		else {
			return new Group(v, size);
		}
	}
	static void printGroup(Group g) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(g.selected[i][j]?1:0);
			}
			System.out.println();
		}
	}
	static void printMap(int[][] map) {
		System.out.println("--출력--");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 6) System.out.printf("%2d", 6);
				else System.out.printf("%2d",map[i][j]);
			}
			System.out.println();
		}
		System.out.println("------");
	}
	/**
	 2 2-1 3 1
	 6 6 2 0-1
	 6 6 6 1 2
	-1 6 1 3 2
	 6 6 2 2 1
	 * @param map
	 */
	static void gravity(int[][] map) {
		for(int c = 0; c<N; c++) {
//			System.out.println("열: " + c);
			
			int[] list = new int[N]; 
			int blank = 0; 
			for(int r=0; r<N; r++) {
				if(map[r][c] == 6) blank++; 
				list[r] = map[r][c]; 
			}
			
			if(blank == 0) continue; 
//			System.out.println("list: " + Arrays.toString(list));
			
			boolean swapAble = false; 
			for(int t=0; t<N; t++) {
				swapAble = false; 
				for(int i=N-1; i>0; i--) {
					if(list[i] == 6 && list[i-1] != -1) {
						swapAble = true; 
					}
				}
				if(!swapAble) break; 
				for(int i=N-1; i>0; i--) {
					if(list[i] == 6 && list[i-1] != -1) {
						int tmp = list[i]; 
						list[i] = list[i-1];
						list[i-1] = tmp; 
					}
				}
			}
//			System.out.println("결과: " + Arrays.toString(list));
			for(int i=0; i<N; i++) {
				map[i][c] = list[i]; 
			}
		}
	}
	/**
	 * 반 시계 방향으로 회전 
	 * @param map
	 */
	static void turn(int[][] map) {
		// 0번 행 -> 0번 열이 된다. 
		int[][] tmp = new int[N][N]; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = map[j][N-i-1];
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = tmp[i][j]; 
			}
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 블록 그룹이 존재하는 동안 계속 반복 
		int score = 0; 
		while(true) {
			/**
			 * 1. 블록 그룹 찾기 
			 */
			PriorityQueue<Group> pq = new PriorityQueue<>((o1, o2) -> {
				if(o1.size == o2.size){
					if(o1.rainbowCnt == o2.rainbowCnt) {
						if(o1.baseX == o2.baseX) return o2.baseY - o1.baseY;
						else return o2.baseX - o1.baseX;
					}else {
						return o2.rainbowCnt - o1.rainbowCnt; 
					}
				}else {
					return o2.size - o1.size; 
				}
				
			});
			boolean[][] selected = new boolean[N][N]; 
			for(int i=0; i<N; i++) {
				for(int j=0;j<N; j++) {
					if(!selected[i][j] && map[i][j] >= 1&& map[i][j] <= M) {
						Group g = bfs(i, j);
						if(g == null) continue; 
						else {
							pq.offer(g);					
							for (int r = 0; r < N; r++) {
                                for (int c = 0; c < N; c++) {
                                    if (g.selected[r][c]) {
                                    	selected[r][c] = true;
                                    }
                                }
                            }
						}
					}
				}
			}
			if(pq.size() == 0) break; 
			Group res = pq.poll(); 
//			printGroup(res);
			/**
			 * 2. 점수 획득 & 제거해버리기 
			 */
			score += res.size * res.size;
//			System.out.println("score = " + score);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(res.selected[i][j]) {
						map[i][j] = 6;
					}
				}
			}
//			printMap(map);
			/**
			 * 3. 바닥으로 블록 내리고 반시계 회전하고 바닥으로 블록 내리기 
			 */
			gravity(map); 
//			System.out.println("중력이후: ");
//			printMap(map);
			turn(map);
//			System.out.println("회전이후: ");
//			printMap(map); 
			gravity(map);
//			System.out.println("중력이후: ");
//			printMap(map); 
			
		}
		System.out.println(score);
	}

}
/*
정사각형 배열 
검정색 -1 
무지개 0 
일반 M가지 색상 (1~M 자연수) 

블록그룹 
- 일반블록 1개 이상이고, 색 모두 같은 거 
- 검정색은 없어야 됨. 무지개는 노상관 
- 총 개수 2개 이상 
- 모두 연결된 블록 
- 기준 블록: 무지개 아니고, r,c쌍이 가장 작은

블록그룹이 존재하는 동안 아래 과정 계속 반복
블록개수가 가장 많은 그룹 찾기 
	- 무지개 블록 많은 순, 기준 블록 r->c 순으로 큰 순 
찾은 블록 제거 
	블록 개수 B개 이면 B*B점 증가 
바닥으로 모든 블록 내리기 
	검정블록 제외 
90도 반시계 회전 
바닥으로 모든 블록 내리기 
*/