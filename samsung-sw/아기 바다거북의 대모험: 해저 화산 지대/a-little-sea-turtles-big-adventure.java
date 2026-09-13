import java.io.*;
import java.util.*; 

public class Main {
    static class T {  // 거북이 
        int id; 
        int i; 
        int j; 
        boolean isRock = false; 
        boolean isArrvd = false; //도착여부 

        T(int id, int i,  int j){
            this.id = id; 
            this.i = i; 
            this.j = j; 
        }
    }
    static class V { 
        int id; 
        int i;
        int j; 
        int P; // 분출시작하는 값
        boolean isErupt = false; // 분출여부 
        V(int id, int i, int j, int P){
            this.id = id;
            this.i = i; 
            this.j = j; 
            this.P = P; 
        }
    }
    static int n, m, k; 
    static int[][] map; 
    static List<T> turtle = new ArrayList<>(); 
    static List<V> volcano = new ArrayList<>();
    static final int[] di = {0, 1, 0, -1};
    static final int[] dj = {1, 0, -1, 0};
    static int[][] pres; // 압력 
    static int[][] heat; // 열기 
    static int[] answer; 

    static void setup() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       map = new int[n][n]; 
       pres = new int[n][n]; 
       heat = new int[n][n];
       answer = new int[m]; // 안식처에 도착한 턴 번호 
       Arrays.fill(answer, -1);

