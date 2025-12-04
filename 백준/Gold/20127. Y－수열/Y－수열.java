import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static long[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new long[N];

        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 일단 증가 수열인지부터 확인하기
        int[] dp = new int[N]; Arrays.fill(dp, 0);
        for(int i=1; i<N; i++){
            if(arr[i] >= arr[i-1]) dp[i] = dp[i-1] +1;
        }

        int inc_res = check(dp);
        if(inc_res > 0){
            long startN = 0, lastN = arr[N-1];
            for(int i=0; i<N; i++){
                if(dp[i] == 0) {
                    startN = arr[i];
                    break;
                }
            }
            if(startN < lastN){
                inc_res = -1;
            }
        }

        Arrays.fill(dp, 0);
        for(int i=1; i<N; i++){
            if(arr[i] <= arr[i-1]) dp[i] = dp[i-1] + 1;
        }

        int dec_res = check(dp);
        if(dec_res > 0){
            long startN = 0, lastN = arr[N-1];
            for(int i=0; i<N; i++){
                if(dp[i] == 0) {
                    startN = arr[i];
                    break;
                }
            }

            if(startN > lastN){
                dec_res = -1;
            }
        }

        if(inc_res >= 0 && dec_res >= 0){
            System.out.println(Math.min(inc_res, dec_res));
        }else if(inc_res >= 0) System.out.println(inc_res);
        else if(dec_res >= 0) System.out.println(dec_res);
        else System.out.println(-1);
    }
    static int check(int[] dp){
        int zero_cnt = 0;
        int max_len = 0;
        int firstCutIdx = -1;
        for(int i=0; i<N; i++){
            if(dp[i] == 0) {
                zero_cnt++;
                if(i>0 && firstCutIdx == -1){
                    firstCutIdx = i;
                }
            }
        }
        if(zero_cnt == 1){
            return 0;
        }else if(zero_cnt == 2){
            return firstCutIdx;
        }else{
            return -1;
        }
    }
}
/*
맨 앞의 k개의 원소를 뒤로 옮겨서
오로지 증가하거나 오로지 감소하는 수열을 만들고 싶은 것
구해야 하는 거: 최소 k 구하기

N 10^6 원소는 long타입 필요

가능한 k가 여러 개 나오는 경우가 뭐가 있지
- 증가는 0
- 감소는 2

3 4 5 1 2
0 1 2 0 1

3 5 4 1 2
0 1 0 0 1

이미 증가하는 수열이면?
1 2 3 4 5
0 1 2 3 4

5개를 꼭 옮겨야 할까? 그냥 0이면 되잖아
0이 1개일 때는 0을 출력하는 게 맞겠다

0이 2개보다 많아지면 안되는 거네
증가하다가 감소하는 지점이 딱 한 군데여야 하네


 */