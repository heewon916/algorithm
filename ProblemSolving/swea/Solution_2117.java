
import java.util.*; 
import java.io.*; 
/**
 * bfs + 3중반복문으로 풀이 시, 
 * O(N * N * 2N * N^2) = O(N^5) ~= 
 * 
 * /** 풀이 기록 
 * 1. 처음에 3중 반복문 + dfs로 1인 곳(집)에 가서 상하좌우 + 맨해튼 거리 조건으로 확인하면서 1이면 count해서 되돌려줌 
 * -> 제한 시간 초과 발생 
 * 2. bfs가 느리니까 이걸 완탐으로 바꿈. 1인 곳(집)을 먼저 찾아서 배열로 저장해두고, 
 * 각 집으로 가서 맨해튼 거리를 계산해 그게 k보다 작으면 count++; 한다. 
 * 
 * 빡구현 문제 같은 
 * 
 * */

public class Solution_2117 {
	static int[][] mat; 
	static int N; 
	static int M; 
//	static int res = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
    
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Test2117.txt"));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
//		int T = 1; 
		for(int tc=1; tc<= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			mat = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1을 미리 받아놓고, 그 위치에 대해서만 계산하자. 			
			// 1인 곳을 먼저 count 
			List<int[]> homes = new ArrayList<int[]>();
//			int[][] getOnePos = new int[N][2]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mat[i][j] == 1) {
						homes.add(new int[]{i, j});
					}
				}
			}
			
			// 왜 내 이름이 안 뜨지? test
			int res_hCount = 0; 
			int cost, houseCount/*, currBenefit=0*/; 
			// 1인 곳에 대해서, K값을 달리하며 1 개수 count 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {	
					for(int k=1; k <= 2*N; k++) { // not N/2+1; yes 2*N
						cost = k*k + (k-1)*(k-1); // k값에 따라 운영비용 계산 후, 수익 계산해서 업데이트
						houseCount = 0; 
						for(int[] h: homes) {
							int nx = h[0], ny = h[1]; 
							int dist = Math.abs(i-nx) + Math.abs(j-ny);
							if(dist < k) houseCount++; 													
						}
											
						// 운영비용보다 수익이 크거나 같고, 그 중에서 가장 많은 집들에 제공하는 서비스 영역 
						// currBenefit은 조건이 아니다. 내 맘대로 해석하지 않기
						if(houseCount*M >= cost) {
							res_hCount = Math.max(res_hCount, houseCount); 
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(res_hCount).append("\n");
		}
		System.out.println(sb.toString());
	}	
}




