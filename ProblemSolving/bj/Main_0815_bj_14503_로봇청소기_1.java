
import java.io.*; 
import java.util.*;

public class Main_0815_bj_14503_로봇청소기_1 {
/*
 * 구현 + 시뮬레이션
 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M]; 
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean(r, c, d);
		System.out.println(clean_cnt);

	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] mat; 
	static int N, M, clean_cnt = 0 ; 
	static void clean(int r, int c, int d) {
		if(mat[r][c] == 0) {
			mat[r][c] = -1; 
			clean_cnt++; 
		}
		boolean done = true; 
		for(int i=0; i<4; i++) {
			// 주변의 4칸 중 
			int nr = r+dx[i]; 
			int nc = c+dy[i]; 
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;			
			// 청소되지 않은 빈칸이 있는 경우 
			if(mat[nr][nc] == 0) done = false; 
		}
		// 청소 전부 다 된 상태면  
		if(done) {
			// 방향 **유지**하고 한 칸 후진
//			d = (d+6) % 4; 	/*이건 유지하는 게 아니지*/
			int nr = r+dx[(d+6) % 4], nc = c+dy[(d+6) % 4]; 
			// 후진 가능: 뒤에가 벽이 아니면 후진하고 1번 
			// 후진 불가능: 뒤에가 벽이면 종료 
			if(mat[nr][nc] == 1) return; 
			else if(nr<0 || nr>=N || nc<0 || nc>=M) return; 
			else clean(nr, nc, d);	
		}
		// 청소 안된 빈 칸 있으면 
		else {
			for(int i=0; i<4; i++) { // 그 빈칸을 찾아야 됨 
				// 반시계 방향으로 회전하고 
				d = (d+3) % 4;
				int nr = r+dx[d], nc = c+dy[d]; 
				// 바라보는 방향을 기준으로 앞쪽 칸이 청소 안된 경우 1칸 전진 
				if(mat[nr][nc] == 0) {
					clean(nr, nc, d);
					return; 	/*이동했으면 다시 돌아오는 게 아니기 때문에 끊어야 해*/
				}
			}		
		}	
	}
}
