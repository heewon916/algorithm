import java.io.*; 
import java.util.*; 
public class Main {
	static int N,M; 
	static ArrayList<int[]> chickenList; 
	static ArrayList<int[]> homeList; 
	static int[] b; 
	/**
	 * M개의 치킨집을 고르면
	 * 각 집마다 그 치킨집 중 가장 가까운 치킨집과의 거리를 계산해서 갖고 있기 
	 * @param cnt
	 * @param start
	 */
	static int ans; 
	static void comb(int cnt, int start) {
		if(cnt == M) {
			int city_dist = 0; 
			for(int[] h: homeList) {
				int dist = Integer.MAX_VALUE; 
				for(int j=0; j<chickenList.size(); j++) {
					if(b[j] == 1) {
						int[] c = chickenList.get(j);
						dist = Math.min(Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]), dist);
					}
				}
				city_dist += dist; 
				if(city_dist > ans) return; 
			}
			ans = Math.min(ans, city_dist);
			return; 
		}
		for(int i=start; i<chickenList.size(); i++) {
			b[i] = 1; 
			comb(cnt+1, i+1); 
			b[i] = 0; 
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chickenList = new ArrayList<>(); 
		homeList = new ArrayList<>(); 
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) chickenList.add(new int[] {i, j});
				else if(map[i][j] == 1) homeList.add(new int[] {i, j});
			}
		}
		ans = Integer.MAX_VALUE; 
		b = new int[chickenList.size()]; 
		comb(0, 0);
		
		System.out.println(ans);
	}

}
/*
r행 c열 r,c >= 1 

집마다 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리 
도시 치킨 거리 = 치킨 거리의 합 

치킨집 중 M개를 고름 -> 조합 
- 각 경우에 대한 치킨 거리를 구해서 모두 더하는 거 (전역변수 min보다 커지면 그냥 아웃시키기) 

NxN 배열 
치킨집 좌표 -> 모두 챙겨야 됨 <= 13 
*/