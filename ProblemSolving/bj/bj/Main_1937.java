package bj;

import java.io.*;
import java.util.*;

public class Main_1937 {
    static int n;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
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
        int[][] depth = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int cangoCount = 0;
                for(int d=0; d<4; d++){
                    int ni = i+di[d], nj = j+dj[d];
                    if(ni<0 || ni>=n || nj<0 || nj>=n) continue;
                    if(map[ni][nj] > map[i][j]) {
                        cangoCount++;
                    }
                }
                if(cangoCount == 0) {
                    len = 0;
                    dfs(i, j);
                    depth[i][j] = len;
                }
                else{
                    int arnd = 0;
                    for(int d=0; d<4; d++){
                        int ni = i+di[d], nj = j+dj[d];
                        if(ni<0 || ni>=n || nj<0 || nj>=n) continue;
                        if(map[ni][nj] > map[i][j]) {
                            arnd = Math.max(arnd, depth[ni][nj]);
                        }
                    }
                    depth[i][j] = arnd +1;
                }
            }
        }
        int answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(answer < depth[i][j]) answer = depth[i][j];
            }
        }
        System.out.println(answer+1);



    }
    static int len = 1;
    static void dfs(int i, int j){
        for(int d=0; d<4; d++){
            int ni = i+di[d], nj = j+dj[d];
            if(ni<0 || ni>=n || nj<0 || nj>=n) continue;
            if(map[i][j] < map[ni][nj]) {
                len++; dfs(ni, nj);
            }
        }
    }
}
/*

내 위치에서 나보다 더 큰 값으로 이동할 수 있나?
    있다면, 나보다 큰 값에서는 다른 곳으로 이동할 수 있다고 했을 때의 최대 경로 길이를 누적하면 된다.

모든 칸은 일단 못 움직인다고 생각하고
맨 윗줄에 대해서 dfs를 했을 때 각 칸에서 갈 수 있는 최대 거리가 나오겠지?
그 아랫 줄 각 칸을 상하좌우로 살펴보면
    나보다 큰 칸 중에서 다른 곳으로 갈 수 있는 칸이 있으면 그 중에서 최댓값인 칸의 값을 가져와서 +1 (내가 그 경로에 속할 수 있다는 뜻; 왜냐면 그 칸의 값은, 그 칸보다 큰 값을 찾아 떠났을 때의 최대 길이)

0 2 0 1
1 1 2 3
1 0

 */
