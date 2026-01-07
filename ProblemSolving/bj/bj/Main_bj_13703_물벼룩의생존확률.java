package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_13703_물벼룩의생존확률 {
    static int n, k; 
    static int[] b; 
    static boolean[] v; 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        v = new boolean[n]; 
        b = new int[n];
        for(int i=0; i<=n; i++){
            comb(0, 0, i);
        }
        System.out.println((int)(Math.pow(2, n) - dieCnt));  
    }
    static int dieCnt = 0; 
    static void comb(int cnt, int start, int depth){
        if(cnt == depth){
            // System.out.println(Arrays.toString(b));
            int move = k; 
            // b[i] = 1일 때는 위로 올라갔다고 생각하고 -1, b[i] = 0일때는 아래로 내려간다고 생각하고 +1 
            // 그러다가 move = 0이 되면 문제가 생기는 거임
            for(int i=0; i<n; i++){
                if(b[i] == 1) move--; 
                else move++; 

                if(move == 0){
                    // 0이 되면 수면에 도착했단 소리니까 죽은 거임 
                    // 그럼 dieCnt++;  
                    // 더 이상 계산할 필요 없음
                    dieCnt++;
                    break; 
                }
            }
            return; 
        }
        for(int i=start; i<n; i++){
            b[i] = 1; 
            // System.out.println(cnt + ", " + start);
            comb(cnt+1, i+1, depth);
            b[i] = 0; 
        }
    }
}
/*
k센티미터에서 0이 되는 경우는 
4
n초 안에 0이 되는 경우
1111
1,0 -> 상쇄 
남은 1만 셌을 때 -> 그게 k여야 함 
모든 경우를 센다? 
k <= 63 
n초 -> 1초에 0 또는 1일 수 있음 
2^n 경우의수 -> 2^63 = 8 * 2^30 * 2^30 = 8 * 10^6 

시간제한 5초네 뭐임 - 1중 반복문만 가능한 상태

모든 경우에 대해서 1이 몇개고 0이 몇갠지 세는 게
n개를 전부 도니까 최대 63 

2^3 * 10^6 * (2^7-1) = 2
*/