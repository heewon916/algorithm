package bj;
import java.util.*; 
import java.io.*; 
public class Main_bj_1189_컴백홈 {
	static int R, C, K; 
	static char[][] map; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; 
		
		for(int i=0; i<R; i++) {
			String input = br.readLine(); 
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		

	}
}
/*
집에 갈 수 있는 길 모두 구하고, 그 중에서 거리가 K인 것만 
*/
