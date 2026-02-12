package bj;

import java.io.*;
import java.util.*;

public class Main_bj_16974 {
    static int n;
    static long x, ans;
    static long[] totalLen;
    static long[] patty;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());

        totalLen = new long[n+1];
        patty = new long[n+1];
        totalLen[0] = 1;
        patty[0] = 1;
        for(int i=1; i<=n; i++){
            totalLen[i] = totalLen[i-1] * 2 + 3;
            patty[i] = patty[i-1]*2 + 1;
        }
//        System.out.println(Arrays.toString(totalLen));
//        System.out.println(Arrays.toString(patty));
        ans = 0;
        if(n == 0) ans =1; // 이렇게만 끝내면 안되고 dfs 안에서 level 0이 되면 ans++ 해줘야 해서 안에도 조건문 추가 필요
        else dfs(x, n);
        System.out.println(ans);
    }
    static void dfs(long temp_x, int level){
//        System.out.println("f(): temp_x="+temp_x+", level=" + level);
        // mid = totalLen[level]/2; // 이렇게 하는 것보다 직관적인 수식이 훨씬 좋다
        if(level == 0){
            ans += 1;
        }
        else if(temp_x == 1){
        }
        else if(temp_x < totalLen[level-1] + 2){
            dfs(temp_x-1, level-1); // 여기서도 앞에 번(B)를 1개 빼줘야 한다.
        }
        else if(temp_x == totalLen[level-1] + 2){
            ans += patty[level-1]+1;
//            System.out.println("ans: " + ans);
        }
        else if(temp_x < totalLen[level]){
            long d = patty[level-1] + 1;
            ans += d;
//            System.out.println("ans: " + ans);
            dfs(temp_x - (totalLen[level-1]+2), level-1); // temp_x에서 patty가 아니라 Ln-1에서의 앞부분 전체길이를 빼줘야 한다.
        }
        else if(temp_x == totalLen[level]){
            ans += patty[level];
//            System.out.println("ans: " + ans);
        }
    }
}
