package bj;
import java.io.*;
import java.util.*;
public class Main_bj_15810_풍선공장 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        System.out.println(Math.pow(10, 12) < Long.MAX_VALUE);
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Long[] arr = new Long[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        if(N > M){
            System.out.println(arr[M-1]);
            return;
        }
        long min = arr[0];
        arr[0] = Long.MAX_VALUE;
        long all = (int) Math.round(M/N);
        long left = M % N;
        Long maxTime = (long) (min * all + min * left);
//        System.out.println(all + " " + left + " " + min * all);
        for(int i=1; i<N; i++){
            if(arr[i] * all > maxTime){
                maxTime = (long) (arr[i] * all);
            }
        }
        System.out.println(maxTime);

    }
}
/*
N명의 스태프
M개의 풍선 만들기
풍선이 다 만들어지는 최소 시간
 */