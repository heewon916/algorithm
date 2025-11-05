package swea;
import java.util.*; 
import java.io.*; 
public class Solution_d5_1247_최적경로 {
	public static int N, compX, compY, homeX, homeY; 
	public static int[][] pos; 
	public static boolean[] v; 
	public static int[] b; 
	public static int minDist;
	public static int calcDist(int x, int y, int a, int b) {
		return Math.abs(x-a) + Math.abs(y-b);
	}
	/**
	 * b를 기준으로 순서대로 집을 그냥 계산하면 될 것 같다 
	 */
	public static void bfs() {
		ArrayList<int[]> seq = new ArrayList<>(); 
		for(int i=0; i<N; i++) {
			seq.add(new int[] {i, b[i]});
		}
		seq.sort((o1, o2) -> Integer.compare(o1[1], o2[1]));
//		for(int i=0; i<N; i++) System.out.print(Arrays.toString(seq.get(i)) + " ");
		int idx = seq.get(0)[0];
		int dist = calcDist(compX, compY, pos[idx][0], pos[idx][1]);
		for(int i=1; i<N; i++) {
			int prev = idx; 
			idx = seq.get(i)[0]; 
			dist += calcDist(pos[prev][0], pos[prev][1], pos[idx][0], pos[idx][1]);
		}
//		System.out.println(" " + idx);
		dist += calcDist(pos[idx][0], pos[idx][1], homeX, homeY);
		minDist = Math.min(minDist, dist);
		System.out.println("minDist = " + minDist);
		return; 
	}
	public static void perm(int cnt) {
		if(cnt == N) {
			System.out.println(Arrays.toString(b));
			// 이 순서대로 회사 -> 0 ... -> 집까지 계산하면 될 듯 
			bfs();
			return; 
		}
		for(int i=0; i<N; i++) {
			if(v[i]) continue; 
			v[i] = true; 
			b[i] = cnt; 
			perm(cnt+1);
			v[i] = false; 
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 고객의 수 
			
			st = new StringTokenizer(br.readLine(), " ");
			compX = Integer.parseInt(st.nextToken());
			compY = Integer.parseInt(st.nextToken());
			
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			
			pos = new int[N][2]; 
			for(int i=0; i<N; i++) {
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			minDist = Integer.MAX_VALUE; 
			b = new int[N]; 
			v = new boolean[N]; // 고객 집 방문 표시 
			perm(0);
			sb.append("#" + tc + " ").append(minDist + "\n");
		}
		System.out.println(sb);
	}

}
/*
n명의 고객 모두 방문후 집으로 돌아오는 최단 경로 찾기 

회사 -> 모든 고객들 -> 내 집 

8!? 회사에서 어느 순서대로 고객들을 만날 것인지? 

아니면 회사에서 가까운 애들을 순서대로 먹기? 
그럼 프림인데 마지막 고객 -> 내 집이 최단 경로가 보장되는가? ㅇ모르겠음 

*/