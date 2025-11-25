import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	// 옆으로(0), 아래로(1), 대각선으로(2)
	static int[] di = {0, 1, 1};
	static int[] dj = {1, 0, 1};
	static int count = 0;

	// 기존 Pipe 클래스 및 다른 메서드는 그대로 유지합니다.
	static class Pipe {
		int sx, sy, dx, dy;
		int direction;

		Pipe(int sx, int sy, int d) {
			this.sx = sx;
			this.sy = sy;
			this.dx = sx + di[d];
			this.dy = sy + dj[d];
			this.direction = d;
		}

		@Override
		public String toString() {
			return "시작점: (" + sx + ", " + sy + ") 끝점: (" + dx + ", " + dy + ") 방향 =" + direction;
		}
	}

	static boolean outOfBound(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	static boolean isWall(int x, int y) {
		return map[x][y] == 1;
	}

	/*
	 * isBlank 메서드는 대각선 이동 가능 여부를 판단하는 로직에 직접 통합되어 더 이상 필요하지 않습니다.
	 * 이는 코드를 더 명확하고 안전하게 만듭니다. (자세한 설명은 아래 '주요 변경 사항' 참고)
	 */

	static void dfs(Pipe p) {
		// Base Case: 파이프의 끝점이 (N-1, N-1)에 도달하면 경우의 수 1 증가
		if (p.dx == N - 1 && p.dy == N - 1) {
			count++;
			return;
		}

		// 현재 파이프의 끝점을 다음 이동의 시작점으로 사용
		int sx = p.dx;
		int sy = p.dy;

		// --- [1] 가로로 이동 (새 방향: 0) ---
		// 현재 파이프가 가로(0) 또는 대각선(2) 상태일 때만 가로로 이동 가능
		if (p.direction == 0 || p.direction == 2) {
			int ndx = sx + di[0];
			int ndy = sy + dj[0];
			
			// 다음 칸이 범위 안이고 벽이 아니면 이동
			if (!outOfBound(ndx, ndy) && !isWall(ndx, ndy)) {
				dfs(new Pipe(sx, sy, 0));
			}
		}

		// --- [2] 세로로 이동 (새 방향: 1) ---
		// 현재 파이프가 세로(1) 또는 대각선(2) 상태일 때만 세로로 이동 가능
		if (p.direction == 1 || p.direction == 2) {
			int ndx = sx + di[1];
			int ndy = sy + dj[1];

			// 다음 칸이 범위 안이고 벽이 아니면 이동
			if (!outOfBound(ndx, ndy) && !isWall(ndx, ndy)) {
				dfs(new Pipe(sx, sy, 1));
			}
		}

		// --- [3] 대각선으로 이동 (새 방향: 2) ---
		// 대각선 이동은 현재 방향과 상관없이 시도 가능
		// 단, 이동에 필요한 3칸(가로, 세로, 대각선 도착점)이 모두 비어있어야 함
		int ndx = sx + di[2];
		int ndy = sy + dj[2];

		// 대각선 도착 지점이 범위를 벗어나는지 먼저 확인하고,
		// 나머지 필수 공간이 벽이 아닌지 확인
		if (!outOfBound(ndx, ndy) && !isWall(ndx, ndy) && !isWall(sx, sy + 1) && !isWall(sx + 1, sy)) {
			dfs(new Pipe(sx, sy, 2));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        // 최종 도착지가 벽이면 어떤 방법으로도 도달 불가능하므로 미리 종료
		if (map[N - 1][N - 1] == 1) {
			System.out.println(0);
			return;
		}
        
		Pipe p = new Pipe(0, 0, 0);
		dfs(p);
		System.out.println(count);
	}
}