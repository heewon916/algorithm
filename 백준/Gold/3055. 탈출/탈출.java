import java.io.*; 
import java.util.*; 
public class Main {
	static int R, C;
	static int sx, sy;
	static ArrayList<int[]> waters; 
	static ArrayDeque<Pos> q; 
	static char[][] map; 
	
	static class Pos{
		int r, c, time;

		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public String toString() {
			return "갈 수 있는 곳: " + r + ", " + c + "/ 내 시간: " + time; 
		} 
		
		
	}
	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C]; 
		waters = new ArrayList<>();
		q = new ArrayDeque<>(); 
		v = new boolean[R][C]; 
		for(int i=0; i<R; i++) {
			String in = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = in.charAt(j);
				if(map[i][j] == '*') {
					waters.add(new int[] {i, j});
				} else if(map[i][j] == 'S') {
					sx = i; sy = j; 
					q.offer(new Pos(i, j, 0));
					v[i][j] = true; 
				} 
			}
		}
	}
	static int[] dr = {1, -1, 0, 0}; 
	static int[] dc = {0, 0, 1, -1}; 
	static void printMap() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		input(br, st);
		
		String msg = ""; 
		int res = 0; 
		while(true) {
//			System.out.println("== new turn ==");
	
			// 물 퍼트리기 
			ArrayList<int[]> newL = new ArrayList<>();
			for(int[] water: waters) {
				int x = water[0]; 
				int y = water[1];
				for(int d=0; d<4; d++) {
					int nx = x+dr[d]; 
					int ny = y+dc[d]; 
					if(nx<0 || nx>=R || ny<0 || ny>=C) continue; 
					if(map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
					if(map[nx][ny] == '*') continue; 
					newL.add(new int[] {nx, ny});
					map[nx][ny] = '*'; 
				}
			}
			waters = newL; 
//			System.out.println("--물이 퍼졌습니다.--");
//			printMap();
			
			// 고슴도치 갈 곳 이동 
			boolean able = false; 
			if(q.isEmpty()) {
//				System.out.println("!! 갈 수 있는 곳이 더 이상 없습니다.");
				break; 
			}
			ArrayDeque<Pos> nq = new ArrayDeque<>();
			while(!q.isEmpty()) {
				Pos cur = q.poll(); 
				
				if(map[cur.r][cur.c] == 'D') {
					able = true; 
					res = cur.time; 
					break; 
				}
				
				for(int d=0; d<4; d++) {
					int nr = cur.r + dr[d]; 
					int nc = cur.c + dc[d]; 
					if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if(map[nr][nc] == 'X' || map[nr][nc] == '*') continue; 
					if(v[nr][nc]) continue; 
					nq.offer(new Pos(nr, nc, cur.time+1));
					v[nr][nc] = true; 
				}
			}
			q = nq;  
//			for(Pos p: q) {
//				System.out.println(p);
//			}
			if(able) {
				msg = res+"";
				break; 
			}
		}
		if(msg.length() == 0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(msg);
		}
		
		
	}

}

/*
r행 c열 
출발지 S 도착지 D
빈 칸 '.' 
물 '*' 
돌 'X' 

S이동
- 인접한 4칸 && 돌이 아닌 && 물이 아닌 
- D에 도착할 수 있게 되면 끝남 
물이동
- 물과 인접한 4칸 && 돌이 아닌 && D가 아닌 곳으로 확장함 

다음 시간에 물이 찰 예정인 칸으로 이동할 수 없다. 

물인 좌표 모두 리스트로 관리 
- 매분마다 모든 물인 좌표인 애들 퍼트림 

고슴도치는 그 다음에 갈 수 있는 곳을 찾아서 감 
고슴도치가 갈 수 있는 위치 -> r,c,시간 이렇게 기록하면 되지 않을까? 

비버의 굴로 갈 수 없는 경우는? 
D에 도착 못했고, S가 갈 수 있는 큐가 빈 경우 
*/