package bj;
import java.io.*;
import java.util.*;
public class Main_bj_1484_다이어트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken());
        ArrayList<Integer> ans = new ArrayList<>();
        for(int b=1; b<=G/2; b++){
            for(int a=b+1; (a+b)*(a-b)<=G; a++){ // 수식 계산 알아내기 +
                if(a*a - b*b == G) ans.add(a);
            }
        }
        StringBuilder sb = new StringBuilder();
        if(ans.isEmpty()) System.out.println(-1);
        else {
            Set<Integer> set = new HashSet<>(ans);
            ans = new ArrayList<>(set);
            Collections.sort(ans);
            for(int i: ans) sb.append(i).append(" \n");
            System.out.println(sb);
        }
    }
}
