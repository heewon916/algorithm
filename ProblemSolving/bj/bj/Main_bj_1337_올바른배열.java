package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_1337_올바른배열 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n]; 
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		int[] tmp = new int[n]; 
		for(int i=0; i<n; i++) {
			int a = arr[i]; 
			for(int j=i; j<n; j++) {
				if(arr[j] < a+5) tmp[i]++; 
			}
		}
//		System.out.println(Arrays.toString(tmp));
		int max = 0; 
		for(int i=0; i<n; i++) {
			if(tmp[i]>max) max = tmp[i]; 
		}
		System.out.println((max>=5? 0: 5-max));
	}

}
/*
정렬했을 때 5개의 수가 연속적이면 된 것임 
배열 최대 크기 50 
수: 10^9보다 작거나 같은 0, 양수 
중복되는 수 없음 



*/