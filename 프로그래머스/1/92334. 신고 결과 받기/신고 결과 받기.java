import java.util.*; 
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportMap = new HashMap<>(); 
        Map<String, Integer> cntMap = new HashMap<>(); 
        
        for(String id: id_list){
            Set<String> s = new HashSet<>(); 
            reportMap.put(id, s);
        }
        
        for(String str: report){
            String[] strSplit = str.split(" "); 
            String from = strSplit[0], to = strSplit[1]; 
            if(from.isEmpty()) break;
            if(!reportMap.get(from).isEmpty()){
                // 동일 신고인이 동일 유저를 신고한 경우는 1회로 처리한다. 
                if(!reportMap.get(from).contains(to)){
                    reportMap.get(from).add(to);
                    cntMap.put(to, cntMap.getOrDefault(to, 0)+1); 
                }
            }else{
                // from이 to를 처음 신고하는 경우에 해당한다 
                reportMap.get(from).add(to); 
                cntMap.put(to, cntMap.getOrDefault(to, 0)+1); 
            }
        }
        
        List<String> stop_list = new ArrayList<>(); 
        for(Map.Entry<String, Integer> e: cntMap.entrySet()){
            if(e.getValue() >= k){
                stop_list.add(e.getKey()); 
            }
        }
        
        int idx = 0; 
        
        for(String from : id_list){
            Set<String> to = reportMap.get(from); 
            int cnt = 0; 
            for(String id: stop_list){
                if(to.contains(id)) cnt++; 
            }
            answer[idx++] = cnt; 
        }
        
        return answer;
    }
}