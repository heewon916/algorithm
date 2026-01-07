package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main_bj_13703_물벼룩의생존확률 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int k = Integer.parseInt(st.nextToken()); //
        int n = Integer.parseInt(st.nextToken());

        int[] h = new int[128]; // 인덱스가 높이를 뜻함
        int[] next = new int[128];
        h[k] = 1;

        for (int i = 0; i < n; i++) {
            next[1] += h[2];
            for (int j = 2; j < 127; j++) {
                next[j] += h[j+1];
                next[j] += h[j-1];
            }
            next[127] += h[126];
            next[0] = 0;
            h = next;
            next = new int[128];
        }
        System.out.printf(Arrays.toString(h));
        System.out.println(Arrays.stream(h).sum());
    }
}