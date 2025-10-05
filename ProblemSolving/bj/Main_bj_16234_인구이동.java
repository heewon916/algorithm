import java.io.*;
import java.util.*;

public class Main_bj_16234_인구이동 {
    static int N, L, R;
    static int[][] map;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };

    static boolean bfs(int i, int j, boolean[][] v) {
        boolean changed = false;
        int cnt = 0;
        int sum = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> result = new ArrayList<>();
        q.add(new int[] { i, j });
        result.add(new int[] { i, j });
        v[i][j] = true;
        sum += map[i][j];
        cnt++;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;
                int diff = Math.abs(map[r][c] - map[nr][nc]);
                if (!v[nr][nc] && diff <= R && diff >= L) {
                    q.add(new int[] { nr, nc });
                    v[nr][nc] = true;
                    result.add(new int[] { nr, nc });
                    changed = true;
                    sum += map[nr][nc];
                    cnt += 1;
                }
            }
        }

        if (cnt > 1) {
            int avg = (int) (sum / cnt);
            for (int[] cur : result) {
                map[cur[0]][cur[1]] = avg;
            }
        }
        // for (int r = 0; r < N; r++) {
        // for (int c = 0; c < N; c++) {
        // System.out.print(map[r][c] + " ");
        // }
        // System.out.println();
        // }
        return changed;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int days = 0;
        boolean change = false;
        while (true) {
            change = false;
            boolean[][] v = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j]) {
                        change = bfs(i, j, v) || change;
                        // System.out.println("@(%d, %d) changed: %s".formatted(i, j, change));
                    }
                }
            }
            if (!change)
                break;
            days++;
        }
        System.out.println(days);

    }
}
