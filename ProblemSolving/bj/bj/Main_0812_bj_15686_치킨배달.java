package bj;

import java.io.*; 
import java.util.*;

public class Main_0812_bj_15686_치킨배달 {
	static int N, M; 
	static int[][] mat; 
	static List<int[]> chickPos; 
	static List<int[]> homePos; 
	static int[][] b; 
	static int minDist, answer = Integer.MAX_VALUE;  
	static void comb(int cnt, int start) {
		if(cnt == M) {
			// 도시의 치킨 거리 계산하기 
			minDist = 0;
			for(int j=0; j<homePos.size(); j++) {
				int tempMinDist = Integer.MAX_VALUE;
				int eachHomeDist = 0; 
				int x = homePos.get(j)[0], y = homePos.get(j)[1];
				for(int i=0; i<M; i++) {
					// 하나의 집과 여러 치킨집의 거리를 구해본다. 
					eachHomeDist = (Math.abs(x-b[i][0]) + Math.abs(y-b[i][1]));
					// 그 중에서 최소거리를 찾아야 한다. 
					tempMinDist = Math.min(tempMinDist, eachHomeDist);
				}
				// 치킨집을 다 돌면 tempMinDist에는 한 집의 치킨거리가 나올 것이다. 얘를 더해주면서 도시의 치킨거리를 구한다.
				minDist += tempMinDist; 
			}
			// 집을 다 돌면 도시의 치킨 거리가 나오게 된다. 이러한 값들 중에서 최솟값을 찾아야 하는 것이다. 
			answer = Math.min(minDist, answer); 
			return; 
		}
		for(int i=start; i<chickPos.size(); i++) {
			b[cnt] = chickPos.get(i);
			comb(cnt+1, i+1);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 배열 크기 
		M = Integer.parseInt(st.nextToken()); // 치킨집 최대 개수 
		mat = new int[N][N]; 
		chickPos = new ArrayList<>();
		homePos = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int c = Integer.parseInt(st.nextToken());
				mat[i][j] = c; 
				if(c == 1) homePos.add(new int[] {i, j});
				if(c == 2) chickPos.add(new int[] {i, j});
			}
		}
		b = new int[M][2];
		
		comb(0, 0);
		System.out.println(answer);

	}

}
