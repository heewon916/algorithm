import java.util.*; 
class Solution {
    public boolean solution(String[] phone_book) {
        // 문자열의 길이로 오름차순 정렬 
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());
        
        Map<String, Integer> map = new HashMap<>(); 
        for(String str: phone_book){
            // System.out.println("about " + str);
            // System.out.println(map.keySet());
            
            for(int i=1; i<=str.length(); i++){
                // System.out.println(str.substring(0, i));
                if(map.containsKey(str.substring(0, i))) {
                    return false;  
                } 
            }
            map.putIfAbsent(str, 0);
        }
        return true;
    }
}