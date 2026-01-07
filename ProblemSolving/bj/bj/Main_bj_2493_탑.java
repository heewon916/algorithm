package bj;
import java.io.*;
import java.util.*;
public class Main_bj_2493_탑 {
    static int N;
    static long[] h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        h = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            h[i] = Long.parseLong(st.nextToken());
        }

        long[] diff = new long[N];
        for(int i=N-1; i>0; i--){
            diff[i] = h[i-1] - h[i];
        }
//        System.out.println(Arrays.toString(diff));

        int[] ans = new int[N];
        for(int i=1; i<N; i++){
            if(diff[i] < 0) {
                int k = i-1;
                while(h[k] < h[i]){
                    if(k == 0) break;
                    k = ans[k]-1;
                }
                ans[i] = k+1;
            }
            else ans[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i : ans) sb.append(i).append(" ");
        System.out.println(sb);

    }
}
