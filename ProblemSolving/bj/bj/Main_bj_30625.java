package bj;
import java.io.*;
import java.util.*;
public class Main_bj_30625 {
    static int n, m;
    static int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] danceMap = new int[n][2]; // i번째 라운드에서 추는 춤의 종류와 방향을 입력받는다
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            danceMap[i][0] = a;
            danceMap[i][1] = b;
        }

        // 모두 다 맞히는 경우
        // 0일 때는 1가지; 1일 때는 M-1가지
        long ans = 1;
        for (int i = 0; i < n; i++) {
            if (danceMap[i][1] == 1) {
                ans = ans * (m - 1) % MOD;
            }
        }
        ans = ans % MOD;

        // i번쩨 라운드에서 틀린다고 가정했을 때
        // 앞쪽 다 정답 * i번째 오답 * 뒤쪽 다 정답
        // pref[i]: 0~ i-1번 라운드까지의 정답인 경우의 누적 수
        // sub[i]: i+1 ~ n-1번 라운드까지의 정답인 경우의 누적 수
        long[] pref = new long[n];
        long[] sub = new long[n];

        if (danceMap[0][1] == 1) pref[0] = m - 1;
        else pref[0] = 1;
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] * (danceMap[i][1] == 1 ? m - 1 : 1) % MOD;
        }

        if (danceMap[n - 1][1] == 1) sub[n - 1] = m - 1;
        else sub[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            sub[i] = sub[i + 1] * (danceMap[i][1] == 1 ? m - 1 : 1) % MOD;
        }

        // 1개의 라운드에서만 틀리는 경우
        // 어느 라운드에서 틀릴거냐
        for (int i = 0; i < n; i++) {
            long p = 0, s = 0;

            if (i == 0) p = 1;
            else p = pref[i - 1];

            if (i == n - 1) s = 1;
            else s = sub[i + 1];

            long tmp = danceMap[i][1] == 1 ? 1 : m - 1;
            tmp = (tmp * p) % MOD;
            tmp = (tmp * s) % MOD;
            ans = (ans + tmp) % MOD;
        }
        System.out.println(ans);
    }
}
//        for(int i=0; i<n; i++){
//            // i번째 라운드에서 틀린다고 했을 때
//            // 1~ i-1라운드까지는 정답
//            // i+1 ~ n 라운드까지도 정답
//            int cnt = 1;
//            for(int j=0; j<n; j++){
//                if(i == j){
//                    if(danceMap[j][1] == 0) cnt *= (m-1);
//                } else{
//                    if(danceMap[i][1] == 1) cnt *= (m-1);
//                }
//            }
//            ans = (ans + cnt) % MOD;
//        }

/*
1. 모두 다 맞힌 경우
- k번째 라운드의 우진 춤 = 0 -> 1 가지 (무조건 같은 춤을 춰야 함)
- k번째 라운드의 우진 춤 = 1 -> M-1 가지 (무조건 다른 춤을 춰야 함)
=> 따라서 (우진 춤 = 1 춤 종류의 개수) * (M-1)

2. 1개의 라운드만 틀리는 경우
- k번째 라운드의 우진 춤 = 0 -> 다른 춤을 춰야 틀림 -> M-1 가지
- k번째 라운드의 우진 춤 = 1 -> 같은 춤을 춰야 틀림 -> 1가지
- 어느 라운드에서 틀릴 지 정해야 함 -> N가지의 경우가 존재함
-> 1번째 라운드에서 틀릴거야
-> 그 값이 0이냐 1이냐에 따라 달라지고, 그 뒤는 다 정답이어야 하니까
   각 라운드의 우진 춤에 따라 가짓수가 달라짐.

그럼 결국 0~ n-1번 라운드까지의 정답 수를 쫙 다 누적곱으로 구해놓으면 어떨까?
arr[i]: 0~ i번 라운드까지의 정답인 경우의 누적 곱이라고 했을 때
k번째 라운드에서만 틀린다고 하면
arr[k-1] * (k번째 라운드의 b_i = 0이면 M-1; 1이면 1) * (arr[n-1] / arr[k]) 아닌가?

예외의 존재:
M = 1이면 그때 다른 방향을 춰야 답인데, 그 경우가 0일 수 있음
그럼 arr[k]이 0이 돼서 0으로 나누는 이슈가 발생함
 */