import java.io.*;
import java.util.*;

public class Main {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1}; 
	static int R, C; 
	static char[][] g; 
	static int[][] bombTime; 
	static void debuggBombTime() {
//		System.out.println("시간대: ");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(bombTime[i][j] == -1) System.out.print('-');
				else System.out.print(bombTime[i][j]);
//				System.out.print(bombTime[i][j]);
			}
			System.out.println();
		}
	}
	static void print() {
//		System.out.println("결과물: ");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(bombTime[i][j] == -1) System.out.print('.');
				else System.out.print('O');
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken()) ; // N초 후 격자판의 상태
		/**
		 * N초를 차례대로 돌리는 건 안될 일
		 * 
		 * 만약 N이 짝수이면 전체 채워진 결과값을 내보내면 된다. 만약 N이 홀수이면 각 좌표가 갖는 값: 내가 폭탄인지, 언제 터지는 폭탄인지
		 */
		g = new char[R][C];
		bombTime = new int[R][C];
		for(int i=0; i<R; i++) Arrays.fill(bombTime[i], -1);
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				g[i][j] = input.charAt(j);
				if(g[i][j] == 'O') bombTime[i][j] = 0; 
			}
		}
		if(N%2 == 0) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
		}else {
			int time = 2; 
			/**
			 * N이 홀수 일 때, N = 9 이면 얘는 5초로 매칭되어야 하는데 
			 * 기존에 N = N - 4 * (N/4)로 해서 1초로 매칭이 되어버림. 
			 */
			if(N>=7) N = N - 4 * (N/4-1);
			while(time <= N) {
//				System.out.print("현재 시점은 " + time);
				if(time%2 == 0) {
					/**
					 * 채울 타이밍: 빈 곳이 있다면 
					 */
					for(int i=0; i<R; i++) {
						for(int j=0; j<C; j++) {
							if(bombTime[i][j] == -1) {
								bombTime[i][j] = time; 
							}
						}
					}
//					System.out.println("채웠습니다.");
//					debuggBombTime();
				}else {
					/**
					 * 터트릴 타이밍: 해당 타임과 동일한 숫자 값을 갖는 곳이 있다면! 
					 */
					ArrayList<int[]> bombList = new ArrayList<>();
					for(int i=0; i<R; i++) {
						for(int j=0; j<C; j++) {
							if(bombTime[i][j]+3 == time) {
								bombList.add(new int[] {i, j});
							}
						}
					}
					for(int[] a: bombList) {
						int i = a[0]; 
						int j = a[1]; 
						bombTime[i][j] = -1; 
						for(int d=0; d<4; d++) {
							int ni = i+di[d]; 
							int nj = j+dj[d]; 
							if(ni<0 || ni>=R || nj<0 || nj>=C) continue; 
							bombTime[ni][nj] = -1;
						}
					}
//					System.out.println("터졌습니다.");
//					debuggBombTime();
				}
				time++; 
			}
			print(); 
		}
	}

}