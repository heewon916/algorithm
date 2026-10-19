import java.util.*; 
import java.io.*; 
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> listMap = new HashMap<>();
        for(String name: participant){
            listMap.put(name, listMap.getOrDefault(name, 0)+1);
        }
        for(String name: completion){
            listMap.put(name, listMap.get(name)-1);
        }
        for(Map.Entry<String, Integer> entry: listMap.entrySet()){
            if(entry.getValue() > 0){
                return entry.getKey(); 
            }
        }
        
        return answer;
    }
}