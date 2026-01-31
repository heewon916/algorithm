import java.io.*;
import java.util.*;
public class Main {
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