       for(int i=0; i<n; i++){
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<n; j++){
            map[i][j] = Integer.parseInt(st.nextToken());
        }
       }
       // TODO 거북이, 화산 위치 저장 
       for(int i=0; i<m; i++){
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        turtle.add(new T(i, r, c));
        map[r][c] = 2; 
       }

       for(int i=0; i<k; i++){
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        volcano.add(new V(i, r, c, p));
       }
    }
    static int[] getNext(int i, int j){
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        q.add(new int[]{n-1, n-1});
        int[][] dist = new int[n][n];
        for(int r=0; r<n; r++) Arrays.fill(dist[r], -1);
        dist[n-1][n-1] = 0; 
        boolean[][] v = new boolean[n][n]; 
        v[n-1][n-1] = true; 

        while(!q.isEmpty()){
            int[] c = q.poll(); 
            int ci = c[0], cj = c[1];

            for(int d=0; d<4; d++){
                int ni = ci + di[d]; 
                int nj = cj + dj[d]; 

                if(ni<0 || ni>=n || nj<0 || nj>=n) continue; 
                if((map[ni][nj] != 0 && !(ni == i && nj == j))|| v[ni][nj]) continue; 

                q.add(new int[]{ni, nj}); 
                v[ni][nj] = true; 
                dist[ni][nj] = dist[ci][cj]+1; 
            }
        }
        for(int d=0; d<4; d++){
            int ni = i+di[d]; 
            int nj = j+dj[d];
            if(ni<0 || ni>=n || nj<0 || nj>=n) continue; 
            if(map[ni][nj] == 1) continue; 
            if(dist[ni][nj] == dist[i][j]-1) return new int[]{ni, nj};
        }
        return new int[]{-1, -1};
    }
    static void moveTurtle(int turn){
        for(T t: turtle){
            if(t.isArrvd || t.isRock) {
                // 도착했거나 이미 화석화된 친구는 움직일 필요 없음 
                continue;
            }
            int[] next = getNext(t.i, t.j); 
            int ni = next[0], nj = next[1]; 

            if(ni == -1 && nj == -1){
                // stay 
                continue; 
            }
            if(ni == n-1 && nj == n-1){
                // 안식처 도착 
                answer[t.id] = turn; // 끝난 턴 입력
                t.isArrvd = true;
                map[t.i][t.j] = 0; 
                continue; 
            }

            // 이동 
            map[t.i][t.j] = 0;  
            t.i = ni; 
            t.j = nj; 
            map[t.i][t.j] = 2; 
        }
    }
    // 화산 압력 증가 
    static void increasePress(){
        for(V v: volcano){
            pres[v.i][v.j] += 10; 
        }
    } 
    static boolean continueExplode(List<Integer> getExplodeV){
        boolean hasExploded = false; 
        for(V v: volcano){
            // 열기 전파 
            if(v.isErupt) continue; 
            if(!v.isErupt && pres[v.i][v.j] + heat[v.i][v.j] >= v.P) {
                v.isErupt = true; 
                getExplodeV.add(v.id);
                // System.out.println("++ 연쇄반응으로 분출하는 화산 위치: " + v.i + ", " + v.j);
                spreadHeat(v);
                hasExploded = true; 
            }
        }
        return hasExploded;    
    }
    static void spreadHeat(V v){
        // 산호초, 열기 = 0 이 될때까지 상하좌우로 전파됨 
        int i = v.i, j = v.j; 
        int curHeat = v.P;
        heat[v.i][v.j] += curHeat; 
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        for(int d=0; d<4; d++){
            q.add(new int[]{i, j, curHeat, d});
        }
        while(!q.isEmpty()){
            int[] c = q.poll(); 
            int ci = c[0], cj = c[1], ht = c[2], dir = c[3]; 
            int ni = ci + di[dir];
            int nj = cj + dj[dir]; 
            int nHeat = ht/2; 
            if(ni<0 || ni>=n || nj<0 || nj>=n) continue; 
            if(map[ni][nj] == 1 || nHeat == 0) continue; 
            heat[ni][nj] += nHeat;
            q.add(new int[]{ni, nj, nHeat, dir});
        }
    }
    // 거북이 화석화
    static void changeToRock(){
        for(T t: turtle){
            if(heat[t.i][t.j] >= 20) {
                t.isRock = true; 
                map[t.i][t.j] = 2; 
            }
        }
    }
    static void reset(List<Integer> getExplodeV){
        for(int i=0; i<n; i++){
            Arrays.fill(heat[i], 0);
        }
        for(int i: getExplodeV){
            V v = volcano.get(i); 
            pres[v.i][v.j] = 0; 
            v.isErupt = false; 
        }
    }
    public static void main(String[] args) throws Exception{
       // 조건
       // nxn / m마리 / 목적지 n-1, n-1/ 전체 100회 
       setup(); 

       int turn = 0; 
       List<Integer> getExplodeV;
       while(turn < 100){
        turn++; 
        // System.out.println("---- turn: " + turn + " ----");
        getExplodeV = new ArrayList<>(); 
        moveTurtle(turn); 
        // printTurtles();

        increasePress(); 
        // printPressure();

        // 분출되는 화산 -> 열기가 전파됨 
        for(V v: volcano){
            if(pres[v.i][v.j] >= v.P && !v.isErupt) {
                // System.out.println("++ Erupt: " + v.i + ", " + v.j);
                v.isErupt = true; 
                getExplodeV.add(v.id);
                spreadHeat(v);
            }
        }
        // 연쇄반응 -- 위의 과정으로 인해서 열기가 전파됨. 
        // 새로 분출하는 화산이 없을 때까지 이 연쇄 분출 과정이 반복됨 
        while(continueExplode(getExplodeV)){
            // continue; 
        }
        // printHeat();
        
        changeToRock(); 
        // printTurtles();

        reset(getExplodeV); 
       }

        StringBuffer sb = new StringBuffer(); 
        for(int i: answer){
            sb.append(i).append("\n");
        }
        System.out.println(sb);

    }
    static void printPressure(){
        System.out.println("++ printPressure: ");
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(pres[i]));
        }
    }

    static void printTurtles(){
        System.out.println("++ printTurtles: ");
        for (T t : turtle) {
            System.out.println(
                "id=" + t.id
                + " pos=(" + t.i + "," + t.j + ")"
                + " rock=" + t.isRock
                + " arrived=" + t.isArrvd
            );
        }
    }
    static void printHeat() {
        System.out.println("++ printHeat: ");
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(heat[i]));
        }
    }
}