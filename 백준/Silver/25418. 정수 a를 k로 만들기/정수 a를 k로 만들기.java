import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;
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
