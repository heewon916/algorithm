import java.io.*;
import java.util.*;
public class Main_bj_21608_상어초등학교 {
	static class Seat{
		int r, c, likes, blanks;

		public Seat(int r, int c, int no) {
			this.r = r;
			this.c = c;
			String[] info = bfs(r,c,no).split(" "); // 현재 위치에 대해서: 좋아하는학생수, 빈칸 수를 계산해온다.
			this.likes = Integer.parseInt(info[0]); 
			this.blanks = Integer.parseInt(info[1]); 
		}

		@Override
		public String toString() {
			return "앉을 수 있는 자리 정보: (" + r + ", " + c + ") -> likes=" + likes + ", blanks=" + blanks + "]";
		} 
		
	}
	/**
	 * 주변에 인접한 4칸에 대해서 좋아하는 학생 수 세기 
	 * @param r
	 * @param c
	 * @return
	 */
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; 
	static String bfs(int r, int c, int stdno) {
		int cnt = 0; 
		int blanks = 0; 
		for(int d=0; d<4; d++) {
			int nr = r+dr[d]; 
			int nc = c+dc[d]; 
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue; 
			if(res[nr][nc] == 0) blanks++; 
			for(int i=0; i<4; i++) {
				if(prefer[stdno][i] == res[nr][nc]) cnt++; 
			}
		}
		return cnt + " " + blanks; 
	}
	static int[][] prefer; 
	static int N; 
	static int[] seq; 
	static int[][] res; 
	static boolean[][] v; 
	static PriorityQueue<Seat> q = new PriorityQueue<>((o1, o2)-> {
		if(o1.likes == o2.likes) {
			if(o1.blanks == o2.blanks) {
				if(o1.r == o2.r) return o1.c- o2.c; 
				else return o1.r - o2.r;
			}
			else {
				return o2.blanks - o1.blanks; 
			}
		}else {
			return o2.likes - o1.likes; 
		}
	}); // r,c,주변에 좋아하는 학생 수, 인접한 빈칸 개수, 이번에 앉는 학생 번호 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		prefer = new int[N*N+1][4]; // prefer[i]:i번 학생이 좋아하는 애들 번호~ 
		seq = new int[N*N]; // 앉힐 순서 
		res = new int[N][N]; // 최종적으로 앉힌 결과물
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int me = Integer.parseInt(st.nextToken());
			seq[i] = me; 
			for(int j=0; j<4; j++) {
				prefer[me][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/**
		 * 전체 학생을 모두 앉힐 때까지~
		 * 각 학생에 대해서 
		 * 1. 앉을 수 있는 곳을 모두 구하기 
		 * 2. poll해서 거기에 앉히고 
		 * 3. 방문처리 하기 ~
		 */
		
		v = new boolean[N][N];
		int seatCnt = 0; 
		while(seatCnt < N*N) {
			int toSeat = seq[seatCnt++]; // 이번에 앉을 학생 번호 
//			System.out.println("이번에 앉힐 학생 번호: " + toSeat);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!v[i][j])q.offer(new Seat(i, j, toSeat));
				}
			}
			
			for(Seat s: q) {
//				System.out.println(s);
				if(v[s.r][s.c]) continue; 	
				else {
					v[s.r][s.c]= true; 
					res[s.r][s.c] = toSeat; 
					break; 
				}
			}
			q.clear(); 
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(res[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("---------");
//			Seat s = q.poll(); 
		}
		
		int[] score = {0, 1, 10, 100, 1000}; 
		int ans = 0; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				String[] s = bfs(i, j, res[i][j]).split(" ");
				int likes = Integer.parseInt(s[0]);
				ans += score[likes];
			}
		}
		System.out.println(ans);
	}

}
/*
정사각형 배열에 애들 앉혀야 됨 

앉힐 순서 정했음 
순서대로 어디에 앉힐지 정해야됨 

어디 앉힐 지 우선순위 
각 학생에 대해서 
	전체 배열에 대해서 
		비어있으면 주변에 좋아하는 학생 몇 명이 앉아있는지 계산 
		좋아하는 학생 명수가 같으면, 빈 칸이 많은 순으로
			그것도 여러 개면 r 작은 순 -> c 작은 순 
*/