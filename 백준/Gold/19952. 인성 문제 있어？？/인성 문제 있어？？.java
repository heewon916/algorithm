import java.util.*;
import java.io.*;
public class Main {
    static int R, C, O, F, Sx, Sy, Dx, Dy;
    static int[][] map;
    static int[] dr = {-1, 1 ,0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken()); F = Integer.parseInt(st.nextToken());
            Sx = Integer.parseInt(st.nextToken())-1; Sy = Integer.parseInt(st.nextToken())-1;
            Dx = Integer.parseInt(st.nextToken())-1; Dy = Integer.parseInt(st.nextToken())-1;

            map = new int[R][C];
            for(int i=0; i<O; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken())-1;
                int h = Integer.parseInt(st.nextToken());
                map[r][c] = h;
            }
            /**
             * 여기서 비용 = 힘
             */
            int[][] dist = new int[R][C]; // 드는 비용
            for(int i=0; i<R; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[Sx][Sy] = 0;
            // pq <- {r, c, dist[r][c]};
            // 최소한의 힘으로 가야 하니까 0부터 시작해서 -1을 +1로 계산하면 된다
            // boolean[][] v = new boolean[R][C];
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
            pq.add(new int[]{Sx, Sy, dist[Sx][Sy]});

            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int minR= cur[0];
                int minC = cur[1];
                int cost = cur[2];

//                if(v[minR][minC]) continue;
//                v[minR][minC] = true;
                if(cost > dist[minR][minC]) continue;

                if(minR == Dx && minC == Dy) {
                    break;
                }

                for(int d=0; d<4; d++){
                    int nr = minR + dr[d];
                    int nc = minC + dc[d];
                    if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
//                    if(v[nr][nc]) continue;

                    int currH = map[minR][minC];
                    int nextH = map[nr][nc];
                    int remaining = F - cost; // 남은 힘에 대해서
                    int dH = nextH - currH;
                    if(dH > 0 && remaining < dH) continue; // 오르막인데 남은 힘보다 높으면 못 감 

                    int newCost = cost + 1; 
                    if(newCost > F) continue;

                    if(dist[nr][nc] > newCost){
                        dist[nr][nc] = newCost;
                        pq.offer(new int[]{nr, nc, newCost});
                    }

                }
            }
            if(F >= dist[Dx][Dy]){
                System.out.println("잘했어!!");
            }else System.out.println("인성 문제있어??");
        }
    }
}
/*
이동 상하좌우 가능
힘 >= 0 이동 가능

이동할 때 따져야 하는 조건
1. 내 위치 >= 이동할 위치 => 힘-1
2. 내 위치 < 이동할 위치
- 남이있는 힘 >= 위치 차 => 힘-1

 */