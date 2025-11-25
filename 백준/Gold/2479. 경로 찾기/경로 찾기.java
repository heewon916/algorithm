import java.io.*; 
import java.util.*; 

public class Main {
	static int N, K; 
	static ArrayList<String> codes; 
	static int ans_length; 
	static String ans_str; 
	static boolean[] v; 

	static int calcDist(int a, int b) {
		String s1 = codes.get(a); 
		String s2 = codes.get(b); 
		
		int diff_cnt = 0; 
		for(int i=0; i<K; i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				diff_cnt++; 
			}
		}
		return diff_cnt; 
	}

	static ArrayList<Integer>[] g; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		codes = new ArrayList<>(); 
		
		for(int i=0; i<N; i++) {
			codes.add(br.readLine()); // 코드 번호는 0번부터 시작시킵니다 
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken())-1; 
		int end = Integer.parseInt(st.nextToken())-1;
		
		v = new boolean[N]; 
		ans_length = Integer.MAX_VALUE; 
		ans_str = ""; 
		g = new ArrayList[N];
		for(int i=0; i<N; i++) g[i] = new ArrayList<>(); 
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int diff_cnt = calcDist(i, j);
				if(diff_cnt == 1) {
					g[i].add(j);
					g[j].add(i); 
				}
			}
		}
		int[] parent = new int[N]; 
		Arrays.fill(parent, -1);
		parent[start] = -2; 
		ArrayDeque<Integer> q = new ArrayDeque<>(); 
		q.add(start);
		v[start] = true; 
		while(!q.isEmpty()) {
			int cur = q.poll(); 
			if(cur == end) {
				break; 
			}
			for(int adj: g[cur]) {
				if(!v[adj]) {
					v[adj] = true; 
					parent[adj] = cur; // !!!!!!!!!!!!!!!!!! 
					q.offer(adj);
				}
			}
		}
		
		if(parent[end] != -1) {
			Stack<Integer> path = new Stack<>(); 
			int cur = end; 
			while(cur != -2) {
				path.push(cur+1);
				cur = parent[cur]; 
			}
			
			StringBuilder sb = new StringBuilder(); 
			while(!path.isEmpty()) {
				sb.append(path.pop() + " ");
			}
			System.out.println(sb);
		}else {
			System.out.println(-1);
		}
		
	}

}
/*
010010
011011 

N개의 이진 코드 길이는 K로 모두 같음 
N 1000 
K 30 


N개의 코드 1번부터 N번까지 코드를 갖고 있음 
이때 주어지는 2개의 코드 번호 사이에 해밍 거리가 있는지를 판단하기 

해밍 거리 계산 
- 두 코드 사이에 다른 숫자 개수가 1개인지를 세는 것임 -- K번 순회 필수 

그럼 지금 번호에서 갈 수 있는 곳이 어디인지를 찾아야 함 
- 1인 곳을 찾아야 하는 것임 
- 뭔가 dfs이면 좋을 것 같은데 


아니면 그냥 각 코드 간의 해밍거리를 다 계산을 미리 해두는 건 

128 * 10^6 
1000 * 1000 -> 10^6개에다가 

아니면 각 정점 간의 거리 계산할 때 1인 것만 넣을까? 
*/