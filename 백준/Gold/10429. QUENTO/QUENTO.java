import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static String[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] v;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[3][3];
        for(int i=0; i<3; i++){
            String[] input = br.readLine().split("");
            map[i][0] = input[0];
            map[i][1] = input[1];
            map[i][2] = input[2];
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(map[i][j].equals("+") || map[i][j].equals("-")) continue;
                v = new boolean[3][3];
                v[i][j] = true;
                list = new ArrayList<>();
                list.add(new int[]{i, j});
                boolean able = dfs(v, i, j, list);
                if(able) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(1).append("\n");
                    for(int[] pos: list){
                        sb.append(pos[0]).append(" ").append(pos[1]).append("\n");
                    }
                    System.out.println(sb);
                    return;
                }
            }
        }
        System.out.println(0);

    }

    static boolean dfs(boolean[][] v, int r, int c, ArrayList<int[]> list){
        if(list.size() == 2*M-1){
            int[] pos = list.get(0);
            int total = Integer.parseInt(map[pos[0]][pos[1]]);
            for(int i=1; i< list.size(); i+=2){
                int[] p = list.get(i);
                String op = map[p[0]][p[1]];
                int[] p1 = list.get(i+1);
                int nextNum = Integer.parseInt(map[p1[0]][p1[1]]);
                if(op.equals("+")) total += nextNum;
                else total -= nextNum;
            }
            return total == N;
        }
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0 || nr>=3 || nc<0 || nc>=3 || v[nr][nc]) continue;

            v[nr][nc] = true;
            list.add(new int[]{nr, nc});

            if(dfs(v, nr, nc, list)) return true; // [!] 중요

            v[nr][nc] = false;
            list.remove(list.size()-1);
        }
        return false;
    }
}
/**
 * DFS는 트리 구조로 탐색합니다.
 * 깊은 곳(Leaf node)에서 정답(total == N)을 찾았을 때,
 * 그 기쁜 소식을 부모 노드들에게 return true로 계속 전달해줘야
 * 메인 루프까지 무사히 도달할 수 있기 때문입니다.
 */