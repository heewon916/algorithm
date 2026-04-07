package bj;

import java.io.*;
import java.util.*;

public class Main_bj_1937 {
    static int n;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        StringTokenizer st = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][n];
        int result = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                result = Math.max(result, dfs(i, j));
            }
        }
//        for(int i=0; i<n; i++) System.out.println(Arrays.toString(dp[i]));

        System.out.println(result);
    }

    static int dfs(int i, int j){
        if(dp[i][j] != 0) return dp[i][j];

        dp[i][j] = 1;

        for(int d=0; d<4; d++){
            int ni = i+di[d], nj = j+dj[d];
            if(ni<0 || ni>=n || nj<0 || nj>=n) continue;
            if(map[ni][nj]>map[i][j]) {
                dp[i][j] = Math.max(dp[i][j], dfs(ni, nj)+1);
            }
        }
        return dp[i][j];
    }

}

/*
중요했던 거:
dfs함수를 짤 때 핵심이 dp[x][y]기 0일 때는 dfs가 한번도 안 지나간 처음 방문하는 곳이어서 무조건 4방향으로 dfs 탐방을 하게 되고,
0이 아닐 때는 이미 계산 했던 곳이니까 또 dfs 안 돌리고 그 자리의 dp[x][y]값을 리턴한다 (그래야 중복 계산을 막음)

시간복잡도: dfs 안에는 계산 안하냐
결국 모든 정점을 딱 한 번씩만 방문하면 되기 때문에,
전체 시간복잡도는 정점의 개수(N^2)에 비례하게 되는 것.
 */
