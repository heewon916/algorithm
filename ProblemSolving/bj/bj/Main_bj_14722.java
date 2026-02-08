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
        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][n];
        int[][] num = new int[n][n];

        dp[0][0] = (map[0][0] == 0? 1 : 0);
        num[0][0] = (dp[0][0] != 0? map[0][0]: 0); // 가장 최근에 먹은 우유 번호
        for(int i=1; i<n; i++){
            // 0행 계산
            if((num[0][i-1] + 1)%3 == map[0][i]){
                // 이어지면
                dp[0][i] = dp[0][i-1] + 1;
                num[0][i] = map[0][i];
            }else{
                // 안 이어지면
                dp[0][i] = dp[0][i-1];
                num[0][i] = num[0][i-1];
            }
        }

        // 0열 계산 필요
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
 */