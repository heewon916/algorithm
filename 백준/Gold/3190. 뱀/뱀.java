import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] mat = new int[N][N]; 
        // 사과의 개수만큼 mat에 표시한다. 
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	mat[a-1][b-1] = 1; 
        }
        
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        
        // 뱀의 방향 변환 정보를 turns에 저장한다. 
        List<int[]> turns = new ArrayList<>(); 
        for(int i=0; i<L; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int time = Integer.parseInt(st.nextToken());
        	int turn = st.nextToken().equals("L")? -1: 1;	// 왼쪽으로 회전해야 하는 경우 turn은 -1을, 오른쪽으로 회전해야 하는 경우 1을 준다. 
        	turns.add(new int[] {time, turn});
        }
          
        // d=0) 상 -> 우 -> 하 -> 좌 
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1}; 
        
        // 처음에 바라보고 있는 방향: 오른쪽 
        int d = 1;
        
        /*
         * 현재 위치 x,y
         * move() 
         * 1. 다음 위치로 머리를 내민다. 
         * nx = x+dx[d], ny = y+dy[d] 
         * 뱀의 몸이 있는 좌표를 큐로 관리
         * 2. 사과가 없으면 그 다음 위치로 그 전의 몸통을 다 끌어와야 한다 
         * - 큐에서 popfirst(), 그리고 다음 위치를 큐에 add 
         * 3. 사과가 있으면 그 전의 내용 유지 + 다음 위치도 -1로 설정하면 된다
         * - 큐에 다음 위치만 add 
         */
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        q.add(new int[] {0, 0});
        mat[0][0] = 2; 
        int time = 0; 
        int turnIdx = 0; 	// turns에서 time이 방향 돌릴 시간이 됐는지 체크한다. 
        while(true) {
        	time++; 
        	int[] cur = q.getLast();
        	int x = cur[0], y = cur[1]; 	// 현재 위치 - 가장 최근에 도착한 머리
//        	System.out.println("@cur: " + x + ", " + y + " #time: " + time);
        	
        	// 다음 위치 구하고
        	int nx = x+dx[d], ny = y+dy[d]; 
        	// 벽에 부딪힌 경우; 범위를 벗어나는 경우
        	if(0>nx || nx>=N || 0>ny || ny>=N) break; 
        	// 자기 자신에 부딪힌 경우 
        	if(mat[nx][ny] == 2) break; 
        	
        	// 사과가 없는 경우 
        	if(mat[nx][ny] == 0) {
        		// 꼬리를 옮긴다. (큐에서 맨 뒤에 있는 좌표를 뺀다.)
        		int[] del = q.pollFirst();
        		int del_x = del[0], del_y = del[1]; 
        		mat[del_x][del_y] = 0; 
        		// 그리고 머리를 옮긴다. (큐에 다음 위치를 넣는다.) 
        		q.offer(new int[] {nx, ny});
        		mat[nx][ny] = 2; 
        	}
        	else if(mat[nx][ny] == 1) {
        		// 사과가 있는 경우 (큐에 다음 위치만 넣으면 된다. 길이가 길어졌으니까) 
        		q.offer(new int[] {nx, ny});
        		mat[nx][ny] = 2; 
        	}
        	
        	// 방향을 회전해야 하는 시간이면 
        	/*
        	 * 방향 회전은 X초가 끝난 뒤에, 즉 뱀이 머리를 옮기고 난 뒤에서야 해야 했다. 
        	 */
        	if(turnIdx < L && turns.get(turnIdx)[0] == time) {
        		int turn_dir = turns.get(turnIdx)[1]; 	// L이면 왼쪽으로 도니까 -1, D이면 오른쪽으로 도니까 1 
        		d = (d+turn_dir+4)%4; 
        		turnIdx++; 
        	}
        	
//        	for(int i=0; i<N; i++) {
//        		for(int j=0; j<N; j++) {
//        			System.out.print(mat[i][j] + " ");
//        		}
//        		System.out.println();
//        	}
        }
        System.out.println(time);
    }	
}
/*
시간제한 1초 
128MB 

100x100 
사과 100개 
L 100번




*/