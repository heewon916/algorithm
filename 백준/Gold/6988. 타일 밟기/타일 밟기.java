import java.io.*; 
import java.util.*; 
public class Main {
    static int N;
    static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][N]; // dp[j][i] = j를 밟고 i에 도착했을 때 밟아온 숫자들의 합
        int[] index = new int[1000001]; // arr[i] 값이 있던 인덱스에 1더한 거
        for(int i=0; i<N; i++){
            index[arr[i]] = i+1;
        }
        long ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){
                int diff = arr[i] - arr[j];
                int toFind = arr[j] - diff;
                if(toFind >= 1 && index[toFind] != 0){
                    int k = index[toFind] - 1; // 그 이전에 밟았어야 하는 값의 인덱스
                    dp[j][i] = dp[k][j] + arr[i];
                    ans = Math.max(dp[j][i], ans);
                }else{
                    dp[j][i] = arr[j] + arr[i];
                }
            }
        }
        System.out.println(ans);
	}

}
