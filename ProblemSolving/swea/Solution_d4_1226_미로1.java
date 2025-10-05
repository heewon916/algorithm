
import java.io.*; 
import java.util.*; 

public class Solution_d4_1226_미로1 {
    static int ans; 
    static boolean[][] v;
    static int[][] map; 
    static int[] diffy = {-1, 1, 0, 0};
    static int[] diffx = {0, 0, -1, 1}; 
    static void dfs(int y, int x){
        v[y][x] = true; 
        for(int d=0; d<4; d++){
            int ny = y+diffy[d];
            int nx = x+diffx[d]; 
            if(ny<0 || ny>=16 || nx<0 || nx>=16) continue;
            if(map[ny][nx] == 3) {
                System.out.println("성공!");
                ans = 1; 
                return; 
            }
            if(map[ny][nx] == 0 && !v[ny][nx]) dfs(ny, nx);
        }
    }
    static void bfs(int y, int x){
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        v[y][x] = true; 
        q.add(new int[]{y, x});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int d=0; d<4; d++){
                int ny = cur[0]+diffy[d];
                int nx = cur[1]+diffx[d]; 
                if(ny<0 || ny>=16 || nx<0 || nx>=16) continue;
                if(map[ny][nx] == 3){
                    ans = 1; 
                    break; 
                }   
                if(map[ny][nx] == 0 && !v[ny][nx]){
                    v[ny][nx] = true; 
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        map = new int[16][16]; 

        for(int tc=1; tc<=10; tc++){
            String in = br.readLine(); 

            ans = 0; 

            int sy = -1, sx = -1; 
            // int dy = -1, dx = -1;
            
            v = new boolean[16][16]; 
            for(int i=0; i<16; i++){
                String input = br.readLine();
                for(int j=0; j<16; j++){
                    map[i][j] = input.charAt(j) - '0'; 
                    if(map[i][j] == 2) {
                        sy=i; sx=j;
                    }
                    // if(map[i][j] == 3) {
                    //     dy=i; dx=j;    
                    // }
                }
            }
            // dfs(sy, sx);
            bfs(sy, sx);
            sb.append('#').append(tc).append(' ').append(ans).append('\n'); 
        }
        System.out.println(sb);
    }
}
