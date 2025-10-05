
import java.io.*; 
import java.util.*; 

public class Main_0815_bj_17136_색종이붙이기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		
		int[][] mat = new int[10][10]; 
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<10; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// for(int i=0; i<10; i++) {
		// 	for(int j=0; j<10; j++) {
		// 		if(mat[i][j] == 1)
		// 	}
		// }
		
	}
	static final int[] dx = {0, 1, 1}; 
	static final int[] dy = {1, 1, 0}; 
	
}

/*
 * 
 * 1인 최좌측상단 좌표에서 5부터 1까지 붙일 수 있는지 보는 거? 
 * 
 * ====
 * ? 현재까지 포함된 좌표를 어떻게 관리할 것인가
 * 아래 탐색 과정을 .. 1인 곳마다 
 * 1. 현재 위치 값이 1이면
 * - 3방향 체크  
 * - width==1이면 스택에 푸시 (width>1인 경우는 3방향 탐색 중인 거니까) 
 * 2. 3방향 모두 1이면 
 * - 스택에 3방향 모두 푸시 
 * - width + 1
 * - 만약 width == 5이면 더 이상 탐색 종료. 색종이 붙이기  
 * - 아니라면 그 3방향에 대해서 1번으로 가서 반복 
 * 3. 모두 1이 아니면 
 * - 탐색 종료. 스택에 푸시 안함 
 * 4. 색종이 붙이기 
 * - 현재 붙일 색종이 너비: width
 * - 만약 색종이가 모자른다면 더 이상 탐색 의미 없음. return -1; 
 * - 아니라면 남는 양 - 1하고, 스택 모두 pop한 후 mat[i][j] = -1로 초기화
 * - width = 1로 초기화 
 */