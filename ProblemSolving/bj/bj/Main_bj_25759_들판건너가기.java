package bj;
import java.io.*;
import java.util.*;
public class Main_bj_25759_들판건너가기 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];
        for(int step=1; step<N; step++){
            for(int i=step; i<N; i++){
                int diff = arr[i] - arr[i-step];
                dp[step][i] = diff * diff ;
            }
        }
// dp[v]: 마지막으로 고른 꽃의 값이 v일 때, 만들 수 있는 아름다움 합의 최댓값

    }
}
