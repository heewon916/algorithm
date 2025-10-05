
import java.util.*; 
import java.io.*;

public class Main_0813_bj_14567_선수과목 {

	public static void main(String[] args) throws Exception {
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 선수 조건의 수 
		List<Integer>[] g = new List[N+1];
		int[] indegree = new int[N+1]; 
		for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g[a].add(b);
			indegree[b]++; 
		}
		int[] resSemester = topoSort(indegree, g, N); 
		
		for(int i=1; i<N+1; i++) {
			sb.append(resSemester[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	static int[] topoSort(int[] indegree, List<Integer>[] g, int N) {
		// 진입차수 0을 들을 때마다 학기+1 
		ArrayDeque<Integer> q = new ArrayDeque<Integer>(); 
		int[] resSemester = new int[N+1]; 
		// 진입차수가 0인 것을 큐에 넣는다. 
		for(int i=1; i<N+1; i++) {
			if(indegree[i] == 0) {
				q.add(i);
				resSemester[i] = 1; 
			}
		}
		// 언제 학기를 올릴 것인가? 진입차수가 0인 것들을 모두 넣었을 때 
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int a: g[cur]) {
				if(--indegree[a] == 0) {
					q.add(a);
					resSemester[a] = resSemester[cur]+1; 
				}
			}			
		}
		return resSemester; 
	}
}
