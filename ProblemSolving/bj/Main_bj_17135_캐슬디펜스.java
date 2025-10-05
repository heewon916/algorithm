import java.io.*;
import java.util.*;

public class Main_bj_17135_캐슬디펜스 {
	static int[] b; 
	static int di = -1; 
	static int[] dj = {-1, 0, 1}; 
	static int enemies;
	// 행, 열, 거리 
	static PriorityQueue<int[]> p1 = new PriorityQueue<>((o1, o2) -> {
		if(o1[2] == o2[2]) { // 거리가 같다면 
			if(o1[1] == o2[1]) return o1[0] - o2[0]; 
			else return o1[1] - o2[1]; // 열의 값이 더 작은 애로 
		}else {
			return o1[2] - o2[2]; 
		}
	});
	static PriorityQueue<int[]> p2 = new PriorityQueue<>((o1, o2) -> {
		if(o1[2] == o2[2]) { // 거리가 같다면 
			if(o1[1] == o2[1]) return o1[0] - o2[0]; 
			else return o1[1] - o2[1]; // 열의 값이 더 작은 애로 
		}else {
			return o1[2] - o2[2]; 
		}
	});
	static PriorityQueue<int[]> p3 = new PriorityQueue<>((o1, o2) -> {
		if(o1[2] == o2[2]) { // 거리가 같다면 
			if(o1[1] == o2[1]) return o1[0] - o2[0]; 
			else return o1[1] - o2[1]; // 열의 값이 더 작은 애로 
		}else {
			return o1[2] - o2[2]; 
		}
	});
	static List<PriorityQueue<int[]>> pList = new ArrayList<>();
	static int result = 0; 
	static void comb(int cnt, int start) {
		if(cnt == 3) {
			int toKill = enemies; 
			int[][] tmp = new int[N][M]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					tmp[i][j] = map[i][j]; 
				}
			}
//			System.out.println(Arrays.toString(b)); // 궁수의 위치는 모두 b에 저장되어 있음 
			int[] p_cols = new int[3]; 
			int idx = 0; 
			for(int i=0; i<M; i++) {
				if(b[i] == 1) p_cols[idx++] = i; 
			}
			// p1.clear(); p2.clear(); p3.clear(); 
			for(int i=0; i<3; i++) pList.get(i).clear(); 
			int killed = 0; // 이번 턴에서 죽인 적의 명수 
			while(toKill > 0) {
				for(int p=0; p<3; p++) { // 각 궁수마다 
//					boolean canKill = false; // 한 명의 적만 죽일 수 있다. 
					/**
					 * N < D인 경우가 있을 수 있으므로, 탐색 범위 조심해야 한다. 
					 */
					for(int r=N-1; r>=(N-D>=0?N-D:0); r--) {
						for(int c=0; c<M; c++) {
							int dist = Math.abs(r-N) + Math.abs(c-p_cols[p]);
							if(dist<=D && tmp[r][c] == 1) {
								pList.get(p).add(new int[] {r, c, dist});
//								canKill = true; 
							}
//							if(canKill) break; => 발견했다고 끝내면 안돼. 전체 중에서 우선순위가 가장 높은 적을 죽이는 건데.. !
						}
					}
					/*
					boolean canKill = false; 
					
					for(int step=1; step<=D; step++) {
						int ni = N + di * step; 
						if(ni <0 || ni >= N) continue; 
						for(int d=0; d<3; d++) {
							int nj = p_cols[p] + dj[d] * (D-step); 
							if(nj<0 || nj>=M) continue; 
							if(tmp[ni][nj] == 1) {
								pList.get(p).add(new int[] {ni, nj, Math.abs(ni-N) + Math.abs(nj-p_cols[p])}); 
								canKill = true; 
							}
						}
						if(canKill) break; 
					}
					 */
				}
				Set<String> set = new HashSet<>(); 
				for(int p=0; p<3; p++) {
					int[] get = pList.get(p).poll(); 
					if(get == null) continue; 
					set.add(get[0]+" "+get[1]);						
//					set.add("%d %d".formatted(get[0], get[1]));						
//					while(!pList.get(p).isEmpty()) {
//					}
				}
				for(String s: set) {
					String[] pos = s.split(" ");
					int i = Integer.parseInt(pos[0]);
					int j = Integer.parseInt(pos[1]);
//					System.out.println("삭제할 위치: " + i + ", " + j);
					tmp[i][j] = 0; 
				}
				killed += set.size(); 
				
				toKill = 0; 
				for(int j=0; j<M; j++) {
					for(int i=N-1; i>0; i--) {
						tmp[i][j] = tmp[i-1][j];
					}
				}
				for(int j=0; j<M; j++) {
					tmp[0][j] = 0; 
				}
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(tmp[i][j] == 1) toKill++; 
					}
				}
				
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<M; j++) {
//						System.out.print(tmp[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("-------------------");
				//p1.clear(); p2.clear(); p3.clear(); 
				for(int i=0; i<3; i++) pList.get(i).clear(); 
			}
			result = Math.max(result, killed);
//			System.out.println("result = " + result);
			return ; 
			
		}
		for(int i=start; i<M; i++) {
			b[i] = 1; 
			comb(cnt+1, i+1);
			b[i] = 0; 
		}
	}
	static int N, M, D; 
	static int[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemies = 0; 
		map = new int[N][M]; // 인덱스 N 행에는 궁수들이 있을 곳
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enemies++; 
			}
		}
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		b = new int[M]; // 궁수가 있을 자리 정하기 
		comb(0, 0);
		
		System.out.println(result);
		
	}

}
