
import java.io.*;
import java.util.*;

public class Main{
    static int N, K;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(st.nextToken());
            list.add(n);
        }
        list.addAll(list);
        int maxSum = 0;
        int[] dp = new int[N+K];
        dp[0] = list.get(0);
        for(int i=1; i<K; i++){
            dp[i] = dp[i-1] + list.get(i);
            maxSum = Math.max(maxSum, dp[i]);
        }
        for(int i=K; i<N+K; i++){
            dp[i] = dp[i-1] + list.get(i) - list.get(i-K);
            maxSum = Math.max(maxSum, dp[i]);
        }
        System.out.println(maxSum);
    }
}
/*
N개의 애플파이
연속으로 K개를 먹음

애플파이 맛의 합의 최댓값

K, N 10**5

 */