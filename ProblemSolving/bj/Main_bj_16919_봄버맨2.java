import java.io.*;
import java.util.*;

public class Main_bj_16919_봄버맨2 {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1}; 
	static int R, C; 
	static char[][] g; 
	
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
		PriorityQueue<int[]> bombs = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]); // r,c,설치된 시간
	
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				g[i][j] = input.charAt(j);
				if(g[i][j] == 'O') bombs.offer(new int[] {i, j, 0});
			}
		}
		if (N <= 1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(g[i][j]);
				}
				System.out.println();
			}
		} else if (N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
		} else {
			/**
			 * N이 홀수이면, 2초부터 직접 그냥 돌려줘보자. 
			 */
			int time = 2; 
			if(N>=7) N = N - 4*(N/4);
//			System.out.println("우리가 구해야 할 시간은? " + N);
			while(true) {
				System.out.println("지금 시점은: " + time);
				// 그 시간에 해야 할일을 하자. 
				if(time%2 == 0) { // 짝수 초일 때는 일단 다 채운다. 
					System.out.println("@@@ 폭탄을 채웁니다.");
					for(int i=0; i<R; i++) {
						for(int j=0; j<C; j++) {
							if(g[i][j] == '.') {
								// 거기는 채워지는 공간이 된다. 
								g[i][j] = 'O'; 
								bombs.offer(new int[] {i, j, time});
							}
						}
					}					
					for(int[] c: bombs) {
						System.out.println("==> 터질 좌표: " +c[0] +", " + c[1]+ ", 터질 시점: " + c[2]);
					}
				}
				else {
					// 홀수 초일 때는 3초 전의 폭탄들을 모두 터트린다. 
					System.out.println("### 폭탄을 터트립니다. ");
					int toBombTime = time-3; 
					while(true) {
						int[] c = bombs.peek(); 
						System.out.println("==> 터질 좌표: " +c[0] +", " + c[1]+"/ 터질 수 있나? " + (c[2] == toBombTime));
//						if(c[2] != toBombTime) break; 
						
						// 만약 지금 시점에 터질 폭탄이면 
						if(g[c[0]][c[1]] == '.') c.
						c = bombs.poll();
						g[c[0]][c[1]] = '.';
						// 이웃한 곳에 폭탄 있으면 같이 터져야지 
						for(int d=0; d<4; d++) {
							int nr = c[0] + di[d]; 
							int nc = c[1] + dj[d]; 
							if(nr<0|| nr>=R||nc<0||nc>=C) continue; 
							if(g[nr][nc] == 'O') g[nr][nc] = '.';
						}
					}
				}
				System.out.println("---지금 상태---");
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						System.out.print(g[i][j]);
					}
					System.out.println();
				}
				// break 
				if(time == N) break; 
				time++; 	
				
			}
			System.out.println("결론: ");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(g[i][j]);
				}
				System.out.println();
			}
			
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
 * 202 
 * 000 
 * 202
 * 
 * 4초 -> 모든 칸에 폭탄 존재 
 * 242 
 * 444 
 * 242
 * 
 * 5초 -> 1초와 동일해짐 
 * 000 
 * 040 
 * 000
 * 
 * 6초 
 * 666
 * 646
 * 666
 * 
 * 7초 -> 4초에 설치된 폭탄 폭타 => 3초랑 같아짐 
 * 606
 * 000
 * 606
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