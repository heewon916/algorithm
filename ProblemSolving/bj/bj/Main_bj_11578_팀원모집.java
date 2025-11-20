package bj;
import java.io.*; 
import java.util.*;
public class Main_bj_11578_팀원모집 {
	static int N, M; 
	static int[] b; 
	static boolean[] v; 
	static int[][] input; 
	static int minTeam; 
	static void comb(int cnt, int start, int R) {
		if(cnt == R) {
			// 팀원을 모두 선택했다 
//			System.out.println(Arrays.toString(b));
			
			// 그때 문제를 모두 풀 수 있는지 체크한다 
			int solveCnt = 0;
			int teamCnt = 0; 
			boolean[] solveCheck = new boolean[N+1];
			for(int i=1; i<=M; i++) {
				if(b[i] == 1) { // 팀에 속한 학생이면 그 학생이 풀 수 있는 문제들을 체크한다 
					teamCnt++; 
					for(int j=1; j<=N; j++) {
						if(!solveCheck[j] && input[i][j] == 1) {
							solveCheck[j] = true; 
							solveCnt++; 
						}
					}
				}
			}
			
			// 풀 수 있다면 그때 그 명수가 최소라면 업데이트 
			if(solveCnt == N) {
				minTeam = Math.min(minTeam, teamCnt);
			}
			return; 
		}
		for(int i=start; i<=M; i++) {
			b[i] = 1; 
			comb(cnt+1, i+1, R);
			b[i] = 0; 
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 문제 수 
		M = Integer.parseInt(st.nextToken()); // 학생 수 
		input = new int[M+1][N+1]; 
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			for(int j=1; j<=n; j++) {
				int no = Integer.parseInt(st.nextToken());
				input[i][no] = 1; 
			}
		}
		// 학생마다 - 풀 수 있는 문제 번호 리스트 
		// 각 학생 중, 누군가를 팀에 넣을지 1명부터 M명까지 고르는 선택지가 있겠네 
		b = new int[M+1]; // 누구를 팀에 넣을지 
		v = new boolean[M+1];
		minTeam = Integer.MAX_VALUE; 
		for(int r=1; r<=M; r++) {
			comb(0, 1, r);
		}
		if(minTeam == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minTeam);			
		}
	}

}
