

import java.io.*; 
import java.util.*; 

public class Solution_d4_1249_보급로_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		/*
		 * 출발지에서 도착지까지 가는 경로 중에 
		 * 복구 시간이 가장 짧은 경로 ==> 총 복구 시간 
		 * 깊이가 1이면 복구에 드는 시간이 1 
		 * 
		 * N <= 100 
		 * 출발지 (0,0) 도착지(N-1, N-1) => 데이터가 0으로 표기되어 있음 
		 * 상하좌우 이동, 한칸씩 이동 
		 * 지도 정보: 각 칸마다 파여진 도로의 깊이(0<= .. ) -> 복구해야만 다른 곳으로 이동 가능 
		 * 지도 정보에서 0이면 복구 작업이 불필요한 곳 
		 * 
		 * dp 같은데? 시간의 누적합 
		 */
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0822\\Input1249.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 입력 받아오기 
		// T <- 10 
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// N <- 지도 크기 (최대 100) 
			N = Integer.parseInt(br.readLine());
			// 2차원 배열 <- NxN 
			mat = new int[N][N]; 
			for(int i=0; i<N; i++) {
				String s = br.readLine(); 
				for(int j=0; j<N; j++) {
					mat[i][j] = s.charAt(j) - '0'; 
				}
			}
			// 0,0부터 탐색 시작 
			// 방법이 2가지일 듯; dp이거나, bfs로 최단 경로를 누적해서 계산하거나 
			// bfs로 해보자 
			// int time[][]: 각 위치까지 걸린 복구시간 (초기화값: Integer.Max_value) 
			time = new int[N][N]; 
			for(int i=0; i<N; i++) Arrays.fill(time[i], Integer.MAX_VALUE);
			// boolean visited[][] 방문 여부 체크 
//			visited = new boolean[N][N]; 
			// 시작점 (0,0)에 대해서 time이랑 방문 처리 
			time[0][0] = mat[0][0]; 
//			visited[0][0] = true; 
			
			
			// bfs시작: 큐 선언 <- (x,y)
			Queue<int[]> q = new LinkedList<>(); 
			q.add(new int[] {0, 0});
			while(!q.isEmpty()) {
				int[] cur = q.poll(); 
				int cx = cur[0], cy = cur[1]; 
				
				for(int d=0; d<4; d++) {
					int nx = cx+dx[d];
					int ny = cy+dy[d]; 
					if(0<=nx && nx<N && 0<=ny && ny<N) {
						if(time[nx][ny] > time[cx][cy] + mat[nx][ny]) {
							time[nx][ny] = time[cx][cy] + mat[nx][ny]; 
							q.add(new int[] {nx, ny});
						}
					}
				}

			}
			sb.append("#").append(t).append(" ").append(time[N-1][N-1]).append("\n");
			// cur <- poll  
			// visited[cx][cy] = true; 
			// 상하좌우 칸에서 갈 수 있는 곳 중에서
			// 범위를 안 벗어나고 방문을 아직 안 했으면 
			// 1. visited[nx][ny] = true; 
			// 2. int cur_time = 현재 위치까지 오는데 걸린 복구시간 
			// - cur_time < time[nx][ny] -> 복구시간 갱신
			// 		- time[nx][ny] = mat[cx][cy] + 현재 위치까지 오는데 걸린 복구시간 
			// - cur_time >= time[nx][ny] -> continue 
			// 마지막에는 time[N-1][N-1] 리턴 
			
		}
		System.out.println(sb.toString());

		
	}
	static int[] dx = {-1, 0, 1, 0}; 
	static int[] dy = {0, 1, 0, -1}; 
	static int[][] time; 
	static boolean[][] visited; 
	static int[][] mat; 
	static int N; 

}
