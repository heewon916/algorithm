import java.io.*; 
import java.util.*; 
public class Main {
	static int up_position, down_position; 
	static LinkedList<Integer> ud_q; 
	static LinkedList<Integer> lr_q; 
	static void moveDice(int moveDirection) {
		if(moveDirection == 4) {
			// 아래로 이동 
			ud_q.addFirst(ud_q.pollLast());
			int new_up = ud_q.get(up_position);
			lr_q.set(up_position, new_up);
		}else if (moveDirection == 3) {
			// 위로 이동 
			ud_q.addLast(ud_q.pollFirst());
			int new_up = ud_q.get(up_position);
			lr_q.set(up_position, new_up);
		}else if(moveDirection == 2) {
			// 왼쪽으로 이동 
			lr_q.addLast(ud_q.pollLast());
			ud_q.addLast(lr_q.pollFirst());
			int new_up = lr_q.get(up_position);
			ud_q.set(up_position, new_up);
		}else if(moveDirection == 1) {
			// 오른쪽으로 이동 
			lr_q.addFirst(ud_q.pollLast());
			ud_q.addLast(lr_q.pollLast());
			int new_up = lr_q.get(up_position);
			ud_q.set(up_position, new_up);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 주사위의 시작 위치 
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		// 명령의 개수 
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M]; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 우1 좌2 상3 하4
		int[][] d = {{0, 1},{0, -1},{-1, 0},{1, 0}}; 
		
		
		// 주사위가 갖고 있을 값을 저장 
		// 처음에는 0으로 모두 초기화되어 있다 
		int[] dice = new int[7]; // 1~6만 사용하자. 
		
		// 좌우 큐와 상하 큐 - 주사위의 전개도를 갖고 있다.
		//   2
		// 4 1 3
		//   5
		//   6
		lr_q = new LinkedList<>(); 
		ud_q = new LinkedList<>();
		lr_q.add(4);
		lr_q.add(1);
		lr_q.add(3);
		ud_q.add(2);
		ud_q.add(1);
		ud_q.add(5);
		ud_q.add(6);
	
		up_position = 1; // left_right_q, up_down_q 모두 1이 윗면을 가리킨다. 
		down_position = 3; // up_down_q에서 마지막 숫자가 바닥면을 가리킨다. 
		
		// 명령을 수행하면서, 주사위의 윗 면에 쓰여 있는 수를 출력한다. 
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			// 주사위가 이동하게 될 위치 
			int nx = x + d[cmd-1][0]; 
			int ny = y + d[cmd-1][1]; 
//			System.out.println("이동방향: " + cmd + " 주사위가 갈 위치: " + nx + ", " + ny);
			// 이동하게 될 위치가 범위를 벗어나면 명령 무시
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue; 
			
			// 갈 수 있는 범위 안에 있으면 
			// cmd 방향으로 주사위를 이동시킨다; 주사위의 전개도를 변형한다. 
			moveDice(cmd);
			// 이동한 칸에 있는 수가 0일 때, 바닥면 -> map으로 복사 
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[ud_q.get(down_position)];
			}else { // 이동한 칸에 있는 map 값이 0이 아닐 때, map -> 바닥면으로 복사 
				dice[ud_q.get(down_position)] = map[nx][ny]; 	
				map[nx][ny] = 0; 
			}
			// 주사위가 이동했을 때마다 상단(윗면)에 쓰여 있는 값을 구하라 ㅏ
			sb.append(dice[ud_q.get(up_position)]).append('\n');
			
			// 주사위 위치 변경 
			x = nx; 
			y = ny; 
		}
		System.out.println(sb.toString());
	}

}
/*
동, 서, 남, 북으로 이동할 때 주사위의 전개에서 위치 변화를 미리 기억해두고 
이동할 때마다 규칙을 적용해주면 된다. 

좌우 큐 LR 
상하 큐 NS 

각 명령을 수행하면서 
- 바닥면은 NS.getLast() 
- 윗면은 NS.get(1) 

이동 시, 그 좌표에 써 있는 수를 확인
- 0이면 주사위 바닥면 숫자 map으로 옮기기 
- 아니면 map의 숫자가 주사위로 옮겨짐 
*/