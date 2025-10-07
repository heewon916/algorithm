package bj;

import java.io.*;
import java.util.*;

public class Main_0815_bj_1182_부분수열의합 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// N개의 정수 중에서, 부분집합 중 합이 S가 되도록
		subset(0, 0, 0);
		System.out.println(res_cnt);
		
//		res_cnt = 0; 
//		for(int i=1; i<N+1; i++) {
//			b = new int[i];
//			comb(0, 0, 0, i);
//		}
//		System.out.println(res_cnt);
	}
	static int N, S, res_cnt=0; 
	static int[] arr; 
	static void subset(int cnt, int i, int sum) {
		if(i==N){
			if(cnt > 0 && sum == S) res_cnt++; 
			return; 
		}
		/*
		if(cnt > 0 && sum == S) {
			System.out.println(cnt + " " + sum);
			res_cnt++; 
			// return; // sum==S되면 즉시 리턴 -> 그 뒤에 음수+양수로 또 다른 예시가 존재할 수 있음
		}
		if(i == N) return; 
		*/
		subset(cnt+1, i+1, sum+arr[i]);
		subset(cnt, i+1, sum); 
	}
	
//	static int[] b; 
//	static void comb(int cnt, int start, int sum, int depth) {
//		if(cnt == depth) {
//			if(sum == S) {
////				System.out.println(Arrays.toString(b)); 
//				res_cnt++; 
//			}
//			return; 			
//		}
//		for(int i=start; i<N; i++) {
//			b[cnt] = arr[i]; 
//			comb(cnt+1, i+1, sum+arr[i], depth);
//		}
//	}
}
