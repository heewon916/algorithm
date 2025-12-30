package bj;
import java.io.*;
import java.util.*;

/**
 * 배운 거: 📌 정규식에서 . 의 의미
 * . → 임의의 문자 1개
 * 즉, "." 은 모든 문자 하나하나를 기준으로 split 하라는 뜻
 */
public class Main_bj_20291_파일정리 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        for(int i=0 ;i<N; i++){
            String input = br.readLine().split("\\.")[1];
            if(map.containsKey(input)){
                map.put(input, map.get(input)+1);
            }else{
                map.put(input, 1);
            }
        }
        Map<String, Integer> tmap = new TreeMap<>(map);
        StringBuilder sb = new StringBuilder();
        for(String key: tmap.keySet()){
            sb.append(key).append(" ").append(map.get(key)).append("\n");
        }
        System.out.println(sb);
    }
}
/*
만약 key, value 모두 정렬의 기준이 된다면
TreeMap을 제거하고 List 정렬로 가야 한다.
List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

Collections.sort(list, (e1, e2) -> {
    if (!e1.getValue().equals(e2.getValue())) {
        return e1.getValue() - e2.getValue(); // value 오름차순
    }
    return e1.getKey().compareTo(e2.getKey()); // value 같으면 key 사전순
});



 */