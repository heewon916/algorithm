package bj;
import java.io.*;
import java.util.*;
public class Main_bj_28422_XOR카드게임 {
    static int N;
    static int[] arr;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        /**
         * i번 카드까지 처리했을 때의 최대 점수 = dp[i]
         */

        int[] dp = new int[N+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i=2; i<N+1; i++){
            // 2장으로 묶었을 떄
            if(i>=2 && dp[i - 2] != -1){
                int currentScore = Integer.bitCount(arr[i] ^ arr[i-1]);
                dp[i] = Math.max(dp[i], dp[i-2] + currentScore);
            }
            // 3장으로 묶었을 때
            if(i>=3 && dp[i - 3] != -1){
                int currentScore = Integer.bitCount(arr[i] ^ arr[i-1] ^ arr[i-2]);
                dp[i] = Math.max(dp[i], dp[i-3] + currentScore);
            }
//            System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[N]==-1? 0: dp[N]);

    }
}
