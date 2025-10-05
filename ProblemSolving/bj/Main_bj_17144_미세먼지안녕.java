
import java.io.*; 
import java.util.*; 
public class Main_bj_17144_미세먼지안녕 {
	static class Dust{
		int r, c, dust;

		public Dust(int r, int c, int dust) {
			this.r = r;
			this.c = c;
			this.dust = dust;
		} 
	}
	static int[][] map; 
	static ArrayList<Dust> dq; 
	static int[][] cleaners; 
	static int R, C, T; 
	static int[] di = {-1, 1, 0, 0}; // 상(0) 하(1) 우(2) 좌(3) 
	static int[] dj = {0, 0, 1, -1}; 
	static void input(BufferedReader br, StringTokenizer st) throws Exception {
		int cleanerIdx = 0; 
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					cleaners[cleanerIdx][0] = i; cleaners[cleanerIdx][1] = j; // 공기 청정기 위치 
					cleanerIdx++; 
				}
				else if(map[i][j] > 0) dq.add(new Dust(i, j, map[i][j])); // 미세먼지들 
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행 길이 
		C = Integer.parseInt(st.nextToken()); // 열 길이 
		T = Integer.parseInt(st.nextToken()); // T초 후 
		
		map = new int[R][C]; // 배열 
		dq = new ArrayList<>(); // 미세먼지 좌표 저장: r,c, dust
		cleaners = new int[2][2];  // 공기청정기 위치 
		
		/**
		 * 입력 받아오기 
		 */
		input(br, st);
		

		/**
		 * 미세먼지 확산시키기 
		 * - 모든 칸에서 동시에 확산된다. 
		 * - 인접한 4방향으로 확산된다. 
		 * - 공청기가 있거나, 범위 밖이면 확산 안된다. 
		 * - 확산되는 양은, 내위치의 미세먼지양/5(소수점 버림) 
		 * - 남는 미세먼지 양 = 원래양 - 확산된양 * 확산된 방향개수
		 */
		for(int t=0; t<T; t++) {
			int[][] diff = new int[R][C]; // 각 좌표에서 얼마나 변해야 하는지를 기록; 한번에 업데이트
			for(int i=0; i<dq.size(); i++) {
				Dust dust = dq.get(i); 
				int newDust = (int) (dust.dust / 5);
				
				// 인접한 4방향에 대해서, 공청기가 위치하거나, 범위 밖이면 continue 
				for(int d=0; d<4; d++) {
					int nr = dust.r+di[d], nc = dust.c+dj[d]; 
					if(nr<0 || nr>=R || nc<0 || nc>=C) continue; // 범위를 벗어나면 패스 
					if(map[nr][nc] == -1) continue; // 공기청정기 자리도 패스 
					
					diff[dust.r][dust.c] -= newDust; 
					diff[nr][nc] += newDust; 
				}
			}
//			System.out.println("===미세먼지 확산됨===");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] += diff[i][j]; 
//					System.out.printf("%3d ", map[i][j]);
				}
//				System.out.println();
			}
		
			/**
			 * 공기 청정기 바람 불어옴
			 * 상(0) 하(1) 우(2) 좌(3) 
			 * 1. 위쪽 바람: 우 -> 상 -> 좌 -> 하: 2 0 3 1 
			 * 2. 아래쪽 바람: 우 -> 하 -> 좌 -> 상: 2 1 3 0 
			 */
			
			// 위쪽 방향

			int cleanX = cleaners[0][0], cleanY = cleaners[0][1]; 
