package bj;
import java.io.*;
import java.util.*;
public class Main_bj_14722 {
    static int n;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n+1][n+1][3];
        if(map[1][1] == 0){
            dp[1][1][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                int current = map[i][j]; // 현재 위치에서 마실 수 있는 우유 번호
                int prev = (current + 2) % 3;
                int up = map[i-1][j];
                int side = map[i][j-1];


                // 근데 current == 0이 아니면 이전 우유를 마신 기록이 있을 때만 가능?

                if(up != prev && side != prev){ // 어느 쪽에서 와도 못 마심
                    // 따라서 어느 쪽에서 오든 최대로 먹을 수 있는 우유 개수를 챙긴다?
                    // 아닌 것 같은데?
                    // 누적의 의미니까 어차피 못 마시면 그 전의 상태값이 유지되어야 함
//                    dp[i][j][current] = Math.max(dp[i-1][j][up], dp[i][j-1][side]);
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j-1][1]);
                dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i][j-1][2]);
            }else if(up == prev && side == prev) { // 어느 쪽에서 와도 마실 수 있음
                    dp[i][j][current] = Math.max(dp[i-1][j][up], dp[i][j-1][side])+1;
                }else{
                    if(up == prev){ // 위에서 내려올 때
                        dp[i][j][current] = dp[i-1][j][prev]+1;
                    }else{ // 옆에서 올 수 있을 때
                        dp[i][j][current] = dp[i][j-1][prev]+1;
                    }
                }
            }
        }
        /**
         * 0,0일 때는?
         * 이전에 2가 아니어도 우유를 마실 수 있음
         *
         * 그럼 i,j 기준으로
         * i-1, j (위)
         * i, j-1 (옆)
         *
         * i, j에 있는 값이 0일 때는
         * 위나 옆에 2가 있었어야 함
         *
         * 2인 좌표인 경우 :
         * i, j에서 마실 수 있음
         * 그럼 2인 좌표에서의 값 +1이 dp에 저장될 것
         *
         * 2가 아닌 좌표인 경우:
         * i, j에서 안 마시는 걸로 처리됨
         * 그럼 dp[i-1][j][0] 아니면 dp[i][j-1][0]이 그대로 옴
         */


    }
}
/*
딸기 > 초코 > 바나나 > 딸기 ..
0 1 2 0 1 2
n xn 우유 가게
(1,1) -> (N, N) 출발점과 목적지가 정해져있음
마시거나 안 마시거나

동, 남으로만 움직임
i, j 기준
i+1, j
i, j+1

n 1000

0 1 2 2
1 1 1 1
2 2 2 2
0 0 1 0

0행과 0열은 일단 채울 수 있음 전부 동 아님 전부 남으로만 이동하는 경우임
dp[][]: 여태 먹은 우유 개수
num[][]: 가장 최근에 그 자리에 먹는 우유 번호

i, j를 구한다고 치면
(위에서 올때) map[i][j] = (num[i-1][j] + 1) % 3 (그 다음에 먹을 우유 번호이면)
=> 맞으면 dp[i-1][j] + 1 이 들어감
=> 아니면 -1
(옆에서 올때) map[i][j] = (num[i][j-1] + 1) % 3 (그 다음에 먹을 우유 번호이면)
=> 맞으면 dp[i][j-1] + 1이 들어감
=> 아니면 -1

둘 다 -1이면 (어느쪽에서 와도 못 마심)
- dp[i][j-1], dp[i-1][j] 중에 더 크거나 같은 값을 dp에 저장
- 더 큰 쪽에서 왔을 때 마지막으로 먹은 우유 번호 넣기

만약 하나만 -1이면
- -1이 아닌 쪽에서의 dp+1 && 그 자리서 먹은 map[i][j]

만약 둘 다 -1이 아니면
- dp+1한 값이 더 큰 쪽을 따른다
---
예외 상황의 존재:
i-1, j -> 5개를 마신 상태 & 마지막우유 = 1 (초코)
i, j-1 -> 4개를 마신 상태 & 마지막우유 = 2(바나나)
i, j가 0번일 때의 정답은? i, j-1에서 오는 게 맞다

근데? 우리의 논리를 따르면 i-1, j까지 먹는데 걸린 우유의 개수가 5개니까 거기서 오는 걸로 정해버림

따라서 dp[i][j] = value였으면
dp[i][j][state] = value
이때 state는 이 칸에 도착했을 때 마지막으로 마신 우유의 종류
dp[i][j][0]: 마지막으로 0을 먹었을 때의 최대 개수



 */