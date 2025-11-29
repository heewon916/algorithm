package bj;

import java.io.*; 
import java.util.*; 

public class Main_bj_20058_마법사상어와파이어스톰 {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
    static int N, Q;
    static int[][] map; 
    static int mapSize; 
    static int[] spinSizes;  // 맵을 나눠서 돌릴 때 실제 너비를 담고 있다. 2의 n승 값들 말하는 거임
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 2^N 배열이 된다 
        Q = Integer.parseInt(st.nextToken()); // 반복할 횟수 

        mapSize = (int) Math.pow(2, N);
        map = new int[mapSize][mapSize];
        for(int i=0; i<mapSize; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<mapSize; j++){
            	map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        spinSizes = new int[Q];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<Q; i++) {
        	spinSizes[i] = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        }
        
        /**
         * 파이어스톰 시전하기
         * 1. 2^L 정사각형으로 나눠서 90도 시계방향으로 돌리기
         * 2. 각 칸에 대해서 인접한 상하좌우 칸 중, 얼음 칸 < 3 이면 그 칸의 얼음 -1
         * 위의 과정을 Q번 반복하기
         *
         * 구해야 되는 거
         * 1. 전체 mat배열의 얼음 총 합
         * 2. bfs로 가장 큰 얼음 덩어리의 칸 개수 세기
         */

        for(int i=0; i<Q; i++){
        	int spinSize = spinSizes[i]; 
        	// 1. 나눠서 돌리기 
        	int[][] tempMap = new int[spinSize][spinSize];
        	for(int x=0; x<mapSize; x+=spinSize) {
        		for(int y=0; y<mapSize; y+=spinSize) {
        			for(int r=0; r<spinSize; r++) {
        				for(int c=0; c<spinSize; c++) {
        					tempMap[r][c] = map[x+r][y+c];
        				}
        			}
        			for(int r=0; r<spinSize; r++) {
        				for(int c=0; c<spinSize; c++) {
        					map[x+r][y+c] = tempMap[spinSize-1-c][r];
        				}
        			}
        		}
        	}
        	
        	// 2. 상하좌우 얼음 없애기 
        	ArrayList<int[]> meltList = new ArrayList<>(); 
        	for(int x=0; x<mapSize; x++) {
        		for(int y=0; y<mapSize; y++) {
        			if(map[x][y] == 0) continue; 
        			
        			int cnt = 0; 
        			for(int d=0; d<4; d++) {
        				int nx = x+dx[d]; 
        				int ny = y+dy[d]; 
        				if(nx<0 || nx>=mapSize || ny<0 || ny>=mapSize) continue; 
        				if(map[nx][ny] > 0) cnt++; 
        			}
        			
        			if(cnt < 3) {
        				meltList.add(new int[] {x, y});
        			}
        		}
        	}
        	
        	for(int[] pos: meltList) {
        		map[pos[0]][pos[1]]--; 
        	}
        }
        
        // 3. 남아있는 얼음의 총 합, 가장 큰 덩어리의 칸 개수를 세야 됨 
        int ans1 = 0; 
        int ans2 = 0; 
        
        for(int i=0; i<mapSize; i++) {
        	for(int j=0; j<mapSize; j++) {
        		if(map[i][j] > 0) ans1 += map[i][j];
        	}
        }
        
        boolean[][] v = new boolean[mapSize][mapSize]; 
        for(int i=0; i<mapSize; i++) {
        	for(int j=0; j<mapSize; j++) {
        		if(!v[i][j] && map[i][j] > 0) {
        			ans2 = Math.max(bfs(i, j, v), ans2);
        		}
        	}
        }
        System.out.println(ans1);
        System.out.println(ans2);
    }
    static int bfs(int i, int j, boolean[][] v) {
    	int blockCnt = 1;
    	ArrayDeque<int[]> dq = new ArrayDeque<>(); 
    	dq.add(new int[] {i, j});
    	v[i][j] = true; 
    	while(!dq.isEmpty()) {
    		int[] cur = dq.poll(); 
    		for(int d=0; d<4; d++) {
    			int nx = cur[0] + dx[d]; 
    			int ny = cur[1] + dy[d]; 
    			if(nx<0 || nx>=mapSize || ny<0 || ny>=mapSize) continue; 
    			if(v[nx][ny] || map[nx][ny] <= 0) continue; 
    			dq.add(new int[] {nx, ny});
    			v[nx][ny] = true; 
    			blockCnt++; 
    		}
    	}
    	
    	return blockCnt; 
    }
}
