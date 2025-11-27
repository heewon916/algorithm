package bj;

import java.io.*; 
import java.util.*; 
public class Main_bj_13701_중복제거 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		Map<Integer, Integer> map = new HashMap<>(); 
		StringBuilder sb = new StringBuilder(); 
		while(st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(n)) continue; 
			else {
				sb.append(n + " ");
				map.put(n, 1);
			}
		}
		System.out.println(sb);

	}

}
