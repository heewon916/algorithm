import java.util.*; 
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for(int i=0; i<discount.length-9; i++){
            Map<String, Integer> temp = new HashMap<>(); 
            for(int j=0 ;j<want.length; j++){
                temp.putIfAbsent(want[j], number[j]);
            }
            for(int j=i; j<i+10; j++){
                if(temp.containsKey(discount[j])){
                    int val = temp.get(discount[j]);
                    if(val >= 1) temp.put(discount[j], val-1); 
                }
            }
            boolean isLeft = false; 
            for(Integer val: temp.values()){
                if(val > 0) {
                    isLeft = true; 
                    break; 
                }
            }
            if(!isLeft){
                answer++;
            }
        }
        return answer;
    }
}
/*
10일 연속 보았을 때 갖고 싶은 게 다 충족되는지를 알아야 함 
*/