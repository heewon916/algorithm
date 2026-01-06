package bj;
import java.io.*;
import java.util.*;
public class Main_bj_27965_N결수 {
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long prev = 1;
        long mul = 10 % K; // 10 ^ digits(1) % K
        int len = 1;
        for(int i=2; i<=N; i++){
            if(i == Math.pow(10, len)){
                mul = (mul * 10) % K;
                len++;
            }
            prev = ((prev % K) * (mul % K) + (i % K)) % K;
        }
        System.out.println(prev % K);
    }
}
/*
아래 코드가 안되는 이유:
long temp = ((prev % K) * ((long)(Math.pow(10, (i+"").length())) % K) + (i % K)) % K;
1. (i+"").length() -> 반복마다 문자열이 생성된다 -> 숨은 비용이 증가함
2. Math.pow(10, d) -> double 연산 + 캐스팅 -> 단순 곱셈 루트가 아니라 부돔소수점 기반 거듭제곱 계산이라 상수시간이 커진다.
- 웬만하면 안 쓰는 게 좋다
 */