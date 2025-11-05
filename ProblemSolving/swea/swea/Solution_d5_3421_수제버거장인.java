package swea;
import java.io.*; 
import java.util.*; 
public class Solution_d5_3421_수제버거장인 {
	public static int N, M; 
	public static boolean[][] conflict; 
	public static boolean[] v; 
	public static int ans; 
	public static void comb(int cnt, int start, int R) {
		if(cnt == R) {
			for(int i=1; i<N+1; i++) {
				for(int j=i+1; j<N+1; j++) {
					if(v[i] && v[j] && conflict[i][j]) return; 
				}
			}
			ans++; 
			return; 
		}
		for(int i=start; i<N+1; i++) {
//			boolean able = true; 
//			for(int j=1; j<N+1; j++) {
//				if(v[j] && conflict[i][j]) {
//					able = false; 
//					break; 
//				}
//			}
//			if(!able) continue; 
			v[i] = true; 
			comb(cnt+1, i+1, R);
			v[i] = false; 
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			conflict = new boolean[N+1][N+1];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				conflict[a][b] = true;  
				conflict[b][a] = true; 
			}
			/**
			 * N개의 재료 중에서 R개를 뽑는다. 이때 R개는 2개부터 체크하기 
			 * 이때, 뽑힌 애들은 v[i] = true; 처리를 하고 
			 * 나중돼서 conflict가 있는지만 체크하면 된다. 
			 */
			
			ans = 1 + N; // 공집합 + 재료 1가지씩은 무조건 가능하니까 
			for(int r=2; r<N+1; r++) {				
				v = new boolean[N+1];
				comb(0, 1, r);
			}
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		System.out.println(sb);
	}
}

/*
1...N 재료 
함께 하면 안되는 정점 존재 

20 정점 
공존 불가 M쌍 

함께 하면 안되는 것들이 있다 
2개의 그룹으로 나눈다


N 3개 M 2개 
1 2 
2 3 

2^20 = 10^6개의 경우의 수 
*/