package bj;
import java.io.*;
import java.util.*;

public class Main_bj_2636 {
    static int n,m;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int time = 0;
    static int lastLeft = 0;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static ArrayList<int[]> tobezero = new ArrayList<>();
    static boolean[][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[n][m]; // 매번 초기화 필요 없음
        while (true) {
            q.add(new int[]{0, 0});
            v[0][0] = true;

            while (!q.isEmpty()) {
                int[] pos = q.poll();
                for (int d = 0; d < 4; d++) {
                    int ni = pos[0] + di[d], nj = pos[1] + dj[d];
                    if (ni < 0 || ni >= n || nj < 0 || nj >= m || v[ni][nj]) continue;

                    v[ni][nj] = true;
                    if (map[ni][nj] == 0) {
                        q.add(new int[]{ni, nj});
                    } else {
                        tobezero.add(new int[]{ni, nj});
                    }
                }
            }

            if (tobezero.isEmpty()) break;

            lastLeft = tobezero.size(); // 녹기 직전 치즈 개수 저장
            time++;

            q.clear();
            for (int[] p : tobezero) {
                map[p[0]][p[1]] = 0;
                q.add(p);
            }
            tobezero.clear();


        }
        sb.append(time).append("\n").append(lastLeft);
        System.out.println(sb);

    }

}
/*
N M 100

내가 놓친 거: 0,0은 항상 공기다..

그렇게 따지면 0,0부터 그냥 bfs 쭉 돌려서 인접한 1인 칸은 전부 0으로 바꾼다.
- 단, 즉시 바꾸지 않고 리스트에 저장해둔 다음에 애들을 바꿔야 한다.
- 0으로 바뀐 좌표들이 겉 테두리였을 테니, 이들을 저장해둔다.

저장했던 0인 좌표 리스트들에 대해서 다시 bfs를 돌려 인접한 1인 칸도 전부 0으로 바꾼다.
- 쓰고 나면 clear하고 다시 조금 전에 0으로 바뀐 좌표들만 리스트에 저장해둔다.
 */