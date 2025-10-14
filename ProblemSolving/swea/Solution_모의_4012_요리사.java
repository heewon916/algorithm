import java.io.*; 
import java.util.*; 

public class Solution_모의_4012_요리사 {
	static int N; 
	static int[][] scores; 
	static int[] b; 
	static int minScore; 
	static void comb(int cnt, int start) {
		if(cnt == N/2) {
//			System.out.println("b: " + Arrays.toString(b));
			int score1 = 0; 
			int score2 = 0; 
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					if(b[i] == 1 && b[j] == 1) score1 = score1 + scores[i][j] + scores[j][i]; 
					if(b[i] == 0 && b[j] == 0) score2 = score2 + scores[i][j] + scores[j][i]; 
				}
			}
//			System.out.println("점수:" + score1 + " " + score2);
//			System.out.println(Math.abs(score2 - score1));
			minScore = Math.min(minScore, Math.abs(score2 - score1));
		}
		for(int i=start; i<N; i++) {
			b[i] = 1; 
			comb(cnt+1, i+1);
			b[i] = 0; 
		}
	}
	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		b = new int[N]; 
		scores = new int[N][N]; 
		minScore = Integer.MAX_VALUE; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		st = new StringTokenizer(br.readLine());
	
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
//			System.out.println("케이스: " + tc);
			input(br, st);
			comb(0, 0);
			sb.append("#").append(tc).append(' ').append(minScore).append("\n");
		}
		System.out.println(sb);
	}

}
/*
N개의 식재료 
반반으로 나눠서 2개의 요리 
맛 = 시너지들의 합 
시너지 = 식재료i와 식재료j를 함께 요리할 때 
두 음식의 맛 차이 최소가 되게 하기 

식재료N개 
재료 인덱스 i, j라고 하면 
mat[i][j] + mat[j][i] 한 게 합친 것의 시너지 

N 최대 16 
시너지 값 20000 8개 더해도 int로 충분 

조합? NCN/2 = 16C8 = 12,870
*/