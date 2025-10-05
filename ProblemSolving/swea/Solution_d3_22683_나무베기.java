import java.io.*;
import java.util.*;

public class Solution_d3_22683_나무베기 {
    static class State {
        int y, x, k, d, cost;

        public State(int y, int x, int k, int d, int cost) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.d = d;
            this.cost = cost;
        }
    }

    static char[][] map;
    static int sy, sx, dy, dx;
    static boolean[][][][] v;
    static int K, N;

    static void input(BufferedReader br, StringTokenizer st) throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        sy = -1;
        sx = -1; // 출발지
        dy = -1;
        dx = -1; // 도착지
        for (int i = 0; i < N; i++) {
            String in = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = in.charAt(j);
                if (map[i][j] == 'X') {
                    sy = i;
                    sx = j;
                }
                if (map[i][j] == 'Y') {
                    dy = i;
                    dx = j;
                }
            }
        }
    }

    static int[] diffy = { -1, 0, 1, 0 };
    static int[] diffx = { 0, 1, 0, -1 }; // 위0 우1 하2 좌3

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            input(br, st);
            int ans = -1;
            v = new boolean[N][N][K + 1][4]; // y,x: 방향 d에서 나무 k번 벤 경우 방문처리
            ArrayDeque<State> q = new ArrayDeque<>();
            q.offer(new State(sy, sx, K, 0, 0));
            v[sy][sx][0][0] = true;
            while (!q.isEmpty()) {
                State cur = q.poll();
                if (map[cur.y][cur.x] == 'Y') {
                    ans = cur.cost;
                    break;
                }

                // 전진 할 때
                int ny = cur.y + diffy[cur.d];
                int nx = cur.x + diffx[cur.d]; // 방향 유지
                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (map[ny][nx] == 'T') { // 나무: 전진하려면 베어야 함
                        if (cur.k > 0 && !v[ny][nx][cur.k - 1][cur.d]) {
                            q.offer(new State(ny, nx, cur.k - 1, cur.d, cur.cost + 1));
                            v[ny][nx][cur.k - 1][cur.d] = true;
                        }
                    } else { // 나무가 아닌 경우: 그냥 전진
                        q.offer(new State(ny, nx, cur.k, cur.d, cur.cost + 1));
                        v[ny][nx][cur.k][cur.d] = true;
                    }
                }

                // 우회전 할 때
                int nd = (cur.d + 1) % 4;
                if (!v[cur.y][cur.x][cur.k][nd]) {
                    q.offer(new State(cur.y, cur.x, cur.k, nd, cur.cost + 1));
                    v[cur.y][cur.x][cur.k][nd] = true;
                }
                // 좌회전 할 때
                nd = (cur.d + 3) % 4;
                if (!v[cur.y][cur.x][cur.k][nd]) {
                    q.offer(new State(cur.y, cur.x, cur.k, nd, cur.cost + 1));
                    v[cur.y][cur.x][cur.k][nd] = true;
                }
            }

            sb.append("#").append(tc).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
}

/*
 * [문제 이해하기]
 * NxN 배열
 * 조작 시작: 위쪽 보는 상태
 * 조작: 전진, 왼쪽으로 90도 회전,
 * 1초
 * 
 * 한 좌표에서 할 수 있는 선택
 * 1. 전진하기
 * - 전진하려는 자리 ny, nx에 나무가 있으면 베야되고, 없으면 베지 않아도 된다.
 * -
 * 2. 왼쪽으로 90도 회전하기
 * 3. 오른쪽으로 90도 회전하기
 * 
 * [설계하기]
 * 
 * 
 */