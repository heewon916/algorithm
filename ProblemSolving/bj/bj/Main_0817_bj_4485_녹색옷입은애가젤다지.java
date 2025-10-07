package bj;

import java.util.*; 
import java.io.*;


public class Main_0817_bj_4485_녹색옷입은애가젤다지 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0, -1, 1};
	static int[][] mat; 
	static int N; 
	static int minPathDijkstra() {
		int[][] dist = new int[N][N];
		boolean[][] v = new boolean[N][N];
		
		for(int[] row: dist) Arrays.fill(row, Integer.MAX_VALUE);
		dist[0][0] = mat[0][0]; 
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
		pq.add(new int[] {mat[0][0], 0, 0}); // (가중치 값, 좌표x, y) 
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll(); 
			int cost=cur[0], x=cur[1], y=cur[2];
			if(v[x][y]) continue; 
			v[x][y] = true;
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d], ny = y+dy[d]; 
				if(0<=nx && nx<N && 0<=ny && ny<N) {
					int newCost = cost + mat[nx][ny];
					if (newCost < dist[nx][ny]) {
						dist[nx][ny] = newCost;
						pq.add(new int[] { newCost, nx, ny });
					}
				}
			}
		}
		return dist[N-1][N-1];
	}
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1; 
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			mat = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			 
			sb.append("Problem ").append(tc).append(": ").append(minPathDijkstra()).append("\n");
			tc++; 
			
		}
		System.out.println(sb.toString());
		
	}

}

