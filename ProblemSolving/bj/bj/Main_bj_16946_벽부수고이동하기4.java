package bj;
import java.io.*;
import java.util.*;

/**
 * 문제를 덩어리 덩어리로 나눠서 풀어보는 연습하기
 * 0의 개수가 결국 답이 된다면
 * 0끼리 묶어서 그래프를 만들고
 * 그 그래프에 포함된 칸의 개수를 따로 연산해두기
 *
 * 시뮬, 구현 연습이 될 것 같다
 */
public class Main_bj_16946_벽부수고이동하기4 {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ArrayList<int[]> blocks = new ArrayList<>();
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = input.charAt(j) -'0';
                if(map[i][j] == 1) blocks.add(new int[]{i, j});
            }
        }
        // 1. 0인 애들끼리 그래프를 묶어서 id, size로 묶기
        compId = new int[N][M];
        compSize = new HashMap<>();
        boolean[][] v = new boolean[N][M];
        int id = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!v[i][j] && map[i][j] == 0){
                    int size = bfs(i, j, v, id);
                    compSize.put(id++, size);
                }
            }
        }
        long[][] ans = new long[N][M];
        for(int[] pos: blocks){
            int i = pos[0], j = pos[1];
            Set<Integer> uniqueIds = new HashSet<>();
            for(int d=0; d<4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(map[ni][nj] == 0) uniqueIds.add(compId[ni][nj]);
            }
            long sum = 1;
            for(int uid: uniqueIds){
                sum += compSize.get(uid);
            }
            ans[i][j] = sum % 10;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
    static Map<Integer, Integer> compSize;
    static int[][] compId;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int answer = 0;
    static void dfs(int i, int j, boolean[][] v){
        // System.out.println("===> " + i + ", " + j + " answer = " + answer);
        for(int d=0; d<4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
            if(!v[ni][nj] && map[ni][nj] == 0) {
               v[ni][nj] = true;
                answer++; answer %=10;
                dfs(ni, nj, v);
            }
        }
    }
    static int bfs(int i, int j, boolean[][] v, int id){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        compId[i][j] = id;
        v[i][j] = true;
        int size = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
                if(!v[ni][nj] && map[ni][nj] == 0) {
                    v[ni][nj] = true;
                    q.offer(new int[]{ni, nj});
                    compId[ni][nj] = id;
                    size++;
                }
            }
        }
        return size;
    }
}
/*
벽을 부수고
내 위치에서 갈 수 있는 곳 dfs로 보면 될 것 같은데
아니면 각 지점마다 bfs가 됨
N * M이 된다
 */