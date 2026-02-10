
import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n+1][n+1][3];
        if(map[1][1] == 0){
            dp[1][1][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                int current = map[i][j]; // 현재 위치에서 마실 수 있는 우유 번호
                int prev = (current + 2) % 3; // current를 마시려면 prev가 옆이나 위에 있어야 한다
                /**
                 * 현재 위치에서 우유를 안 마신다고 가정하면:
                 * - 이전 칸 i-1, j 또는 i, j-1 값을 그대로 계승함
                 *
                 * 현재 위치에서 우유를 마신다고 가정하면:
                 * 1. current == 0 인 경우: 이전에 아무것도 안 마셨거나(0개), prev = 2였어야 함
                 * 2. current > 0 인 경우: prev = current -1 이어야 함
                 *
                 * ++ 지금 안 마시는 게 이득일 수 있음
                 */
                // 일단 안 마시는 경우를 기본값으로 세팅하고 간다
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j-1][1]);
                dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i][j-1][2]);

                if(current == 0){
                    dp[i][j][current] = Math.max(dp[i][j][current], Math.max(dp[i-1][j][prev], dp[i][j-1][prev])+1);
                }else{
                    // 초코(1) 바나나(2) -> 이전에 prev를 마신 기록이 있어야만 가능함
                    int getPrev = Math.max(dp[i-1][j][prev], dp[i][j-1][prev]);
                    if(getPrev > 0){ // 이전에 마셨어서 이번에 마실 수 있는 순서이면
                        // 이 칸에서 우유를 안 마시고 그냥 넘어왔을 때랑
                        // 이 칸에서 순서가 맞아서 우유를 한 팩 더 마셨을 때랑 비교했을 때
                        // 그러니까 예전에 어디선가 이미 같은 종류의 우유를 더 많이 마시고 온 기록을 유지하는 게 나을 수도 있으니 비교함
                        dp[i][j][current] = Math.max(dp[i][j][current], getPrev+1);
                    }
                }
            }
        }
        System.out.println(Math.max(dp[n][n][0], Math.max(dp[n][n][1], dp[n][n][2])));
    }
}
