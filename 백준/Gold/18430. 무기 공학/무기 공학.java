import java.io.*;
import java.util.*;
public class Main {
    static int n, m;
    static int[][] map;
    static int[][] shp1 = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}};
    static int[][] shp2 = {{0, 1}, {0, -1}, {0, 1}, {0, -1}};
    static int answer = 0;
    // 0: 상/ 1: 하/ 2: 좌/ 3:우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(n <= 1 || m <= 1) {
            System.out.println(0);
            return;
        }

        boolean[][] v = new boolean[n][m];
        dfs(0, 0, v, 0);
        System.out.println(answer);
//        for(int i=1; i<n; i++) {
//            for (int j = 1; j < m; j++) {
//                v[i][j] = true; // 거기를 중심점으로 한다고 했을 떄를 봐보자고
//                dfs(i, j, v, map[i][j] * 2);
//                v[i][j] = false;
//            }
//        }
    }
    static void dfs(int i, int j, boolean[][] v, int total){
        if(j == m){
            dfs(i+1, 0, v, total);
            return;
        }
        if(i == n){
            answer = Math.max(answer, total);
            return;
        }
        dfs(i, j+1, v, total);

        if(v[i][j]) return; 
        for(int k=0; k<4; k++){
            int ni1 = i + shp1[k][0];
            int nj1 = j + shp1[k][1];
            int ni2 = i + shp2[k][0];
            int nj2 = j + shp2[k][1];

            if(ni1<0 || ni1>=n || nj1<0 || nj1>=m) continue;
            if(ni2<0 || ni2>=n || nj2<0 || nj2>=m) continue;
            if(v[ni1][nj1] || v[ni2][nj2]) continue; // 하나라도 방문되었으면 그거는 고려 불가능

            v[i][j] = v[ni1][nj1] = v[ni2][nj2] = true;
            int score = map[i][j] * 2 + map[ni1][nj1] + map[ni2][nj2];

            // 이 상태에서 다음 상태를 어떻게 고려 해야 하지..
            // 일단 다음 칸으로 이동
            dfs(i, j+1, v, total + score);
            v[i][j] = v[ni1][nj1] = v[ni2][nj2] = false;
        }
    }
}
/*
nxm 배열
만들 수 있는 합의 최댓값

나무 재료의 크기가 작아서 암것도 못 만들면 0

좌-하 1-2
좌-상 0-2
우-하 1-3
우-상 0-3

한 지점에 대해 4가지 조합이 나올 수 있음
- 그 조합 중에서 하나라도 외부로 삐져 나가면 continue

모든 좌표에 대해서 4가지 방향 무조건 탐색하면 시간 out

만들어질 수 있는 좌표 조합을 set에 넣으면 그것도 타임아웃아닌가?

 */