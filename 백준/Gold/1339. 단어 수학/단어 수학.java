import java.io.*;
import java.util.*;
public class Main{
    static int[] match;
    static boolean[] v;
    static ArrayList<String> list;
    static ArrayList<Character> charList;
    static int answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for(int i=0; i<n; i++){
            String input = br.readLine();
            list.add(input);
            for(int j=0; j<input.length(); j++) set.add(input.charAt(j));
        }

        charList = new ArrayList<>(set);
        Collections.sort(charList); // 주어졌던 문자열들에서 중복 없이 알파벳만 뽑음. 

        // 알파벳의 순열 
        match = new int[charList.size()]; // 맨 앞에서부터 9, 8, 7,.. 이렇게 할당함 
        v = new boolean[charList.size()];
        answer = 0;
        perm(0, match.length);
        System.out.println(answer);
    }
    static void perm(int cnt, int len){
        if(cnt == len){
            int sum = 0;
            Map<Character, Integer> map = new HashMap<>();
            for(int i=0; i<match.length; i++){
                map.put(charList.get(match[i]), 9-i );
            }
            for(String str: list){
                int n = map.get(str.charAt(0));
                for(int i=1; i<str.length(); i++){
                    n = n * 10 +  map.get(str.charAt(i));
                }
                sum += n;
            }
            answer = Math.max(answer, sum);
            return;
        }
        for(int i=0; i<len; i++){
            if(v[i]) continue;
            v[i] = true;
            match[cnt] = i;
            perm(cnt+1, len);
            v[i] = false;
        }
    }
}
