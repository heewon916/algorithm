import java.io.*; 
import java.util.*; 

public class Main {
	static int N, K, P, X; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 최대 층수
		K = Integer.parseInt(st.nextToken()); // 자릿수 
		P = Integer.parseInt(st.nextToken()); // 반전
		X = Integer.parseInt(st.nextToken()); // 현재 층 수 
		String[] lights = new String[10];
		//.......... 0123456
		lights[0] = "1111110";
		lights[1] = "0110000";
		lights[2] = "1101101";
		lights[3] = "1111001";
		lights[4] = "0110011";
		lights[5] = "1011011";
		lights[6] = "1011111";
		lights[7] = "1110000";
		lights[8] = "1111111";
		lights[9] = "1111011";
		
		/**
		 * 현재 층에서 각 자릿수별로 0-9를 만들 수 있는 경우 
		 * 9**6
		 */
		
		int[][] turnCnt = new int[K][10]; 
		
		// 1의 자리부터 탐색 
		int tmp = X; 
		for(int i=K-1; i>=0; i--) {
			int now = tmp % 10; 
			tmp /= 10; 
			String nowBin = lights[now]; 
			for(int j=0; j<10; j++) {
				int diff = 0; 
				for(int k=0; k<7; k++) {
					if(nowBin.charAt(k) != lights[j].charAt(k)) diff++; 
				}
				turnCnt[i][j] = diff; 
			}
			
		}
		// 각 층이 되기 위해서 필요한 횟수 더해보기 
		int ans = 0; 
		for(int t=1; t<=N; t++) {
			if(t == X) continue; 
			if(check(t, turnCnt)) {
				ans++; 
			}
		}
		
		System.out.println(ans);
	}
	/**
	 * t층이 될 수 있는지를 체크해야 됨 
	 * 전체 자릿수의 반전횟수가 P번 이하이고 
	 * N층 이하인지 
	 * @param t
	 * @param turnCnt
	 * @return
	 */
	static boolean check(int t, int[][] turnCnt) {
		int totalDiff = 0; 
		int tmp = t; 
		for(int i=K-1; i>=0; i--) {
			int now = tmp % 10; 
			tmp /= 10;
			totalDiff += turnCnt[i][now];
		}
		return totalDiff <= P; 
	}
}

/*
1234
length = 4 
1234 / 10 = 123 1234 % 10 = 4 
123 / 10 = 12   123 % 10 = 3 
12 / 10 = 1     12 % 10 = 2 
1 / 10 = 0 		1 % 10 = 1 


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