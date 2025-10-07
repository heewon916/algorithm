package bj;
import java.io.*;
import java.util.*;

public class Main_bj_16919_봄버맨2 {
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
/*
 * 모든 폭탄 -> 설치되고 3초가 지난 후에 폭발한다. 폭발할 때, 인접 4칸에 폭탄 있으면 걔네도 같이 사라진다. 0초 초기상태: 일부
 * 폭탄이 설치되어 있음 1초 - (위와 동일한 상태) 2초 모든 빈칸 폭탄 설치 -> 5초에 폭탄 터질 것 3초 0초에 설치한 폭탄 터짐
 * 4초 모든 빈칸 폭탄 설치 -> 7초에 터질 폭탄들 5초 2초에 설치한 폭탄 터짐 6초 모든 빈칸 폭탄 설치 -> 9초에 터질 폭탄들 7초
 * 4초에 설치한 폭탄 터짐
 * 
 * N이 짝수일 때 폭탄을 설치하고, N이 홀수일 때 N-3초에 설치한 폭탄이 터진다~
 * =====
 * => 꼭 그렇지 않음. 예외 존재함 
 * 3 3 7 
 * .O.
 * O.O
 * .O. 의 경우를 살펴보자. ~~
 * 
 * 0, 1초 
 * -0-
 * 0-0
 * -0-
 * 
 * 2초 
 * 202
 * 020
 * 202
 * 
 * 3초 -> 0초 때 설치한 폭탄은 터질 것이다. 
 * ---
 * ---
 * ---
 * 
 * 4초 => 채움 
 * 444
 * 444
 * 444
 * 
 * 5초 -> 2초 때 설치한 폭탄 터질 것이다. 
 * 444
 * 444
 * 444
 * 
 * 6초 => 채움 
 * 444
 * 444
 * 444
 * 
 * 7초 -> 4초 때 설치한 폭탄 터짐 == 3초 
 * ---
 * ---
 * ---
 * 
 * 8초 => 채움 == 4초 
 * 888
 * 888
 * 888
 * 
 * 9초 -> 6초 때 설치한 폭탄 터짐
 * 888
 * 888
 * 888
 *  
 *  
 * 10초 => 채움 == 6초 
 * 888
 * 888
 * 888
 * 
 * 11초 -> 8초 때 설치한 폭탄 터짐 
 * ---
 * ---
 * ---
 * 
 * 
 * 
 * =====
 * 
 * 0, 1초 
 * --- 
 * -0- 
 * ---
 * 
 * 2초가 지난 후 -> 모든 칸에 폭탄 존재 
 * 222 
 * 202 
 * 222
 * 
 * 3초 -> 초기 그래프 기준으로 터트린다. (나머지 칸에는 폭탄 넣기) 
 * 2-2 
 * --- 
 * 2-2
 * 
 * 4초 -> 모든 칸에 폭탄 존재 
 * 242 
 * 444 
 * 242
 * 
 * 5초 -> 1초와 동일해짐 
 * ---
 * -4- 
 * ---
 * 
 * 6초 
 * 666
 * 646
 * 666
 * 
 * 7초 -> 4초에 설치된 폭탄 폭타 => 3초랑 같아짐 
 * 6-6
 * ---
 * 6-6
 * 
 * 1초 
 * ------- 
 * ---0--- 
 * ----0-- 
 * ------- 
 * 00----- 
 * 00-----
 * 
 * 2초
 * 
 * 2222222 
 * 2220222 
 * 2222022 
 * 2222222 
 * 0022222 
 * 0022222
 * 
 * 3초 
 * 2220222 
 * 2200022 
 * 2220002 
 * 0022022 
 * 0002222 
 * 0002222
 * 
 * 4초 
 * 2224222 
 * 2244422 
 * 2224442 
 * 4422422 
 * 4442222 
 * 4442222
 * 
 * 5초 
 * 0000000
 * 0004000 
 * 0000400 
 * 0000000 
 * 4400000 
 * 4400000
 * 
 * 5초일 때 원상복구가 되네 N초의 크기 상관없이 1초~4초의 모습을 찾아두고 N%5초일때의 모습을 리턴하면 되겠네
 * 
 * 
 * 
 * 
 * 
 */