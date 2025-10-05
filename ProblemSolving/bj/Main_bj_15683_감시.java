
import java.io.*; 
import java.util.*; 
public class Main_bj_15683_감시  {
    static int N, M; 
    static int[][] map; 
    static int[] b; 
    static ArrayList<Camera> list; 
    static class Camera{
    	int r, c, no;

		public Camera(int r, int c, int no) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
		} 
    	
    }
    /**
     * 중복조합을 위한 것 
     * @param cnt cctv의 개수만큼 돌고 끝난다. 
     * @param start i도 포함 
     */
    static void comb(int cnt) {
    	if(cnt == list.size()) {
    		int[][] tmp = new int[N][M]; 
    		for(int i=0; i<N; i++) {
    			for(int j=0; j<M; j++) {
    				tmp[i][j] = map[i][j]; 
    			}
    			// System.out.println(Arrays.toString(tmp[i]));
    		}
    		
    		direction(tmp);
    		minRes = Math.min(countNone(tmp), minRes);
//    		System.out.println("===결과물: " + minRes + "===");
    		return; 
    	}
    	for(int i=0; i<4; i++) {
    		b[cnt] = i; 
    		comb(cnt+1);
    	}
    	
    }
    static int minRes = Integer.MAX_VALUE; 
    static int[][] direction = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // 우(0) 하(1) 좌(2) 상(3) 
    static int countNone(int[][] tmp) {
    	int count = 0; 
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			if(tmp[i][j] == 0) count++; 
//    			System.out.print(tmp[i][j] + " ");
    		}
