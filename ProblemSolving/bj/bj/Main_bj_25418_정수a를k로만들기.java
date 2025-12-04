package bj;
import java.util.*;
import java.io.*;
public class Main_bj_25418_정수a를k로만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;
        /**
         * 조심해야 될 거: 아이디어 자체는 맞았는데
         * K=10일 때는 2로 나누면 K=5가 되는데 N=7임
         * 그러면 5>=7이 되어서 루프 종료되어버림
         * N(7)에 도달하지도 못했는데 반복문이 끝나버림
         * (실제 답은 10 > 9 > 8 > 7 로 3번이어야 함)
         */
        // while(K >= N){
        while(K != N){
            // System.out.println(cnt + " " + K);
            if(K%2 == 0 && K/2 >= N){
                K /= 2;
            }else{
                K -= 1;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}
/*
1 더하고 2 곱하는 거 반복해서 K를 만드는 거네

최대한 2를 곱하는 게 답은 아님
 */
