package bj;

import java.io.*;
import java.util.*; 
public class Main_0813_bj_11403_경로찾기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// i에서 j로 가는 길이 있는지 확인
		for(int i=0; i<N; i++) {
			answer = new boolean[N]; 
			bfs(i); 
			for(int j=0; j<N; j++) {
				if(answer[j]) System.out.print(1 + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
		

	}
	static int N; 
	static int[][] mat; 
	static boolean[] answer;
	static void bfs(int i) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(i);
//		answer[i] = true; 
		while(!q.isEmpty()) {
			int cur = q.poll(); 
//			answer[cur] = true; 
			for(int k=0; k<N; k++) {
				if(mat[cur][k] != 0 && answer[k] == false) {
					answer[k] = true; /*포인트*/
					q.add(k);					
				}
			}
		}
	}

}
