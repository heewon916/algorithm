package bj;
import java.io.*;
import java.util.*;
public class Main_bj_1311 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][N+1]; // zero padding
        for(int i=0; i<=N; i++) dp[0][i] = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1] + map[i-1][j-1]);
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            ans = Math.min(dp[i][N], ans);
//            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(ans);

    }
}
