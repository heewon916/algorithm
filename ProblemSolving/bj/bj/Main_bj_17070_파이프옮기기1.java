package bj;
import java.io.*;
import java.util.*; 
public class Main_bj_17070_파이프옮기기1 {
	static int N; 
	static int[][] map;
	// 옆으로 밀거나, 아래로 밀거나, 대각선으로 밀거나 
	static int[] di = {0, 1, 1};
	static int[] dj = {1, 0, 1}; 
	static class Pipe{
		int sx, sy, dx, dy; 
		int direction; 
		Pipe(int sx, int sy, int d){
			this.sx = sx; 
			this.sy = sy; 
			this.dx = sx+di[d];
			this.dy = sy+dj[d];
			this.direction = d;
		}
		@Override
		public String toString() {
			return "시작점: (" + sx + ", " + sy + ") 끝점: (" + dx + ", " + dy + ") 방향 =" + direction;
		}
		
	}
	static int count = 0; 
	static boolean outOfBound(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N) return true; 
		else return false; 
	}
	static boolean isWall(int x, int y) {
		if(map[x][y] == 1) return true; 
		else return false; 
	}
	static boolean isBlank(int sx, int sy) {
		for(int d=0; d<3; d++) {
			int nx = sx+di[d]; 
			int ny = sy+dj[d]; 
			if(map[nx][ny] != 0) return false; 
		}
		return true; 
	}
	static void dfs(Pipe p) {
		if(p.dx == N-1 && p.dy == N-1) {
			count++; 
			return; 
		}
		int dx = p.dx; 
		int dy = p.dy; 
		int dir = p.direction; 
//		System.out.println("현재 상태: " + p.toString());
		int nx_s = dx; 
		int ny_s = dy; 
		int nx_d, ny_d; 
		if(dir == 0) {
			// 옆으로 밀거나
			nx_d = nx_s;
			ny_d = ny_s+1;  
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) || isWall(nx_s, ny_s) || isWall(nx_d, ny_d)) {
				// do nothing 
//				System.out.println("--- 1. 옆으로 밀기 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 0); 
//				System.out.println("1. 옆으로 밉니다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
			// 대각선으로 돌리거나
			nx_d = dx+1;
			ny_d = dy+1;
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) 
					|| isWall(nx_s, ny_s) || isWall(nx_d, ny_d) 
					|| !isBlank(nx_s, ny_s)) {
				// do nothing 
//				System.out.println("--- 3. 대각선 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 2); 
//				System.out.println("3. 대각선으로 돌린다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
		}else if(dir == 1) {
			// 아래로 밀거나 
			nx_d = nx_s+1; 
			ny_d = ny_s;  
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) || isWall(nx_s, ny_s) || isWall(nx_d, ny_d)) {
				// do nothing 
//				System.out.println("--- 2. 아래로 밀기 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 1); 
//				System.out.println("2. 아래로 민다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
			// 대각선으로 회전하거나
			nx_d = nx_s+1; 
			ny_d = ny_s+1; 
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) 
					|| isWall(nx_s, ny_s) || isWall(nx_d, ny_d)
					|| !isBlank(nx_s, ny_s)) {
				// do nothing
//				System.out.println("--- 3. 대각선 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 2); 
//				System.out.println("3. 대각선으로 돌린다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
		}else if(dir == 2) {
			// 가로로 회전하거나 
			nx_d = nx_s; 
			ny_d = ny_s+1; 
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) || isWall(nx_s, ny_s) || isWall(nx_d, ny_d)) {
				// do nothing 
//				System.out.println("--- 1. 옆으로 밀기 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 0); 
//				System.out.println("1. 옆으로 돌린다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
			// 세로로 회전하면서 밀거나 
			nx_d = nx_s+1;
			ny_d = ny_s;
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) || isWall(nx_s, ny_s) || isWall(nx_d, ny_d)) {
				// do nothing 
//				System.out.println("--- 2. 아래로 밀기 불가능 ---");
			}else {
				Pipe np = new Pipe(nx_s, ny_s, 1); 
//				System.out.println("2. 아래로 민다.");
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
			// 대각선으로 밀거나
			nx_s = dx; 
			ny_s = dy; 
			nx_d = nx_s+1; 
			ny_d = ny_s+1;
			if(outOfBound(nx_s, ny_s) || outOfBound(nx_d, ny_d) 
					|| isWall(nx_s, ny_s) || isWall(nx_d, ny_d)
					|| !isBlank(nx_s, ny_s)) {
				// do nothing 
//				System.out.println("--- 3. 대각선 불가능 ---");
			}else {
//				System.out.println("3. 대각선으로 돌린다.");
				Pipe np = new Pipe(nx_s, ny_s, 2); 
//				System.out.println("=> 다음 상태: " + np.toString());
				dfs(np);
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로 방향: d = 0 
		// 세로 방향: d = 1
		// 대각선 방향: d = 2
		Pipe p = new Pipe(0, 0, 0);
		dfs(p);
		System.out.println(count);
	}

}
/*
배열 크기 n 
정사각형 배열
파이프 처음 방향 우 

1인 칸에는 있을 수 없음 
한쪽 끝이 n-1, n-1에 도달해야됨 
그 방법의 개수를 구해야됨 

x,y에 대해서 
	가로로 밀면 0,1
	대각선으로 밀면 1,1
	아래로 밀면 1,0 

시작점, 끝점이 있으면 
항상 시작점 <- 끝점이 되고, 끝점 <- 어느 방향으로 이동할거냐에 따라 달라짐 
내 방향: 
내 현재 위치가 i,j라고 하면 
가로이면 다음 시작점은 
	밀거나 -> i, j+1 (방향 유지)/ 끝점 = 시작점 + 우방향 
	대각선 -> i, j+1 (대각선 방향)/ 끝점 = 시작점 + 대각선 방향 
세로이면 
	아래로 밀거나 
대각선이면 
*/