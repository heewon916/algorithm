import java.util.*; 
import java.io.*; 
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int[] jump2 = new int[N];
		int[] jump3 = new int[N]; 
		
		for(int i=1; i<N; i++) {
			jump2[i] = arr[i] - arr[i-1]; 
		}
		
		for(int i=2; i<N; i++) {
			jump3[i] = arr[i] - arr[i-2]; 
		}
		
		int[] leftSum = new int[N]; 
		int[] rightSum = new int[N]; 

		
		leftSum[1] = jump2[1];
		for(int i=2; i<N; i++) {
			if(i%2 ==1) {
				leftSum[i] = leftSum[i-2] + jump2[i]; 
			}else {
				leftSum[i] = leftSum[i-1]; 
			}
		}
		
		rightSum[N-2] = jump2[N-1]; 
		for(int i=N-3; i>=0; i--) {
			if(i%2 == 1) {
				rightSum[i] = rightSum[i+2] + jump2[i+1];  
			}else {
				rightSum[i] = rightSum[i+1]; 
			}
		}
//		System.out.println(Arrays.toString(rightSum));
		int ans = Integer.MAX_VALUE; 
		for(int i=0; i<N-2; i+=2 ) {
			int cost3 = arr[i+2] - arr[i]; 
			
			int costLeft = (i == 0)? 0: leftSum[i-1]; 
			int costRight = (i+3 >= N)? 0: rightSum[i+3];
			
			ans = Math.min(ans, cost3+costLeft+costRight);
		}
		System.out.println(ans);
	}

}
/*
어색함 수치 = 한 그룹 안의 학번 min과 max
min - max가 최소가 되게 
0 1 | 2 3 |4 5 6 |7
0 1 2 3 4 5 6 7 
2명인 그룹은 그냥 빼면 되고 3명인 그룹 1개만 min, max를 구하면 됨 
N 5*10^5

정렬하는 게 맞지 않을까 
1 2 3 100 101
0 1 1 97  1 
0 0 2 98  98 
 앞에서부터 2개씩은 말이 안됨 
  1 2 3 하고 100 101이 맞음 
  
  
 1 3 9 10 11 18 20
 0 2 6 1  1  7  2
 0 0 8 7  2  8  9 
 
  1 3 -- 2
  9 10 11 -- 2
  18 20 -- 2 
  
1초 

대충 250000개의 조가 나올 것임
어느 조를 3명으로 할지 -- 250000경우의 수 
각 경우마다 계산 - 500000
*/