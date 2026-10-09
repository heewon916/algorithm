import java.util.*;
import java.io.*; 

public class Main {
    static int n, k, l; 
    static int[][] map; 
    static class Robot { 
        int r; 
        int c; 
        Robot(int r, int c){
            this.r = r; 
            this.c = c; 
        }
    }
    static List<Robot> robots; 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    /*
    4방향
    0 상 -1, 0
    1 하 1, 0
    2 좌 0, -1
    3 우 0, 1
    */
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; 

    static void setup() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        n = Integer.parseInt(st.nextToken()); 
        k = Integer.parseInt(st.nextToken()); 
        l = Integer.parseInt(st.nextToken()); 

        map = new int[n][n]; 
        robots = new ArrayList();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine()); 
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine()); 
            int r = Integer.parseInt(st.nextToken())-1; 
            int c = Integer.parseInt(st.nextToken())-1;
            robots.add(new Robot(r,c));
        }
    }
    /*
    1. 이동
    */
    static void moveAllRobot() { 
        /*
        각 로봇 청소기 순서대로
            이동거리가 가장 가까운 && 오염된 격자로 이동한다. 
                물건, 청소기면 못 지나감
                여러개면 여러개인대로 일단 저장
                -> 현재 위치 기준으로 bfs 진행 
            그 중에서 가장 행 오름차순, 열 오름차순 했을 때 맨 앞에 있는 곳으로 이동 
        */
        for(Robot r: robots){
            /* !!! 현재 칸부터 먼지가 있으면 이동하면 안돼~~ */
            if (map[r.r][r.c] > 0) continue;
            // 현재 위치 기준으로 찾는다. 
            List<int[]> moveAbleList = findAllMoveable(r); 
            if (moveAbleList.isEmpty()) continue;

            // 가장 가까운 거리, 가장 작은 행 번호, 가장 작은 열 번호로 이동한다. 
            moveAbleList.sort((a,b) -> {
                if(a[2] != b[2]) return a[2] - b[2]; 
                if(a[0] != b[0]) return a[0] - b[0]; 
                return a[1]- b[1]; 
            }); 
            // 이동한다. -- 로봇 위치 갱신 잘해야됨
            r.r = moveAbleList.get(0)[0]; 
            r.c = moveAbleList.get(0)[1]; 
        }
    }
    /* 
    1-1. 이동할 곳 찾음 
    - 이동거리가 가장 가까운
    - 오염된 격자 
    - 못 지나가는 조건: 물건 위치/ 청소기가 있음 
    bfs 
    */ 
    static List<int[]> findAllMoveable(Robot r){
        List<int[]> result = new ArrayList<>(); 
        Queue<int[]> q = new ArrayDeque<>(); 
        boolean[][] v = new boolean[n][n]; 
        /* r, c, dist */ 
        q.add(new int[]{r.r, r.c, 0}); 
        v[r.r][r.c] = true; 

        while(!q.isEmpty()){
            int[] curr = q.poll(); 
            int currDist = curr[2]; 
            int i = curr[0]; 
            int j = curr[1]; 

            for(int d=0; d<4; d++){
                int ni = i+dr[d]; 
                int nj = j+dc[d]; 

                if(ni<0 || ni>=n || nj<0 || nj>=n) continue; 
                /* 방문했거나, 물건이 위치하거나, 청소기가 있으면 못 지나감 */ 
                if(v[ni][nj] || map[ni][nj] == -1 || isRobotExists(ni, nj)) continue; 

                if(map[ni][nj] > 0){
                    /* 오염된 위치만 리턴 */ 
                    result.add(new int[]{ni, nj, currDist+1}); 
                }
                
                q.add(new int[]{ni, nj, currDist+1});
                v[ni][nj] = true; 
            }
        }
        return result; 
    }
    /* 
    1-2. 청소기가 있는지 확인 
    */ 
    static boolean isRobotExists(int i, int j){
        for(Robot r: robots){
            if(r.r == i && r.c == j) return true; 
        }
        return false; 
    }
    /*
    2. 청소 
    */ 
    static void cleanByEachRobot(Robot r){
        /*
        각 4방향에 대해서 오른-아래-왼-위 순서대로 조회하면서 먼지량 누적 
            만약 20 넘기면 20으로 처리
            dust[] idx: 먼지량 
            오른쪽이면 0 0,0/ -1,0 (0) / 1,0 (1) / 0,1 (3) 
            아래쪽이면 1 0,0/ 0,1 (3) / 1,0 (1) / 0,-1 (2)
            왼쪽이면 2 0,0/ 0,-1 (2) / -1, 0 (0)/ 1, 0 (1)
            위쪽이면 3 0,0/ -1, 0 (0)/ 0, -1 (2)/ 0, 1(3)
        각 순서대로 조회한 것 중에서 가장 큰 먼지량인 방향을 선택
            => list 로 관리하지 말고, 매번 업뎃으로 구현
            => 오른쪽부터 도니까, 오른쪽먼지량보다 크면 업뎃. 아니면 유지하면 될 듯? 
        그 방향에서 방문하는 4개의 격자 
            20 이하로 먼지 처리 
        */
        // 오 - 아 - 왼 - 위 순
        int[] dust = new int[4]; 
        // -1: 자기자신 
        int[][] dirMap = {
            {-1, 0, 1, 3}, 
            {-1, 3, 1, 2},
            {-1, 2, 0, 1}, 
            {-1, 0, 2, 3}
        };

        // 청소할 수 있는 곳이 없을 수도 있다. -- 그러니 dir 초기값은 -1 ㄴㄴ
        int dustMax = 0; 
        int dir = 0;
        for(int i=0; i<4; i++){
            int total = map[r.r][r.c];
            for(int d=1; d<4; d++){
                int nr = r.r + dr[dirMap[i][d]];
                int nc = r.c + dc[dirMap[i][d]];
                /* 범위 벗어났거나, 물건이 있거나 - 청소 불가 */ 
                if(nr<0 || nr>=n || nc<0 || nc>=n) continue; 
                if(map[nr][nc] == -1 /*|| isRobotExists(nr, nc)*/) continue; 
                
                /* 격자마다 청소할 수 있는 최대 먼지량 = 20 */ 
                if(map[nr][nc] > 20){
                    total += 20; 
                }else {
                    total += map[nr][nc]; 
                }
            }
            dust[i] = total; 
            if(dust[i] > dustMax){
                dustMax = dust[i]; 
                dir = i; 
            }
        }

        /* map에 청소 반영 */
        map[r.r][r.c] = (map[r.r][r.c] >= 20 ? map[r.r][r.c] - 20: 0); 
        for(int d=1; d<4; d++){
            int nr = r.r + dr[dirMap[dir][d]];
            int nc = r.c + dc[dirMap[dir][d]];

            /* 범위 벗어났거나, 물건이 있거나, 청소기가 있으면 - 청소 불가 */ 
            if(nr<0 || nr>=n || nc<0 || nc>=n) continue; 
            if(map[nr][nc] == -1/*|| isRobotExists(nr, nc)*/) continue;

            /* 격자마다 청소할 수 있는 최대 먼지량 = 20 */ 
            map[nr][nc] = (map[nr][nc] >= 20 ? map[nr][nc] - 20: 0); 
        }

    }
    /* 3. 먼지 축적 
    - 먼지가 있는 모든 격자에만 
    + 5
    */ 
    static void addDustOrigin() { 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] > 0){
                    map[i][j] += 5; 
                    // if(map[i][j] > 100) map[i][j] = 100; 
                }
            }
        }
    }
    /* 4. 먼지 확산 
    - 깨끗한 격자에
    - 주변 4방향 격자의 먼지량 합 / 10 만큼만
    */ 
    static void spreadDust() { 
        /*
        바로 갱신하지 않는다. 
        [][]: 먼지량 을 따로 관리하고

        전체 격자 돌면서 
            현재 위치 == 깨끗
                4방향 탐색 => 먼지량 합/ 10 (바로 갱신 ㄴㄴ)

        [][]: 먼지량 -> 지도에 반영
        */
        List<int[]> list = new ArrayList<>(); 
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                /* 깨끗한 격자 한정 */ 
                if(map[i][j] == 0){
                    int surroundTotal = 0; 
                    for(int d=0; d<4; d++){
                        int ni = i + dr[d]; 
                        int nj = j + dc[d]; 

                        if(ni<0 || ni>=n || nj<0 || nj>=n) continue; 
                        if(map[ni][nj] == -1 /*|| isRobotExists(ni, nj)*/) continue; 

                        surroundTotal += map[ni][nj]; 
                    }
                    list.add(new int[]{i, j, surroundTotal/10});
                }
            }
        }

        /* 확산 */ 
        for(int[] spreads: list){
            int i = spreads[0]; 
            int j = spreads[1]; 
            int dust = spreads[2]; 

            map[i][j] += dust; 
        }
    }
    /* 5. 출력 */ 
    static int calcTotalDust(){
        int result = 0; 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] > 0){
                    result += map[i][j];
                }
            }
        }
        return result; 
    }
    public static void main(String[] args) throws Exception{
        setup(); 
        StringBuilder sb = new StringBuilder();
        for(int turn=0; turn<l; turn++){
            // 1. 이동 -- 잘 되는 거 확인 
            moveAllRobot(); 
            // printAllRobotState(); 
            // 2. 청소 
            for(Robot r: robots){
                cleanByEachRobot(r);
            }
            // printMapState("cleanByEachRobot"); // -- ok
            // 3. 먼지 축적
            addDustOrigin(); 
            // printMapState("addDustOrigin"); 
            // 4. 먼지 확산
            spreadDust(); 
            // printMapState("spreadDust"); 
            // 5. 출력
            int totalDust = calcTotalDust(); 
            sb.append(totalDust).append("\n");
            if(totalDust == 0) break; 
        }
        System.out.println(sb);
    }
    static void printAllRobotState(){
        int i = 0; 
        System.out.println("#=== robot");
        for(Robot r: robots){
            System.out.println(i++ + "번째 로봇: ");
            System.out.println("-- 위치: " + r.r + ", " + r.c);
        }
    }
    static void printMapState(String msg){ 
        System.out.println("#=== " + msg);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(map[i][j] + " "); 
            }
            System.out.println(); 
        }
    }
}

