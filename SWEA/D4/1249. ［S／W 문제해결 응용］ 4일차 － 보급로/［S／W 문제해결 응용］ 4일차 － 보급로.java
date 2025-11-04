
import java.util.*; 
import java.io.*; 
public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		int[] dx = {-1, 1, 0, 0}; 
		int[] dy = {0, 0, -1, 1}; 
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N]; 
			for(int i=0; i<N; i++) {
				String input = br.readLine(); 
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(input.charAt(j)+"");
				}
			}
			
			int[][] dist = new int[N][N]; 
			for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[0][0] = map[0][0]; 
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2], o2[2]));
			pq.add(new int[] {0, 0, dist[0][0]});
			boolean[][] v = new boolean[N][N]; 
			while(!pq.isEmpty()) {
				int[] cur = pq.poll(); 
				int x = cur[0]; 
				int y = cur[1];
				int min = cur[2]; 
				
				if(v[x][y]) continue; 
				v[x][y] = true; 
				
				if(x == N-1 && y == N-1) continue; 
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i]; 
					int ny = y + dy[i]; 
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue; 
					
					if(dist[nx][ny] > min + map[nx][ny]) {
						dist[nx][ny] = min + map[nx][ny]; 
						pq.offer(new int[] {nx, ny, dist[nx][ny]});
					}
				}
			}
			sb.append("#"+tc+" ").append(dist[N-1][N-1] +"\n");
		}
		System.out.println(sb);
	}

}
