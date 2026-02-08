package bj;
import java.io.*;
import java.util.*;
public class Main_bj_4095 {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) System.exit(0);
            map = new int[n][m];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<m; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][m];
            for(int i=0; i<n; i++) dp[i][0] = map[i][0];
            for(int i=0; i<m; i++) dp[0][i] = map[0][i];

            for(int i=1; i<n; i++){
                for(int j=1; j<m; j++){
                    int a = dp[i-1][j-1];
                    int b = dp[i-1][j];
                    int c = dp[i][j-1];
                    if(map[i][j] == 0) dp[i][j] = 0;
                    else{
                        if(a == 0 || b == 0 || c == 0){
                            dp[i][j] = 1;
                        }else if(a == b && b == c){
                            dp[i][j] = dp[i-1][j] + 1;
                        }else{
                            dp[i][j] = Math.min(a, Math.min(b, c)) + 1;
                        }
                    }
                }
            }
            int ans = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            System.out.println(ans);
        }

    }

}
/*
nxm 행렬 1로 이루어진 가장 큰 정사각형 찾기
그때의 한 변의 길이 찾기

0 1 0 1 1
1 1 1 1 1
0 1 1 1 0
1 1 1 1 1

0 1 0 1 1
1 1 1 1 1
0 1 2 2 0
1 1 2 3 0

0, n 또는 n, 0은 그대로 map에 있는 값으로 초기화

내 위치가 i, j라고 하면
i-1, j
i, j-1
i-1, j-1 의 수가 전부 같으면 (0을 제외한) 그 수에 +1
0이면 -> 0
0이 아닌 자리에 좌표에 대해:
만약 3개 중에서 1개라도 0이면 그 자리는 1로 자기 자신이 됨
모두 0이 아님 && 모두 같은 수 => 그 수에 +1 하면 됨
모두 0이 아님 && 하나라도 다른 수 => 가장 작은 수에 +1

그렇게 n-1, n-1에 있는 게 답임

1 1 1 1
1 1 1 1
1 1 1 1

1 1 1 1
1 2 2 2
1 2 3 3

 */