//    		System.out.println();
    	}
    	return count; 
    }
    static void direction(int[][] tmp) {
    	int res = 0;  // 사각지대 개수를 센다 
    	for(int i=0; i<list.size(); i++) {
    		int cctv_dir = b[i]; 
    		int cctv_no = list.get(i).no;
    		int r = list.get(i).r; 
    		int c = list.get(i).c; 
//    		System.out.println(cctv_no + "번호 cctv: " + (i+1) + "번째 cctv 방향: " + cctv_dir);
    		if(cctv_no == 1) {
    			fill(r, c, cctv_dir, tmp);
    			/*
    			int nr = r+direction[cctv_dir][0]; 
    			int nc = c+direction[cctv_dir][1]; 
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(tmp[nr][nc] == 6) break; 
    				if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
    					nr = r+direction[cctv_dir][0]; 
    					nc = c+direction[cctv_dir][1];     					
    					continue; 
    				}
    				tmp[nr][nc] = -1; 
    				nr = r+direction[cctv_dir][0]; 
    				nc = c+direction[cctv_dir][1]; 
    			}
    			 */
    		}else if(cctv_no == 2) {
    			int cctv_dir2 = (cctv_dir + 2)%4; 
    			fill(r, c, cctv_dir, tmp);
    			fill(r, c, cctv_dir2, tmp);
    			/*
    			int nr = r+direction[cctv_dir][0]; 
    			int nc = c+direction[cctv_dir][1]; 
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(tmp[nr][nc] == 6) break; 
    				if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
    					nr = r+direction[cctv_dir][0]; 
    					nc = c+direction[cctv_dir][1];     					
    					continue; 
    				}
    				tmp[nr][nc] = -1; 
    				nr = r+direction[cctv_dir][0]; 
    				nc = c+direction[cctv_dir][1]; 
    			}
    			nr = r+direction[cctv_dir2][0]; 
    			nc = c+direction[cctv_dir2][1]; 
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(tmp[nr][nc] == 6) break; 
    				if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
    					nr = r+direction[cctv_dir2][0]; 
    					nc = c+direction[cctv_dir2][1];     					
    					continue; 
    				}
    				tmp[nr][nc] = -1; 
    				nr = r+direction[cctv_dir2][0]; 
    				nc = c+direction[cctv_dir2][1]; 
    			}
    			*/
    		}else if(cctv_no == 3) {
    			int cctv_dir2 = (cctv_dir+1)%4; 
    			fill(r, c, cctv_dir, tmp);
    			fill(r, c, cctv_dir2, tmp);
    			/*
    			int nr = r+direction[cctv_dir][0]; 
    			int nc = c+direction[cctv_dir][1]; 
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(tmp[nr][nc] == 6) break; 
    				if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
    					nr = r+direction[cctv_dir][0]; 
    					nc = c+direction[cctv_dir][1];     					
    					continue; 
    				}
    				tmp[nr][nc] = -1; 
    				nr = r+direction[cctv_dir][0]; 
    				nc = c+direction[cctv_dir][1]; 
    			}

    			nr = r+direction[cctv_dir2][0]; 
    			nc = c+direction[cctv_dir2][1]; 
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(tmp[nr][nc] == 6) break; 
    				if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
    					nr = r+direction[cctv_dir2][0]; 
    					nc = c+direction[cctv_dir2][1];     					
    					continue; 
    				}
    				tmp[nr][nc] = -1; 
    				nr = r+direction[cctv_dir2][0]; 
    				nc = c+direction[cctv_dir2][1]; 
    			}
    			*/
    		}else if(cctv_no == 4) {
    			int cctv_dir2 = (cctv_dir+1)%4; 
    			int cctv_dir3 = (cctv_dir+2)%4; 
    			fill(r, c, cctv_dir, tmp);
    			fill(r, c, cctv_dir2, tmp);
    			fill(r, c, cctv_dir3, tmp);
    		}else if(cctv_no == 5) {
    			for(int d=0; d<4; d++) {
    				fill(r, c, d, tmp);
    			}
    		}
    	}
    	
    }
    static void fill(int r, int c, int dir, int[][] tmp) {
    	int nr = r+direction[dir][0]; 
    	int nc = c+direction[dir][1];
    	while(nr>=0 && nr<N && nc>=0 && nc<M) {
			if(tmp[nr][nc] == 6) break; 
			if(tmp[nr][nc]>=1 && tmp[nr][nc]<=5) {
				nr = nr+direction[dir][0]; 
				nc = nc+direction[dir][1];     					
				continue; 
			}
			tmp[nr][nc] = -1; 
			nr = nr+direction[dir][0]; 
			nc = nc+direction[dir][1]; 
//			System.out.println("-> 그 다음 좌표: " + nr + ", " + nc);
		}
  
    }
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M]; 
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j=0; j<M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if(map[i][j] >=1 && map[i][j] <=5) list.add(new Camera(i, j, map[i][j]));
        	}
        }
        b = new int[list.size()];
        comb(0);
        System.out.println(minRes);
        
    }
}
/**
[문제 이해하기] 
우(0) 하(1) 좌(2) 상(3) 

NxM 배열 
벽은 통과할 수 없음 
회전 항상 90도
0 빈칸/ 6 벽/ 1~5 cctv 번호 

1번 cctv -> 4가지 경우의 수 
2번 cctv -> 2가지 경우의 수 
3번 cctv -> 4가지 경우의 수 
4번 cctv -> 4가지 경우의 수 
5번 cctv -> 1가지 경우의 수 

cctv 최대 8개 
각 cctv가 가질 수 있는 최대 경우의 수 4 
4^8 = 2^16 = 10^3 * 2^6 
각 경우마다 개수 count -> NxM = 64 = 2^6 

10^3 * 2^12  = 10^6 * 2^

[설계하기] 
cctv의 위치와 번호를 구한다. 
cctv의 크기만큼 b[]배열을 만들어서, 어느 방향을 보고 있는지 조합을 만든다. 
이때, 방향이 중복되어도 된다. 따라서 v[]배열은 필요하지 않다. 

cctv의 개수만큼 방향을 모두 정했으면 
각 cctv에 대해서, 
	그 방향으로 바라봤을 때 NxM으로 #을 채운다. 

모두 채운 뒤, 
	0인 곳의 개수를 센다. 
	이 개수가 min값보다 작으면 갱신한다. 
	
조합: 4*2*4*4*1 = 조합의 경우 = 2^7 
2^7 * (8x8x8 + 8x8) = 2^7 * 2^9 = 2^16 =    
*/