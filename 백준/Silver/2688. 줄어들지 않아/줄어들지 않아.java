
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        arr = new long[65][10];
        Arrays.fill(arr[1], 1);
        for(int i=0; i<65; i++){
            arr[i][0] = 1;
        }
//        System.out.println(1 + ": "+ Arrays.toString(arr[1]));
        for(int i=2; i<=64; i++) { // 자릿수
            for (int j = 1; j < 10; j++) {
                arr[i][j] = arr[i][j - 1] + arr[i-1][j];
            }
//            System.out.println(i + ": "+ Arrays.toString(arr[i]));
        }

        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            // arr[n][9]가 아니라 arr[i][j]: i자리수의 맨 마지막 수가 j일 때 수의 개수 
            // i자리수에서 원하는 것 -> arr[i][j]의 합 
            long sum = 0; 
            for(int i=0; i<10; i++) sum += arr[n][i]; 
            sb.append(sum).append("\n");
        }
        System.out.println(sb);

    }
}
/*
   0 1 2 3 4 5 6 7 8 9
1: 1 1 1 1 1 1 1 1 1 1
2: 1 2 3 4 5 6 7 8 9 10
3: 1 3 6


1자리 -> 0~9 10개
2자리 -> 10*11/2 = 55개
3자리 ->
0_ -> 10
1_ -> 9
2_ -> 8
3_
4_
5_
..
9_ -> 1

0__
-> 00_
-> 01_
-> 02_
-> 03_



 */