//			System.out.println("공기청정기 위치: " + cleanX + ", " + cleanY);
			
			int[] windUp = {2, 0, 3, 1}; 
			ArrayList<Integer> list = new ArrayList<>(); // 이동해야 하는 미세먼지들 리스트
			list.add(0); // 공청기 자리는 0으로 둔다. 
			
			int sx = cleanX, sy = cleanY; // 현재 위치: 시작점은 공청기 위치이다. 
			for(int d=0; d<4; d++) {
				int dir = windUp[d];
				
				while(true) {
					int nx = sx+di[dir]; 
					int ny = sy+dj[dir]; 
					if(nx<0 || nx>=R || ny<0 || ny>=C) break; 
					if(map[nx][ny] == -1) break;
					
					list.add(map[nx][ny]);
					
					sx = nx; sy = ny; 
				}
			}	
//			System.out.println("위로 보낼 때 list: "+ list);
			
			int idx = 0; 
			int x , y; 
			int last[] = new int[] {cleanX, cleanY}; 
			for(int d=0; d<4; d++) {
				int dir = windUp[d];
				x = last[0]+di[dir]; y = last[1]+dj[dir]; 
//				System.out.println("@방향 변경: " + dir);
//				System.out.println("남은 list: " + list);
				while(idx < list.size()) {
					if(x<0 || x>=R || y<0 || y>=C) break; 
					map[x][y] = list.get(idx++);
					last[0] = x; last[1] = y; 
//					System.out.println("x = " + x + ", y = " + y + "거기 값: " + map[x][y]);
					x = x + di[dir]; 
					y = y + dj[dir]; 
				}
			}
			
			map[cleanX][cleanY] = -1; 
//			System.out.println("===공기청정기: 윗 방향 ===");
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					System.out.printf("%3d ", map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("=============");
			
			// 아래로 보낼 때 
			int cleanx2 = cleaners[1][0];
			int cleany2 = cleaners[1][1]; 
//			System.out.println("공기청정기 위치: " + cleanx2 + ", " + cleany2);
			
			int[] windDown = {2, 1, 3, 0}; 
			list.clear();
			list.add(0);
			sx = cleanx2;
			sy = cleany2; 
			for(int d=0; d<4; d++) {
				int dir = windDown[d];
				
				while(true) {
					int nx = sx+di[dir]; 
					int ny = sy+dj[dir]; 
					if(nx<0 || nx>=R || ny<0 || ny>=C) break; 
					if(map[nx][ny] == -1) break;
					
					list.add(map[nx][ny]);
					
					sx = nx; sy = ny; 
				}
			}	
//			System.out.println("아래 방향으로 보낼 때 list: "+ list);
			idx = 0; 
			int a, b; 
			int[] last2 = new int[] {cleanx2, cleany2}; 
			for(int d=0; d<4; d++) {
				int dir = windDown[d];
				a = last2[0]+di[dir]; b = last2[1]+dj[dir]; 
//				System.out.println("@방향 변경: " + dir);
				while(idx < list.size()) {
					if(a<0 || a>=R || b<0 || b>=C) break; 
					map[a][b] = list.get(idx++);
					last2[0] = a; last2[1] = b; 
//					System.out.println("x = " + a + ", y = " + b);
					a = a + di[dir]; 
					b = b + dj[dir]; 
				}
			}
			map[cleanx2][cleany2] = -1; 
//			System.out.println("===공기청정기: 아래 방향 ===");
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					System.out.printf("%3d ", map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("=================");
			
			/**
			 * 미세먼지 있는 곳 다시 탐색
			 */
			dq.clear(); 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j]>0) dq.add(new Dust(i, j, map[i][j]));
				}
			}
		}
		int ans = 0; 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) ans += map[i][j]; 
			}
		}
		System.out.println(ans);

	}
}
/*
t = 0부터 T초까지 
	미세먼지가 있는 좌표 전부에 대해서 확산 i, j
		인접 4방향 상하좌우에 대해서 ni, nj
			공청기가 위치하거나, 범위 밖이면 continue
			아니면 
				해당 방향으로 확산 미세먼지 양 저장하고 ni, nj <- ..  
				해당 위치에서 그만큼 빼기 i, j <- ..
				
	공기청정기가 바람을 내보낸다. 
*/