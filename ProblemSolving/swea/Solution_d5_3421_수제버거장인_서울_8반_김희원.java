
import java.util.*;
import java.io.*; 

public class Solution_d5_3421_수제버거장인_서울_8반_김희원 {
	static int[][] unable;
	static int count, N, M ; 
	static boolean[] isSelected; 
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0808/Test3421.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 궁합이 맞지 않는 재료들로 M개쌍에 대한 정보가 주였을
		// 주어지는 쌍들은 모두 다르지는 않고, 즉 같은 쌍이 여러 번 주어질 수도 있다.
		// 같은 종류의 재료 같은 종류의 버거
		// 제약조건을 만족시키며 만들 수 있는 버거의 가짓수
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
//			List<Integer>[] unAvails = new List[M];
//			for(int i=0; i<M; i++) unAvails[i] = new ArrayList<>(); 
			unable = new int[N+1][N+1];  
			isSelected = new boolean[N];
			count = 0; 
			// 만들 수 있는 조합을 모두 만들고, 그 조합 안에 불가능한 조합이 있으면 뺄까? 
			// 길이별로 조합을 만들어보면서, 그 조합이 불가능하면 카운트 하지 말까? 
			subSet(0);
			sb.append("#").append(t).append(" ").append(count).append("\n");
			count = 0; 
		}
		System.out.println(sb.toString());
	}
	static void subSet(int cnt) {
		if(cnt == N+1) {
			for(int i=0; i<N; i++) {
				
			}
		}
		isSelected[cnt] = true; 
		subSet(cnt+1);
		isSelected[cnt] = false; 
		subSet(cnt+1);
	}

}
