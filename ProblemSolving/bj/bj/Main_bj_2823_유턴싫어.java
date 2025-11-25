package bj;

import java.io.*;
import java.util.*;

public class Main_bj_2823_유턴싫어 {
    static int R, C;
    static char[][] map;
    static ArrayList<int[]> l;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    // 위 0 아래 1 왼쪽 2 오른쪽 3
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        l = new ArrayList<>(); 
        map = new char[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == '.') l.add(new int[]{i, j});
            }
        }

        /***
         * 어떤 길에서 출발해서 다시 자기 자리로 오는데 유턴하면 안되는거지
         */
        for(int[] pos: l){
            int r = pos[0]; 
            int c = pos[1];
            
            int blckCnt = 0; 
            int total = 0; 
            for(int d=0; d<4; d++) {
            	int nr = r + dr[d]; 
            	int nc = c + dc[d]; 
            	if(nr<0 || nr>=R || nc<0 || nc>=C) continue; 
            	total++; 
            	if(map[nr][nc] == 'X') blckCnt++; 
            }
            if(total-blckCnt <= 1) {
            	System.out.println(1);
            	return; 
            }
        }
        System.out.println(0);

    }

}
