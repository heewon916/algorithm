import java.io.*; 
import java.util.*; 
public class Main {
	static int[][] map; 
	static int[][] spins; // r,c,s (r,c는 1부터 시작하니까 인덱스로 쓰려면 -1 해야됨) 
	static int N, M, K; 
	static int result; 
	static void init(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		spins = new int[K][3]; 
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<3; j++) {
				spins[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static int[] b;
	static boolean[] v; 
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0}; 
	static void cycle(int r, int c, int s, int[][] map) {
		int sr = r-s, sc = c-s; 
		int len = 2*s + 1; 
		int prev = -1;
		for(int d=0; d<4; d++) {
			int l = 0; 
			while(true) {
				int nr = sr+dr[d]; 
				int nc = sc+dc[d]; 
				l++;
				if(l == len) break; 
//				System.out.println("길이: " + l + "- 이동: " + sr + ", " + sc + " => " + nr + ", " + nc);
				int tmp = map[sr][sc];
				map[sr][sc] = prev; 
				prev = tmp; 
				sr = nr; sc = nc; 
			}
		}
		map[r-s][c-s] = prev; 
	}
	static void print(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void perm(int cnt) {
		if(cnt == K) {
//			System.out.println(Arrays.toString(b));
			// 이제 이 회전순서대로 배열이 회전해야 됨. 
			// 원본 배열을 돌리면 안됨. 
			int[][] mat = new int[N][M]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					mat[i][j] = map[i][j]; 
				}
			}
			// 각 회전정보들을 실행하기 위해서 K번 돌린다. 
			for(int i=0; i<K; i++) {
				// r,c,s이면 s가 0,1,..s까지 돈다. 
				int turn = b[i]; // 이번에 실행할 회전 정보의 인덱스 
				int r = spins[turn][0]-1; 
				int c = spins[turn][1]-1; 
				int s = spins[turn][2]; 
//				System.out.println("# 회전정보: " + r + ", " + c + ", " + s);
				for(int j=1; j<=s; j++) {
//					System.out.println("한변의길이 = " + j + " 회전 후");
					cycle(r, c, j, mat);
//					print(mat); 
				}
			}
			// 각 행의 합을 모두 구하고, 그때의 최솟값으로 res를 갱신해야 한다. 
			for(int i=0; i<N; i++) {
				int tmp = 0; 
				for(int j=0; j<M; j++) {
					tmp += mat[i][j]; 
				}
				if(tmp<result) result = tmp; 
			}
		}
		for(int i=0; i<K; i++) {
			if(v[i]) continue; 
			b[cnt] = i; 
			v[i] = true; 
			perm(cnt+1);
			v[i] = false; 
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		init(br, st);
		
		/**
		 * 주어진 K개의 회전 정보들의 순서를 정해서, -> perm 
		 * 
		 * 각 회전 정보 순서대로 회전 시킨다.  
		 */
		b = new int[K]; //b[0]: 1번째로 실행될 회전순서 인덱스 값 
		v = new boolean[K];
		result = Integer.MAX_VALUE; 
		perm(0);
		
		System.out.println(result);
	}

}
/*
배열 nxm 
배열 a의 값 = 각 행의 합 중에서, min

회전연산
r,c를 기준으로 양옆, 위아래로 s칸씩 범위를 잡기 
시작점: r-s, c-s
끝점: r+s, c+s 

1<=r,c인 상태 

회전연산이 K개 주어지는데 이 순서에 따라서 최종 배열이 다르겠지? 
그때의 배열의 합이 여러개가 될텐데, 최솟값을 구하는 게 문제임 

제한시간은 1초 
N, M은 50 
K는 6까지 

배열 순회 -> 250 
회전 연산 최대 종류의 개수 6! = 30 24 = 720 
720가지의 결과물 -> 각 결과물 안에서 배열 순회 발생 
720 * ( s * 250) = 

한 변의 길이가 2s+1 최대 
어떤 s를 주더라도, r,c를 기준으로 하는 한 변의 길이가 2*s+1인 정사각형은 NxM 배열 안에 속한다. 


*/