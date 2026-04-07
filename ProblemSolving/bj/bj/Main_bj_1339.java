package bj;
import java.io.*;
import java.util.*;

public class Main_bj_1339 {
    static int[] match;          // i번째 알파벳이 받을 숫자
    static boolean[] v;          // nums에서 사용 여부
    static int[] nums;           // 배정할 숫자들: 9,8,7,...
    static ArrayList<String> list;
    static ArrayList<Character> charList;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        Set<Character> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            String input = br.readLine();
            list.add(input);
            for(int j = 0; j < input.length(); j++){
                set.add(input.charAt(j));
            }
        }

        charList = new ArrayList<>(set);
        Collections.sort(charList);

        int len = charList.size();
        match = new int[len];
        v = new boolean[len];
        nums = new int[len];

        for(int i = 0; i < len; i++){
            nums[i] = 9 - i;
        }

        answer = 0;
        perm(0, len);

        System.out.println(answer);
    }

    static void perm(int cnt, int len){
        if(cnt == len){
            int sum = 0;
            Map<Character, Integer> map = new HashMap<>();

            for(int i = 0; i < len; i++){
                map.put(charList.get(i), match[i]); // 알파벳 #에 대응시킬 숫자
            }

            for(String str : list){
                int num = 0;
                for(int i = 0; i < str.length(); i++){
                    num = num * 10 + map.get(str.charAt(i));
                }
                sum += num;
            }

            answer = Math.max(answer, sum);
            return;
        }

        for(int i = 0; i < len; i++){
            if(v[i]) continue;
            v[i] = true;
            match[cnt] = nums[i];
            perm(cnt + 1, len);
            v[i] = false;
        }
    }
}