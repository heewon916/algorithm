package bj;
import java.util.*;
import java.io.*;

public class Main_bj_1360 {
    static char[][] map;
    static int n, m;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        int[] red = new int[2];
        int[] blue = new int[2];
        int[] hole = new int[2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'R') {
                    red[0] = i; red[1] = j;
                }else if(map[i][j] == 'B'){
                    blue[0] = i; blue[1] = j;
                }else if(map[i][j] == 'O'){
                    hole[0] = i; hole[1] = j;
                }
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        q.add(new int[] {red[0], red[1], blue[0], blue[1], 0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ri = cur[0], rj = cur[1], bi = cur[2], bj = cur[3], depth = cur[4];
            boolean able = false;
            for(int d=0; d<4; d++){
                int nri = ri + di[d], nrj = rj+dj[d], nbi = bi+di[d], nbj = bj+dj[d];
                while(0<=nri && nri<=n && 0<=nrj && nrj<=m){
                    v[nri][nrj] = true;
                    nri += di[d]; nrj +=dj[d];
                    if(nri == hole[0] && nrj == hole[1]) { // 빨간 구슬이 빠지면 그건 답임
                        System.out.println(depth+1);
                        return;
                    }
                    if(map[nri][nrj] == '#'){
                        nri -= di[d]; nrj -= dj[d];
                        break;
                    }
                }
                while(0<=nbi && nbi<=n && 0<=nbj && nbj<=m){
                    v[nbi][nbj] = true;
                    nbi += di[d]; nbj += dj[d];
                    if(nbi == hole[0] && nbj == hole[1]){ // 파란 구슬이 빠지면 답 없음
                        System.out.println(-1);
                        return;
                    }
                    if(map[nbi][nbj] == '#'){
                        nbi -= di[d]; nbj -= dj[d];
                        break;
                    }
                }
                // 파란 구슬, 빨간 구슬 둘 다 안 빠지면
                // 같은 위치에 가있으면 더 많이 이동한 애 뒤로 한 칸

                // 서로 다른 위치에 가있으면 이제 큐에 추가
                q.add(new int[]{nri, nrj, nbi, nbj, depth+1});
                v[nri][nrj] = true;
                v[nbi][nbj] = true;


            }
        }


    }
}
