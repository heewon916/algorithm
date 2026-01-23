
import java.io.*;
import java.util.*;
public class Main {
    static int N, K, B;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        arr = new int[N];
        Arrays.fill(arr, 1);
        for(int i=0; i<B; i++){
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())-1] = 0;
        }

        int[] zeroCnt = new int[N];
        zeroCnt[0] = (arr[0] == 0)? 1: 0;
        for(int i=1; i<N; i++){
            if(arr[i] == 0) zeroCnt[i] = zeroCnt[i-1]+1;
            else zeroCnt[i] = zeroCnt[i-1];
        }
//        System.out.println(Arrays.toString(zeroCnt));

        int[] dp = new int[N];
        dp[K-1] = zeroCnt[K-1]; // 0번부터 K-1번까지 고장난 개수
        for(int i=K; i<N; i++){
            // 구간 i-K+1 ~ i 까지의 고장난 개수 = 현재까지의 누적 - 구간 앞까지의 누적
            dp[i] = zeroCnt[i] - zeroCnt[i-K];
        }
//        System.out.println(Arrays.toString(dp));
        int ans = Integer.MAX_VALUE;
        for(int i=K-1; i<N; i++){
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
/*
N개의 횡단보도 10**5
연속한 K개의 신호등이 존재해야 함
고장난 신호등의 번호가 있음
연속 K개가 존재하려면 최소한 몇 개의 신호등을 고쳐야 할까

N = 10
K = 6
B = 5
0 1 2 3 4 5 6 7 8 9
0 0 1 1 0 1 1 1 0 0
1 2 2 2 3 3 3 3 4 5
0 0 0 0 0 3 2 1 2 2

6 - 0
7 - 1

 */