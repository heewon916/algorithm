package bj;
import java.io.*; 
import java.util.*; 
public class Main_bj_16235_나무재테크 {
    static int[][] nutrient; 
    static int[][] map; 
    static int N, M, K;
    static PriorityQueue<Tree>[][] treeMap; 
    static class Tree{
        int age; 
        int r, c; 
        boolean alive;
        public Tree(int r, int c, int age) {
            super();
            this.age = age;
            this.r = r;
            this.c = c;
            this.alive = true;
        }
		@Override
		public String toString() {
			return "나무정보: (" + r + ", " + c + ") 나이: " + age + ", 살아있는가? " + alive; 
		} 

    }
    static void input(BufferedReader br, StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 배열 크기 
        M = Integer.parseInt(st.nextToken()); // 나무 개수 
        K = Integer.parseInt(st.nextToken()); // 년 수 
        aliveCount = 0; 
        
        map = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(map[i], 5);

        nutrient = new int[N][N]; 
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        treeMap = new PriorityQueue[N][N]; 
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		treeMap[i][j] = new PriorityQueue<>((o1, o2) -> o1.age-o2.age);        
        	}
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            Tree t = new Tree(x, y, age);
            treeMap[x][y].offer(t);
        }

    }
    static int[] di = {-1,-1,-1,0,0,1,1,1};
    static int[] dj = {-1,0,1,-1,1,-1,0,1};
    static int aliveCount; 
    static void spread(int i, int j) {
    	for(int d=0; d<8; d++) {
    		int ni = i + di[d]; 
    		int nj = j + dj[d]; 
    		if(ni<0 || ni>= N || nj<0 || nj>=N) continue; 
    		treeMap[ni][nj].add(new Tree(ni, nj, 1));
    	}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 
        long t1 = System.currentTimeMillis();
        input(br, st);
        /**

	     * 계절마다 나무를 전체로 돌면? 최소 400 중간에 bfs도 있으니까 더 함 
	     * 이게 K년 후니까 K가 최대 1000년 
	     * 6 * 10^5
	     */
	    int year = K; 
	 
	    while(year > 0) {
	        // 봄 
	    	System.out.println("---봄---");
	        for(int i=0; i<N; i++) {
	            for(int j=0; j<N; j++) {
	            	PriorityQueue<Tree> trees = treeMap[i][j]; 
	            	if(trees.size() == 0) continue; 
	            	System.out.println("현재 위치: " + i + ", " + j);
	            	int nut = map[i][j]; 
	            	for(Tree t : trees) {
	            		System.out.println(t);
	            		if(t.age > nut || nut == 0) {
	            			System.out.println("-> 죽음");
	            			t.alive = false; 
	            		}else {
	            			System.out.println(nut + ", "  + t.age);
	            			nut = nut - t.age > 0 ? nut - t.age : 0; 
	            			System.out.println(nut);
	            			map[i][j] = nut; 
	            			System.out.println("나무에게 양분을 주고 나서: " + map[i][j]);
	            			t.age++; 
	            			System.out.println("양분 먹음 -> 남은 양분: " + map[i][j] + " / 나이: " + t.age);
	            		}
	            	}
	            	System.out.println("- 남은 양분 : " + map[i][j]);
	            }
	        }
		    // 여름 
	        System.out.println("---여름---");
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<N; j++) {
	        		System.out.println("현재 위치: " + i + ", " + j);
	        		PriorityQueue<Tree> trees = treeMap[i][j]; 
	            	if(trees.size() == 0) continue; 
	            	for(Tree t : trees) {
	            		if(t.alive) continue; 
	            		map[i][j] += t.age/2; 
	            		System.out.println("- 양분 추가: " + (t.age/2));
 	            	}
	        	}
	        }
	        
		    // 가을 
	        System.out.println("---가을---");
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<N; j++) {
	        		System.out.println("현재 위치: " + i + ", " + j);
	        		PriorityQueue<Tree> trees = treeMap[i][j]; 
	        		if(trees.size() == 0) continue; 
	        		ArrayList<Tree> modify = new ArrayList<>(); 
	        		for(Tree t: trees) {
	        			if(t.age%5 == 0) {
	        				modify.add(t);
	        			}
	        		}
	        		for(Tree t: modify) {
	        			spread(t.r, t.c);
	        		}
	        	}
	        }
		    // 겨울 
	        System.out.println("---겨울---");
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<N; j++) {
	        		map[i][j] += nutrient[i][j]; 
	        		System.out.print(map[i][j]+ " ");
	        	}
	        	System.out.println();
	        }
	        year--; 
	    }
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		PriorityQueue<Tree> t = treeMap[i][j];
	    		if(t.size() == 0) continue; 
	    		for(Tree tree: t) {
	    			if(tree.alive) aliveCount++; 
	    		}
	    	}
	    }
	    System.out.println(aliveCount);
	    long t2 = System.currentTimeMillis(); 
	    System.out.println((t2-t1)+"ms");
    }	
    
}
/*
nxn 배열 

r,c 행과 열 1-index 

땅의 양분 조사 기본값 5 

M개의 나무 심음 
한 칸에 1개 이상의 나무 가능 

봄 
어린 나무부터 - 나이만큼의 양분이 필요하다 
    칸의 양분 -= 나무의 나이 
    나무의 양분 += 나무의 나이 
    나무의 나이 += 1
    나무의 나이 > 칸의 양분 -> die (나무의 나이)
여름 
die한 나무들
    칸의 양분 += (int)죽은 나무의 나이/2

가을
나무들에 대해서 
    나무의 나이가 5의 배수이면 
        인접 8칸에 나무 생성 - 나이 = 1
겨울 
땅에 양분 추가 
    전체 for문 
        각 칸의 양분 += A[r][c] 
        

구해야 하는 것: K년 후 살아있는 나무의 개수
나무 클래스 

나이
좌표
생존 여부
칸 클래스 

양분 = 초기값 5
좌표
N 배열 크기 10 
M 나무 개수 100
K 1000년 후 
0.3초 = 10^7 * 3 
*/