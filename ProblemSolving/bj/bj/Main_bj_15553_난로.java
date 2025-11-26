package bj;
import java.util.*;
import java.io.*;

public class Main_bj_15553_난로 {
    static class Time{
        long start, end;
        public Time(long start, long end) {
            this.start = start;
            this.end = end;
        }
        public String toString(){
            return this.start + "-" + this.end;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 시간 초기화
        int[] input = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            input[i] = Integer.parseInt(st.nextToken());
        }

        if(K >= N) {
            System.out.println(N);
            return;
        }

        long[] diffs = new long[N-1];

        for(int i=0; i<N-1; i++){
            diffs[i] = input[i+1] - input[i] - 1;
        }

        Arrays.sort(diffs);

        int ans = N;
        for(int i=0; i<N-K; i++){
            ans += diffs[i];
        }

        System.out.println(ans);
    }
}
/*
1-3 2 5-7 1 8-9 2 11-12 1 13-14 1 15-17 3 20-21

1 2 5 6 8 11 13 15 16 20
 1 3 1 2 3  2   2  1   4

구간 차가 1인 애들끼리만 먼저 묶으면
1-3 5-7 8-9 11-12 13-14 15-17 20-21
    2   1    2     1      1      3
여기서 또 구간 차가 1인 애들끼리 묶으면
1-3 5-9 11-12 13-14 15-17 20-21
    2   2     1       1      3
여기서 또 구간 차가 1인 애들끼리 묶으면
1-3 5-9 11-14 15-17 20-21 -> 근데 K묶음이 생기니까 끝내기

구간 차가 가장 작은 애들끼리 먼저 묶는다. 없으면 구간차+1해서 묶던가
묶는 작업
	1. i와 i+1의 시간차 구하기 (시작 그룹의 원소 개수는 1개)
	2. 여러 그룹 간의 차이 중 가장 작은 차를 구한다.
	3. 맨 앞에 있는 그 작은 차를 갖는 두 그룹을 묶는다.
	4.  그러고 묶음 개수가 K개 될때까지 반복한다.
 */