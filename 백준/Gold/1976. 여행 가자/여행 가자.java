import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine()); N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()); M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] target = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++){
            target[i] = Integer.parseInt(st.nextToken());
        }
        int[] label = new int[N+1];
        bfs(1, label);

        boolean ans = true;
        int gNo = label[target[0]];
        for(int i=1; i<M; i++){
            int no = label[target[i]];
            if(gNo != no){
                ans = false; break;
            }
        }

        if(ans) System.out.println("YES");
        else System.out.println("NO");
    }
    static void bfs(int n, int[] label){
        boolean[] v = new boolean[N+1];
        v[n] = true;
        label[n] = 1;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i=1; i<=N; i++){
                if(!v[i] && map[curr][i] == 1){
                    q.add(i);
                    v[i] = true;
                    label[i] = 1;
                }
            }
        }
    }

}
/*
N 도시 개수
M 여행 계획에 속한 도시 개수

인접행렬로 주어짐
도시 번호 i에 대해서
    다른 도시 j에 갈 수 있으면 1로 모두 표시해두면 될 것 같음
for i 1...N
    for j i+1..N
        cango(i,j) -> map[i][j] = map[j][i] = 1
        1/2 * n^3 = 8 * 10^6 * 1/2
cango(i, j)
    bfs start = i
    q <- adj : map[i][adj] == 1; v[adj] = true

---

같은 그룹에 있는가만으로도 충분히 풀리는 문제
 */