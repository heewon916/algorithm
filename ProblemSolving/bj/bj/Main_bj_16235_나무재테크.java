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
        public Tree(int r, int c, int age) {
            super();
            this.age = age;
            this.r = r;
            this.c = c;
        }
		@Override
		public String toString() {
			return "나무정보: (" + r + ", " + c + ") 나이: " + age ;
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
	        for(int i=0; i<N; i++) {
	            for(int j=0; j<N; j++) {
	            	PriorityQueue<Tree> aliveTemp = new PriorityQueue<>((o1, o2) -> o1.age - o2.age); 
	            	int deadNutrient = 0; 
	            	/**
	            	 * 죽었으면 버려야지 그대로 PQ에 쌓아뒀었음. 
	            	 * 그리고 alive = false;로 굳이 표현하지 않아도 되는게 죽은 나무들에 대해서는 나이만 관심사가 있었음 
	            	 * 그래서 따로 리스트로 만들어서 안 빼도 됐음
	            	 * 
	            	 * 그리고 여기서는 정보 업데이트가 계속 발생하니까 굳이 따로 변수에 danceMap[i][j]를 담지 말고
	            	 * 바로바로 처리하는 게 훨 깔끔함 
	            	 */
	            	while(!treeMap[i][j].isEmpty()) {
	            		Tree t = treeMap[i][j].poll(); 
	            		if(t.age > map[i][j]) {
	            			deadNutrient += t.age/2; 
	            		}else {
	            			map[i][j] -= t.age; 
	            			t.age++; 
	            			aliveTemp.add(t);
	            		}
	            	}
	            	/**
	            	 * treeMap[i][j] = aliveTemp; 보다 addAll이 훨씬 안정적이다.
	            	 */
	            	map[i][j] += deadNutrient; 
	            	treeMap[i][j].addAll(aliveTemp);
	            }
	        }
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<N; j++) {
	        		/**
	        		 * 주의해야 했던 점은 바로 PQ에서 값을 바꿔버리면 수정과 동시에 접근하려는 문제가 발생한다. 
	        		 * 따라서 안전하게 복사본을 만들어서 거기서 spread해도 문제가 없으니 그렇게 하자 
	        		 */
	        		ArrayList<Tree> modify = new ArrayList<>(treeMap[i][j]);  // 복사본 만든 거임 
	        		for(Tree t: modify) {
	        			if(t.age%5 == 0) {
	        				spread(t.r, t.c);
	        			}
	        		}
	        	}
	        }

	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<N; j++) {
	        		map[i][j] += nutrient[i][j]; 
	        	}
	        }
	        year--; 
	    }
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		aliveCount += treeMap[i][j].size(); 
	    	}
	    }
	    System.out.println(aliveCount);
	    long t2 = System.currentTimeMillis(); 
//	    System.out.println((t2-t1)+"ms");
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
