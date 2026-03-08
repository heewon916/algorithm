import java.io.*;
import java.util.*;

public class Main {
    static int len;
    static int[][] map = new int[100][100];
    // 0: 상 -> 1: 우 -> 2: 하 -> 3: 좌
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        len = Integer.parseInt(br.readLine());
        String str = br.readLine();

        for(int i=0; i<100; i++) Arrays.fill(map[i], -1);

        int dir = 2;
        int r = 49, c = 49;
        map[r][c] = 0;

        int minR = 49, maxR = 49, minC = 49, maxC = 49;
        for(int i=0; i<len; i++){
            char input = str.charAt(i);
            if(input == 'R') {
                dir = (dir + 1) % 4;
            }
            else if (input == 'L') {
                dir = (dir-1 + 4) % 4;
            }
            else {
                r = r + di[dir];
                c = c + dj[dir];
                map[r][c] = 0;

                minR = Math.min(minR, r);
                maxR = Math.max(maxR, r);
                minC = Math.min(minC, c);
                maxC = Math.max(maxC, c);
            }
        }

        for(int i=minR; i<=maxR; i++){
            for(int j=minC; j<=maxC; j++){
                if(map[i][j] == 0) sb.append(".");
                else sb.append("#");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
/*
상 -> 우 -> 하 -> 좌

방문한 위치들만 . 표시하면 될 듯 ?
 */