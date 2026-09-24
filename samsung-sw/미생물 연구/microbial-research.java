import java.util.*;
import java.io.*;

public class Main {
    public static int n, q; // nxn, turn q
    public static int[][] map;

    public static class Bug {
        int id;
        int size;
        int startR;
        int startC;
        boolean isRemoved = false;

        Bug(int id, int size) {
            this.id = id;
            this.size = size;
        }
    }

    public static int[] dr = {0, 0, -1, 1};
    public static int[] dc = {-1, 1, 0, 0};
    public static List<Bug> bugList;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void setup() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // ?? 의문이 들고 있는 거: (0, 0) ~ (n, n)이라는데 그럼 배열을 n+1, n+1로 잡는 게 맞나
        // ㄴ 아님.. 마지막 칸은 (7,7) ~ (8,8)로 정의되기 때문에 (9,9)까지 잡으면
        //    (8,8)~(9,9)가 마지막 칸이 되어버림
        // ㄴ 경계를 말하는 거임 유의하셈 !
        map = new int[n][n];
        bugList = new ArrayList<>();
    }

    public static void addBug(int id) throws Exception{
         
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int size = Math.abs(r1 - r2) * Math.abs(c1 - c2);

        Bug bug = new Bug(id, size);
        bugList.add(bug);

        // - map[i][j] != 0? new : map[i][j] 덮어쓰기 유의
        Set<Bug> affectedList = new HashSet<>(); 
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                if (map[i][j] != 0) {
                    Bug prevBug = getBugById(map[i][j]); 
                    affectedList.add(prevBug); 
                }

                map[i][j] = id; // 그냥 항상 덮어 쓰면 되니까
            }
        }

        // !! 만약 기존 덩어리가 두 덩어리로 나뉘면 사라짐 
        // - 지도에서 삭제 
        for(Bug b: affectedList){
            if(isDividedOverTwo(b)){
                removeBugFromMap(b); 
            }
        }

        updateAllBugSize(); 
    }
    public static void updateAllBugSize(){
        for(Bug bug : bugList){
            bug.size = 0; 
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] != 0){
                    Bug bug = getBugById(map[i][j]); 
                    bug.size++; 
                }
            }
        }
    }
    public static void removeBugFromMap(Bug bug){
        bug.isRemoved = true; 
        bug.size = 0; 

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == bug.id) map[i][j] = 0; 
            }
        }
    }
    public static void bfs(int i, int j, boolean[][] v, int id){
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j}); 
        v[i][j] = true; 

        while(!q.isEmpty()){
            int[] curr = q.poll(); 
            int cr = curr[0]; 
            int cc = curr[1]; 

            for(int d=0; d<4; d++){
                int nr = cr + dr[d]; 
                int nc = cc + dc[d]; 
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue; 
                if(v[nr][nc]) continue; 
                if(map[nr][nc] != id) continue; 

                q.add(new int[]{nr, nc}); 
                v[nr][nc] = true; 
            }
        }
    }
    public static boolean isDividedOverTwo(Bug bug){
        boolean[][] v = new boolean[n][n]; 
        int count = 0; 
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!v[i][j] && map[i][j] == bug.id){
                    bfs(i, j, v, bug.id); 
                    count++; 
                }
            }
        }
        if(count >= 2){
            return true; 
        }

        return false; 
    }
    public static int[] findStartPoint(Bug bug){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == bug.id) {
                    return new int[]{i, j}; 
                }
            }
        }
        return null; 
    }

    public static List<int[]> getPosInOriginMap(Bug bug, int[][] map) {
        List<int[]> res = new ArrayList<>();

        // int r = bug.startR;
        // int c = bug.startC;
        int[] start = findStartPoint(bug); 
        if(start == null) return res; 

        int r = start[0]; 
        int c = start[1]; 

        // res.add(new int[]{0, 0}); // 현재 위치 추가 

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == bug.id){
                    res.add(new int[] {i-r, j-c}); 
                }
            }
        }

        return res;
    }

    public static void printLog(String method, String message) {
        System.out.println(method + ":: " + message);
    }
    public static Bug getBugById(int id){
        for(Bug bug: bugList){
            if(bug.id == id) return bug; 
        }
        return null; 
    }
    public static void putBugOnNewMap(
            int sr,
            int sc,
            int bugId,
            List<int[]> positions,
            int[][] nMap
    ) {
        // 왼쪽 아래 초기화 
        Bug bug = getBugById(bugId); 
        if(bug == null) return ; 

        // 위치 이동
        for (int[] pos : positions) {
            int r = sr + pos[0];
            int c = sc + pos[1];

            nMap[r][c] = bugId;
        }
    }

    // 새로운 지도에서 둘 수 있는가를 확인한다.
    public static boolean checkPositions(
            int r,
            int c,
            List<int[]> positions,
            int[][] nMap
    ) {
        // 시작점부터 겹침
        // if (nMap[r][c] != 0) return false; // 없어도 되는게 positions 에는 0,0 도 포함되어 있음

        // 시작점은 안 겹침. 모양 따라가다가 겹침 && 범위 벗어남
        for (int[] pos : positions) {
            int nr = r + pos[0];
            int nc = c + pos[1];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                return false; // 범위를 벗어남.
            }

            if (nMap[nr][nc] != 0) {
                return false; // 겹침
            }
        }

        return true;
    }

    public static boolean moveBug(Bug bug, int[][] newMap) {
        // 기존 지도 기준:
        // 시작점을 기준으로 모양만, 상대위치만 기록하는 걸로 바꾸자
        List<int[]> positions = getPosInOriginMap(bug, map);

        if (positions.isEmpty()) {
            return false; 
        }

        // 형태 유지 가능 && 범위 안 벗어남 && 안 겹침
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 새로운 지도 기준:
                boolean canMove = checkPositions(i, j, positions, newMap);

                if (!canMove) continue; 
                // return false; // - 끝낼 게 아니라 다음 위치에 대해서 탐색을 해야지 

                // 새로운 지도:
                // 움직일 수 있으면 실제로 이동시키기
                putBugOnNewMap(i, j, bug.id, positions, newMap);
                // 위치 바꾸고 나서 size 재계산 필요 
                // calcBugSize(i, j, bug.id, newMap); 
                return true; 
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        setup();
        StringBuilder sb = new StringBuilder(); 

        for (int id = 1; id <= q; id++) {

            // 1. 미생물 추가
            // - map[i][j] != 0? new : map[i][j] 덮어쓰기 유의
            // - size 변경 
            addBug(id);

            // 2. 모든 미생물 이동
            // - 이동:
            // - size 내림차순, id 오름차순 순서대로 이동
            // - 이동가능조건:
            //   형태유지 & 범위 안 벗어남 & 안 겹침 & x,y오름차순
            bugList.sort((a, b) -> {
                if (a.size != b.size) {
                    // b-a; 로는 작동 안해 
                    return Integer.compare(b.size, a.size);
                }
                return Integer.compare(a.id, b.id);
            });

            int[][] nMap = new int[n][n]; // 새 맵을 만들어도 메모리가 괜찮을까 ...

            for (Bug bug: bugList) {
                if (bug.isRemoved) continue;

                boolean hasMoved = moveBug(bug, nMap);

                if (!hasMoved) {
                    bug.isRemoved = true;
                    bug.size = 0; 
                }
            }
            map = nMap; 
            updateAllBugSize(); 

            // 3. 출력결과 계산하기 StringBuffer에 추가하기
            // nMap 기준으로 계산 진행해야 됨.
            int[][] checkAdj = new int[q+1][q+1]; 

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int a = nMap[i][j];
                    if(a == 0) continue; 

                    for (int d = 0; d < 4; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];

                        if (ni < 0 || ni >= n || nj < 0 || nj >= n) {
                            continue;
                        }
                        int b = nMap[ni][nj]; 

                        if(b == 0) continue; 
                        if(a == b) continue; 

                        if (a != b) {
                            checkAdj[a][b] = 1; 
                            checkAdj[b][a] = 1; 
                        }
                    }
                }
            }
            
            int ans = 0; 
            for(int i=1; i<=q; i++){
                for(int j=i+1; j<=q; j++){
                    if(checkAdj[i][j] == 1){
                        Bug a = getBugById(i); 
                        Bug b = getBugById(j);
                        if(a.isRemoved || b.isRemoved) continue;  
                        ans += a.size * b.size; 
                    }
                }
            }
            sb.append(ans).append("\n"); 
        }
        System.out.println(sb);
    }
}