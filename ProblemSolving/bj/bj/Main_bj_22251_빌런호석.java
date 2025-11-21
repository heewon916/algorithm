package bj;
import java.io.*; 
import java.util.*; 

public class Main_bj_22251_빌런호석 {
	static int N, K, P, X; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		String[] lights = new String[10];
		//.......... 0123456
		lights[0] = "1111110";
		lights[1] = "0110000";
		lights[2] = "1101101";
		lights[3] = "1111001";
		lights[4] = "0110011";
		lights[5] = 
	}
}

/*
각 수를 표현하는데 들어오는 불의 위치를 저장 

현재 층에서 7개의 불을 on 또는 off할 때
- 그 수가 올바른 수인지: 각 자리의 수가 실존하는 표시인지 
- 그 수가 N보다 작은 수인지

층수 최대 10^6 
자릿수 최대 6 
한 수 표현하는 불 개수 7 
바꿀 수 있는 최대 개수 42 

자릿수가 6자리고 한 수가 7개의 불을 가지니까 총 42개 
42개의 불을 on, off하는 경우의 수 -> 2^42 2^10 = 10^3 ~ 10^12 
====
<<역으로 계산하는 방법>>
모든 층수를 후보로 두고 g 그 층으로 바꾸는데 필요한 반전 횟수


*/