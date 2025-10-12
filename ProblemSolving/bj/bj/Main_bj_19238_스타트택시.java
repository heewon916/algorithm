package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_19238_스타트택시 {
	static int N, M, oil; 
	static int[][] map; // 0은 빈칸, 1은 벽 
	static int tx, ty; // 행,열은 1-index로 주어진다 
	static class User{
		int id; 
		int sx, sy, dx, dy;
		int dist; 
		User(int id, int sx, int sy, int dx, int dy, int d) {
			this.id = id; 
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
			this.dist = d;
		}
		@Override
		public String toString() {
			return "승객정보: 시작점(" + sx + ", " + sy + ")/ 택시와의 거리: " + dist;  
		}
		
	}
	/**
	 * 사용자의 위치: ux, uy
	 * 택시 위치: tx, ty
	 * 목적지(사용자)와 출발지(택시)와의 최단 경로를 구한다. 
	 * @param ux, uy
	 * @return
	 */
	static int calcDist(int ux, int uy) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		boolean[][] v = new boolean[N][N]; 
		q.offer(new int[] {tx, ty, 0}); // 택시 기준 거리 시작거리 0 
		v[tx][ty] = true; 
		while(!q.isEmpty()) {
			int[] pos = q.poll(); 
			int x = pos[0], y = pos[1], d = pos[2]; 
			if(x == ux && y == uy) {
				return d; 
			}
			for(int k=0; k<4; k++) {
				int nx = x+di[k]; 
				int ny = y+dj[k];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue; 
				if(map[nx][ny] == -1) continue;
				if(v[nx][ny]) continue; 
				v[nx][ny] = true; 
				q.offer(new int[] {nx,ny,d+1});
			}
		}
		return -1; 
	}
	/**
	 * 택시 tx, ty로부터 가장 가까운 고객을 찾아 리턴하면 된다. 
	 * 만약 태울 수 있는 승객이 없으면 null을 리턴한다. 
	 * @return
	 */
	static User findNearUser() {
		User user; 
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N]; 
		PriorityQueue<User> pq = new PriorityQueue<>((o1, o2)->{
			if(o1.dist != o2.dist) return o1.dist - o2.dist; 
			if(o1.sx != o2.sx) return o1.sx - o2.sx; 
			return o1.sy - o2.sy; 
		});
		v[tx][ty] = true; 
		q.offer(new int[] {tx, ty, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int x = cur[0], y = cur[1], d = cur[2]; 
			if(map[x][y] > 0) {
				int idx = map[x][y] - 1;
				if(!serviced[idx]) {
					user = users.get(idx);
					user.dist = d; 
//					System.out.println(user);
					pq.offer(user);				
				}
			}
			for(int k=0; k<4; k++) {
				int nx = x + di[k]; 
				int ny = y + dj[k]; 
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue; 
				if(map[nx][ny] == -1) continue; 
				if(v[nx][ny]) continue; 
				v[nx][ny] = true; 
				q.offer(new int[] {nx, ny, d+1});
			}
		}
		if(pq.size() == 0) return null; 
		else {
			User u = pq.poll();
			serviced[u.id-1] = true;
			return u; 
		}
	}
	static int[] di = {-1, 0, 0, 1}; 
	static int[] dj = {0, -1, 1, 0}; 
	static List<User> users; 
	static boolean[] serviced; 
	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정사각형 배열 길이 
		M = Integer.parseInt(st.nextToken()); // 승객 명수 
		oil = Integer.parseInt(st.nextToken()); // 초기 연료 
		map = new int[N][N]; 
		users = new ArrayList<>(); 
		serviced = new boolean[M]; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1; // 벽은 -1로 저장하자.
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		tx = Integer.parseInt(st.nextToken())-1;
		ty = Integer.parseInt(st.nextToken())-1;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken())-1;
			int sy = Integer.parseInt(st.nextToken())-1;
			int dx = Integer.parseInt(st.nextToken())-1;
			int dy = Integer.parseInt(st.nextToken())-1;
			map[sx][sy] = i+1; 
			User u = new User(i+1, sx, sy, dx, dy, -1); // 각 사용자는 i+1의 값으로 지도에 표현되어있다. 
			users.add(u);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		input(br, st); 
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.printf("%3d", map[i][j]);
//			}
//			System.out.println();
//		}
//		
//		for(User u: users) {
//			System.out.println(u);
//		}
		int needTaxi = M; // 0이 되면 모든 승객을 태운 것
		boolean able = true; 
		/**
		 * 1. 어느 승객을 태울지 선택한다. 
		 * 2. 그 승객을 태우러 가는데 연료가 얼마나 필요한지 계산한다. - 최단경로 
		 * - 필요한 연료: tx,ty <-> sx,sy + sx,sy <-> tx,ty
		 * 3. 필요한 연료 > 현재 연료 -> 업무가 멈춘다. break 
		 * 4. 필요한 연료 <= 현재 연료 -> 이동한다. 
		 * - 연료 = 기존연료 - 필요한 연료 + 필요한 연료*2
		 * - 택시 최종 위치: user의 dx, dy 
		 */
		while(needTaxi > 0) {
//			System.out.println("택시 위치: "+ tx + ", " + ty);
			// 1. 택시 위치 기준 태울 승객들 
			User u = findNearUser();
			if(u == null) {
//				System.out.println("[error] 태울 수 있는 승객 없음");
				able = false; 
				break; 
			}
//			System.out.println(u);
			
			
			// 2. 연료 계산하기 
			int getUserOil = u.dist; // 승객 태우러 가는 길 
			tx = u.sx; ty = u.sy; 
			int goDestOil = calcDist(u.dx, u.dy); // 목적지로 가는 길
			/**
			 * 승객을 태우기는 했는데, 목적지로 못가는 예외상황이 있을 수 있다. 
			 */
			if(goDestOil == -1) {
				able = false; 
				break; 
			}
//			System.out.println("필요한 연료: " + getUserOil + ", " + goDestOil);
			
			
			// 3. 필요한 연료 > 현재 연료 -> 업무가 멈춘다. break 
			if(getUserOil + goDestOil > oil) {
//				System.out.println("[error] 연료 부족");
				able = false; 
				break; 
			}
			
			// 4. 필요한 연료 <= 현재 연료 -> 이동한다. 
			oil = oil - getUserOil - goDestOil + goDestOil*2; 
			tx = u.dx; ty = u.dy; 
			needTaxi--; 
			
					
		}
		// 모든 승객을 태운 경우 
		if(needTaxi > 0 || !able) {
			System.out.println(-1);
		}else {
			System.out.println(oil);
		}

	}

}
/*
손님 도착지 데려다주면 -> 연료 충전 
연료 바닥 -> 업무 끝 

nxn 배열 
m명의 승객 
좌표 이동 시 항상 최단경로로 이동 

한번에 한 승객만 태움 

승객 태우는 기준 
현 위치에서 최단거리 가장 짧은 승객 -> 행 번호 작은 -> 열 번호 작은 

연료 소모 - 이동 거리 
목적지까지 이동 성공 - 연료 = 소모한 연료*2 + 기존 연료 
가는 길에 연료 0 && 목적지 도착 안함 -> break 

우선순위에 부합하는 승객을 태우고 
그 거리는 모두 최단경로(bfs)로 이동한다. 이때 연료 계산 같이 
만약 모두 태웠다면 -> break 
그렇지 않고, 연료가 0이 되었다면 -> break 
연료도 남고, 승객도 남았다면 반복 


*/