/*
map[n][n]
1-index


1. 이동

각 로봇 청소기 순서대로
    이동거리가 가장 가까운 && 오염된 격자로 이동한다. 
        물건, 청소기면 못 지나감
        여러개면 여러개인대로 일단 저장
        -> 현재 위치 기준으로 bfs 진행 
    그 중에서 가장 행 오름차순, 열 오름차순 했을 때 맨 앞에 있는 곳으로 이동 

2. 청소 

각 로봇 청소기 순서대로 
    각 4방향에 대해서 오른-아래-왼-위 순서대로 조회하면서 먼지량 누적 
        만약 20 넘기면 20으로 처리
        dust[] idx: 먼지량 
        오른쪽이면 0 0,0/ -1,0 (0) / 1,0 (1) / 0,1 (3) 
        아래쪽이면 1 0,0/ 0,1 (3) / 1,0 (1) / 0,-1 (2)
        왼쪽이면 2 0,0/ 0,-1 (2) / -1, 0 (0)/ 1, 0 (1)
        위쪽이면 3 0,0/ -1, 0 (0)/ 0, -1 (2)/ 0, 1(3)
    각 순서대로 조회한 것 중에서 가장 큰 먼지량인 방향을 선택
        => list 로 관리하지 말고, 매번 업뎃으로 구현
        => 오른쪽부터 도니까, 오른쪽먼지량보다 크면 업뎃. 아니면 유지하면 될 듯? 
    그 방향에서 방문하는 4개의 격자 
        20 이하로 먼지 처리 

3. 먼지 축적
전체 격자 돌면서 
    먼지가 있으면 +5
- 고민해볼만한 거: 먼지가 있는 격자를 따로 해시맵으로 관리할 것인가? 굳이? 

4. 먼지 확산 -- 동시에 !!!
바로 갱신하지 않는다. 
[][]: 먼지량 을 따로 관리하고

전체 격자 돌면서 
    현재 위치 == 깨끗
        4방향 탐색 => 먼지량 합/ 10 (바로 갱신 ㄴㄴ)

[][]: 먼지량 -> 지도에 반영

5. 출력
- 총 먼지량 
전체 격자 돌면서
    총 먼지량 계산

시간 복잡도 
n 30 x 30 = 9 * 10^2 
로봇청소기 50개
*/