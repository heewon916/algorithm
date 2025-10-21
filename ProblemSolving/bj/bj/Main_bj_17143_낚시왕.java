package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_17143_낚시왕 {
	// 위 아래 오른쪽 아래 1 2 3 4 d값 
	static int[] di = {-1, 1, 0, 0}; 
	static int[] dj = {0, 0, 1, -1}; 
	static class Shark{
		int r, c, speed, dir, size; 
		Shark(int r, int c, int speed, int dir, int size){
			this.r = r;
			this.c = c;
			this.speed = speed; 
			this.dir = dir; 
			this.size = size; 
		}
	}
	static ArrayList<Shark>[][] map; 
	static int R, C, M; 
	static void input(BufferedReader br, StringTokenizer st) throws Exception{
		 st = new StringTokenizer(br.readLine(), " ");
		 R = Integer.parseInt(st.nextToken());
		 C = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 
		 map = new ArrayList[R][C]; 
		 for(int i=0; i<R; i++) {
			 for(int j=0; j<C; j++) {
				 map[i][j] = new ArrayList<>(); 
			 }
		 }
		 for(int i=0; i<M; i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			 int r = Integer.parseInt(st.nextToken())-1;
			 int c = Integer.parseInt(st.nextToken())-1;
			 int speed = Integer.parseInt(st.nextToken());
			 int dir = Integer.parseInt(st.nextToken())-1;
			 int size = Integer.parseInt(st.nextToken());
			 
			 map[r][c].add(new Shark(r,c,speed,dir,size));
		 }
	}
	static int catchShark() {
		int user_c = -1; 
		int catch_size = 0; // 잡은 상어 크기의 합 
		while(user_c < C) {
			// 낚시왕 이동 
			user_c++; 
			if(user_c >= C) break;
			// user_c 열에 상어가 있는지 확인 
			// 있으면 그 상어 먹고 끝 
			for(int r=0; r<R; r++) {
				if(map[r][user_c].size() > 0) {
					catch_size += map[r][user_c].get(0).size; 
					map[r][user_c].remove(0); 
					break; 
				}
			}
			
			// 상어도 이동 
			moveShark(); 
		}
		return catch_size; 
	}
	/**
	 * ++ 여기가 포인트이지 아닐까.. 다시 풀자 
	 * 상어를 움직인다. 
	 * 
	 * 모든 r, c 에 대해서 거기에 상어가 있으면 
	 * 		상어를 제거하고 이동시킨다. 
	 * 그렇게 가야할 위치로 전부 보내고 난뒤, 
	 * 
	 * 전체 배열에 대해서 같은 위치에 size() > 1인 경우가 있으면 가장 큰 사이즈를 빼고 삭제한다. 
	 * 
	 * ++ 직접 map에서 바로바로 이동하는 것보다 새로운 맵을 만들어서 거기에다가 상어를 두는 게 낫다. 
	 */
	static void moveShark() {
		ArrayList<Shark>[][] nextMap = new ArrayList[R][C]; 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				nextMap[i][j] = new ArrayList<>(); 
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 만약 상어가 한 개도 없으면 이동할 애가 없
				if(map[i][j].size() == 0) continue; 
				
				// 이동할 상어가 있게 되면 
				Shark s = map[i][j].remove(0);
				if(s == null) {
					System.out.println("상어가 없어요");
					return; 
				}
				/**
				 * ++ 상어 이동 로직: 주기를 이용하자 
				 * 
				 * 상하 이동 왕복 주기 (R-1) *2  
				 * 좌우 이동 왕복 주기 (C-1) *2
				 * 
				 * 속도가 1000이고 R = 5일 떄 
				 * (5-1) * 2 = 8이므로 1000 % 8 = 0이다 
				 * 즉, 1000칸 이동은 0칸 이동과 같다고 생각하자 
				 * 
				 * 따라서 나머지만큼만 이동시키면 된다. 
				 */
				
				int speed = s.speed; 
				int r = s.r; 
				int c = s.c; 
				int dir = s.dir; 
				
				/**
				 * ++ 여기가 아무래도 포인트지 않을까.. 
				 */
				// 상하로 이동하는 경우 총 얼마나 이동해야 하는지를 확인하기  
				if(dir == 0 || dir == 1) {
					speed = (R == 1) ? 0 : speed % ((R-1)*2); 
				}
				
				// 좌우로 이동하는 경우 
				else if(dir == 2 || dir == 3) {
					speed = (C == 1) ? 0 : speed % ((C-1)*2); 
				}
				
				// 상어 이동시키고 
				for(int mv = 0; mv < speed; mv++) {
					int nr = r + di[dir];
					int nc = c + dj[dir];
					
					// 만약 범위를 벗어나면 방향을 전환해야 한다. 
					if(nr<0 || nr>=R || nc<0 || nc>=C) {
						// 0<->1, 2<->3
						dir = (dir%2 == 0) ? dir+1: dir-1; 
						nr = r + di[dir]; 
						nc = c + dj[dir]; 
					}
					
					/**
					 * ++ else문에 들어가면 안되고,
					 * 범위를 벗어나도 이동은 해야 되기 때문에 갱신을 해줘야 한다. 
					 */
					r = nr; 
					c = nc; 
				
				}
				
				// nextMap에 재배치 
				s.r = r;
				s.c = c; 
				s.dir = dir; 
				nextMap[r][c].add(s); 
			}
		}
		
		// 이동시켰으면 이제 같은 자리에 상어가 여러 마리 있는지 확인해야됨 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j].clear();
				
				// 최대 상어 사이즈를 찾아야 됨 
				if(nextMap[i][j].size() == 0) continue;
				else if(nextMap[i][j].size() > 1) {
					Shark maxShark = nextMap[i][j].get(0);
					for(int k=1; k<nextMap[i][j].size(); k++) {
						if(nextMap[i][j].get(k).size > maxShark.size) {
							maxShark = nextMap[i][j].get(k);
						}
					}
					map[i][j].add(maxShark);
				}else {
					map[i][j].add(nextMap[i][j].get(0));
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		input(br, st); 
		System.out.println(catchShark());
	}
}
/*
rxc 배열 100 x 100 
상어 개수 M <= 10^4 
 
상어 클래스 
r,c,speed, dir, size 

d: 1(위) 2(아래) 3(오른쪽) 4(왼쪽) 
di -1   1      0      0
dj  0   0      1      -1

1초

하나의 칸에 한 개의 상어만 있을 수 있고 
여러 개가 있게 되면 가장 큰 상어가 다른 애들을 잡아먹음 

그래프를 어떻게 표현할 것인가? 

각 칸에 상어를 넣는다? 
아니면.. 그냥 상어 리스트를 둔다? 

10^4칸에 상어 객체가 들어가고 

낚시꾼의 위치는 0,0 -> 0,1 -> 0,2 ... -> 0, C-1로 이동한다. (0,C)가 되면 stop 
낚시왕이 오른쪽으로 한 칸 이동함 
그 열에 상어가 있는지 확인하고 
	있으면 가장 작은 행에 있는 상어를 잡는다 
	있어도 없어도 일단 그 다음 열로 이동 
상어가 이동한다 
	각 상어의 방향과 속도 유지한채로 
	원래 위치의 r,c에서 속도만큼의 개수를 이동한다.
		속도/행크기 0이면 방향 안 바꿔도 되니까 
			현재 r,c에서 속도 + 방향 만큼만 이동하기 
		그 외의 경우 
			d = 4이면 (속도 - c) / C의 
			d = 3이면 (속도 - (C-1-c)) / C 
			d = 2이면 (속도 - (R-1-r)) / R
			d = 1이면 (속도 - r) / R
			몫+1이 방향 바꿔야 하는 횟수이고 나머지는 그냥 최종 위치 
			

*/