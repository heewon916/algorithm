
import java.util.*; 
import java.io.*; 
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int[] di = {-1, 1, 0, 0}; 
		int[] dj = {0, 0, -1, 1}; 
		int tc = 1; 
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break; 
			
			
			int[][] map = new int[N][N]; 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 하나의 위치에서 도착지점이 정해진 
			// 다익스트라 
			int[][] dist = new int[N][N]; 
			for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[0][0] = map[0][0]; 
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			pq.add(new int[] {0, 0, dist[0][0]});
			boolean[][] v = new boolean[N][N]; 
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll(); 
				int minX = cur[0];
				int minY = cur[1]; 
				int min = cur[2]; 
				
				if(v[minX][minY]) continue; 
				v[minX][minY] = true; 
				
				if(minX == N-1 && minY == N-1) break; 
				
				for(int i=0; i<4; i++) {
					int nx = minX + di[i]; 
					int ny = minY + dj[i]; 
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue; 
					if(dist[nx][ny] > min + map[nx][ny]) {
						dist[nx][ny] = min + map[nx][ny]; 
						pq.offer(new int[] {nx, ny, dist[nx][ny]});
					}
				}
			}
//			System.out.println(dist[N-1][N-1]);
			sb.append("Problem ").append(tc++).append(": ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}

}
/*
nxn 배열 

0,0 -> n-1, n-1 

칸에 있는 숫자만큼 사라짐 
비용을 최소로 
한번에 상하좌우 


*/