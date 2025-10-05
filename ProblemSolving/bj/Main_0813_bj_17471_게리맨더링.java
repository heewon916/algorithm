

import java.io.*;
import java.util.*;

public class Main_0813_bj_17471_게리맨더링 {
	/*
	 * 조합 + dfs(백트래킹) + 인접리스트
	 */
	static int N; 
	static int[] people, a, b; 
	static boolean able = true; 
	static int minDiff = Integer.MAX_VALUE; 
	static List<Integer>[] g;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		people = new int[N+1];	// 인구 수 
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<N+1; i++){
			people[i] = Integer.parseInt(st.nextToken());
		}
		// 인접 리스트 
		g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>(); 
		for(int i=1; i<N+1; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			for(int j=0; j<c; j++){
				g[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		b = new int[N+1];
		for(int i=1; i<N+1; i++){
			able = true; 
			comb(0, 1, i); 	
			/* 틀린 부분 
			for(int j=0; j<2; j++) {
				able = checkConnected(j) && able; 
			}
			*/

		}
		if(minDiff != Integer.MAX_VALUE) sb.append(minDiff);
		else sb.append(-1);
		System.out.println(sb.toString());
	}
	static boolean checkConnected(int num) {
		/*
		 * @param: num; 0 또는 1 
		 * @return: boolean; 그룹 내에서 정점들이 모두 연결되어 있는지 
		 */
		List<Integer> li = new ArrayList<>(); 	// 0 또는 1인 정점만 들어감 
		boolean[] v = new boolean[N+1];			// 그러한 정점들에 대한 visited 배열 
		for(int i=1; i<N+1; i++){
			if(b[i] == num) li.add(i);
		}
			
		if(li.size() > 0) dfs(li.get(0), num, v);// 0이상일때만 체크; N=6일때 111111인 그래프 안됨. 			
		else return false; 
		
		for(int i=1; i<N+1; i++){				// 같은 그룹 내 정점들을 모두 방문했는지 체크 
			if(b[i] == num && !v[i]) return false; 
		}
		return true; 
	}
	static void dfs(int i, int num, boolean[] v){
		/*
		 * @param: i; 방문 정점 
		 * @param: num; 0 또는 1 
		 * @param: boolean[] v; 방문 배열 
		 */
		v[i] = true; 
		for(int a: g[i]){				
			if(b[a] == num && !v[a]){	// b[i]의 값이 num이고 i의 인접정점에 대해서 방문 
				dfs(a, num, v);
			}
		}
	}
	static void comb(int cnt, int start, int depth){
		/*
		 * @param: cnt; depth 길이만큼만 그룹 형성 
		 * @param: start; 어느 정점부터 조합 시작할 것인지 
		 * @param: depth; 조합에서 부분집합의 길이가 미정이기 때문에 1부터 N-1까지 필요
		 * 조합 만들기: b 배열을 0과 1로 적절히 채워, 그룹을 나눈다. 
		 */
		if(cnt == depth) {							// 조합이 만들어지면
			for(int j=0; j<2; j++) {					// 각 그룹이 연결되어 있는지 확인하고, 
				able = checkConnected(j) && able; 	// 두 그룹 모두 true 일 때만 성립 
			}
			if(able){								// 각 그룹의 인구 수 계산해 minDiff 업데이트
				int p_cnt1 = 0, p_cnt2 = 0; 
				
				List<Integer> li = new ArrayList<>();
				for(int j=1; j<N+1; j++){
					if(b[j] == 0) li.add(j);
				}
				for(int j=0; j<li.size(); j++) {
					p_cnt1 += people[li.get(j)];
				}
				li.clear();
				for(int j=1; j<N+1; j++){
					if(b[j] == 1) li.add(j);
				}
				for(int j=0; j<li.size(); j++) {
					p_cnt2 += people[li.get(j)];
				}
				minDiff = Math.min(minDiff, Math.abs(p_cnt1-p_cnt2));
			}
			return; 
		}
		for(int i=start; i<N+1; i++) {	// 조합의 핵심: 백트래킹 
//			b[cnt] = 1; 					// 몇 개 골랐냐가 아니라, 어느 위치를 골랐냐가 중요함 
			b[i] = 1; 					// i 위치에 1을 넣고
			comb(cnt+1, i+1, depth);		// 조합 만든 뒤
			able = true; 				
			b[i] = 0; 					// 다시 복구
		}
	}

}
