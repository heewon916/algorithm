package bj;
import java.io.*;
import java.util.*;
public class Main_bj_16456 {
    static int MOD = 1000000009;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());


    }
}
/*
n -> n + 1
n -> n + 2
n -> n - 1

n개의 섬을 보고 싶음
방문한 섬 또 안 감
모든 n개의 섬을 방문하는 가짓수

n 10^4 * 5

 */