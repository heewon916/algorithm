import java.io.*;
import java.util.*;
public class Main {
    static int N, M; // 회차 수, 무기 개수
    static int[][] input;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        input = new int[N][M]; // 각 회차마다, 각 무기가 클리어하는데 걸리는 시간

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];
        for(int i=0; i<M; i++) dp[0][i] = input[0][i];
//        System.out.println(Arrays.toString(dp[0]));
        for(int r=1; r<N; r++){
            for(int c=0; c<M; c++){
                int min = Integer.MAX_VALUE;
                for(int t=0; t<M; t++){
                    if(t == c) continue;
                    min = Math.min(min, dp[r-1][t]);
                }
                dp[r][c] = input[r][c] + min;
            }
//            System.out.println(Arrays.toString(dp[r]));
        }
        int result = Integer.MAX_VALUE;
        for(int i=0; i<M; i++){
            if(result > dp[N-1][i]) result = dp[N-1][i];
        }
        System.out.println(result);
    }
}
/*
능력치가 무작위 조정
회차마다 무기 바꾸진 않음
이전 회차에서 사용한 무기는 안 사용한다.
 3 2
 1 4


 */