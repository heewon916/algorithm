import java.io.*;
import java.util.*;

/**
 * 메인 아이디어: int가 32비트. 대문자 알파벳 26개
 * 각 알파벳의 방문 여부를 비트로 표현하기
 */

/**
 * 기본 비트 연산 정리
 * 1. 특정 알파벳을 비트로 만들기
 * int bit = 1 << alphabet; // alphabet: 0~25라고 하자
 * A(0) -> 1 << 0 -> 0001
 * C(2) -> 1 << 2 -> 0100
 *
 * 2. 방문 여부 확인하기 - & AND연산
 * if(visited & bit) != 0) -> 이미 방문한 알파벳
 * visited = 시작할 때의 상태
 *
 * 3. 알파벳 방문 처리 - | OR연산
 * visited = visited | bit
 *
 * 4. 방문 해제: 백트래킹
 * visited = visited ^ bit
 */
public class Main {
    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = 1 << (input.charAt(j)-'A');
            }
        }

        ans = Integer.MIN_VALUE;
//        int startBit = 1 << map[0][0];
        dfs(0, 0, map[0][0], 1);
        System.out.println(ans);
    }
    static int ans;
    static void dfs(int r, int c, int visitedAlphas, int length){
        ans = Math.max(ans, length);
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            int bit = map[nr][nc];

            // map[nx][nc]에 있는 알파벳이 이미 포함되어 있니?
            if ((visitedAlphas & bit) != 0) continue; // 이미 방문한 알파벳임
            // 방문한 알파벳도 아니고, 방문했던 위치도 아니면?
            dfs(nr, nc, visitedAlphas|bit, length+1);
        }
    }
}
/*
시작점은 0,0

 */